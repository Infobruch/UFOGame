import GLOOP.*;
import UI.DeathMenu.DeathMenu;
import UI.InGame.GoldUI;
import UI.InGame.Timer;
import UI.MainMenu.Menu;
import UI.Store.StorePageOne;

import java.util.UUID;

public class Game {
    private GLKamera cam1;
    private GLLicht light;
    private GLTastatur keyboard;
    private GLNebel fog;
    private GLHimmel himmel;
    private Player.Player player;
    private Asteroid[] asteroid;
    private Gold[] gold;
    private UI.InGame.GoldUI goldDisplay;
    private UI.InGame.Timer timer;
    private UI.DeathMenu.DeathMenu deathMenu;
    private UI.MainMenu.Menu mainMenu;
    private UI.Store.StorePageOne StoreOne;
    int asteroidCount = 200;
    int coinCount = 15;
    boolean boughtItemOne,boughtItemTwo,boughtItemThree,boughtItemFour;
    boolean death,onDeath,store = false;
    boolean menu = true;
    int selected;
    String ship = "TFighter";

    public Game() {
        cam1 = new GLEntwicklerkamera();
        cam1.setzePosition(0, -600, 200);
        cam1.setzeBlickpunkt(0, 0, 200);
        cam1.setzeScheitelrichtung(0, 0, 1);
        light = new GLLicht(-5000, -10000, 0);
        keyboard = new GLTastatur();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        fog = new GLNebel();
        fog.setzeNebelbereich(2000,3500);
        fog.setzeFarbe(0,0,0);
        player = new Player.Player(ship);
        player.build();
        player.setVisibility(false);

        goldDisplay = new GoldUI();
        goldDisplay.goldUI();
        goldDisplay.setVisibility(false);

        mainMenu = new Menu();
        mainMenu.build();
        deathMenu = new DeathMenu();
        deathMenu.build();
        StoreOne = new StorePageOne();
        StoreOne.build();

        timer = new Timer();

        GLTextur asteroidTex = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[asteroidCount];
        for (int i = 0; i < asteroidCount; i++) {
            asteroid[i] = new Asteroid(player, asteroidTex);
            asteroid[i].setVisibility(false);
            asteroid[i].move(5000);
        }

        GLTextur coinTex = new GLTextur("src/img/coin.jpg");
        gold = new Gold[coinCount];
        for (int i = 0; i < coinCount; i++) {
            gold[i] = new Gold(player, coinTex, goldDisplay);
            gold[i].setVisibility(false);
            gold[i].move(5000);
        }
    }

    public void run() {
        timer.build();
        while (!keyboard.esc() && !keyboard.alt()) {
            if (menu){
                mainMenu.open();
                if (keyboard.oben()) {
                    selected = 1;
                    mainMenu.onSelected(selected);
                } else if (keyboard.unten()) {
                    selected = 2;
                    mainMenu.onSelected(selected);
                }
                if (keyboard.enter()) {
                    if (selected == 1) {
                        // Perform actions for button 1
                        death = false;
                        mainMenu.run();
                        goldDisplay.setVisibility(true);
                        player.reset();
                        menu = false;
                        selected = 0;
                    } else if (selected == 2) {
                        // Perform actions for button 2
                        deathMenu.run();
                        menu = true;
                        selected = 0;
                    }
                }
            }
            if (store){
                if (keyboard.rechts()) {
                    // move right to the next button
                    if (selected < 5) {
                        selected++;
                    } else {
                        selected = 1; // wrap around to the first button
                    }
                } else if (keyboard.links()) {
                    // move left to the previous button
                    if (selected > 1) {
                        selected--;
                    } else {
                        selected = 5; // wrap around to the last button
                    }
                }
                if (keyboard.enter()) {
                    // perform actions based on the selected button
                    switch (selected) {
                        case 1:
                            if (boughtItemOne) {
                                break;
                            }
                            StoreOne.onSelected(1);
                            boughtItemOne = true;
                            selected = 0;
                            break;
                        case 2:
                            if (boughtItemTwo) {
                                break;
                            }
                            StoreOne.onSelected(2);
                            boughtItemTwo = true;
                            break;
                        case 3:
                            if (boughtItemThree) {
                                break;
                            }
                            StoreOne.onSelected(3);
                            boughtItemThree = true;
                            break;
                        case 4:
                            if (boughtItemFour) {
                                break;
                            }
                            StoreOne.onSelected(4);
                            boughtItemFour = true;
                            break;
                        case 5:
                            // perform actions for button 5
                            StoreOne.onSelected(5);
                            break;
                        default:
                            // handle error if selected button is out of range
                            break;
                    }
                }
            }
            if (death && !menu) {
                timer.onDeath();
                goldDisplay.setVisibility(false);
                player.setVisibility(false);
                if (onDeath) {
                    for (int i = 0; i < asteroidCount; i++) {
                        asteroid[i].setVisibility(false);
                        asteroid[i].move(5000);
                    }
                    for (int i = 0; i < coinCount; i++) {
                        gold[i].setVisibility(false);
                        gold[i].move(5000);
                    }
                    onDeath = false;
                    System.out.println();
                }
                if (keyboard.oben()) {
                    selected = 1;
                    deathMenu.onSelected(selected);
                } else if (keyboard.unten()) {
                    selected = 2;
                    deathMenu.onSelected(selected);
                }

                if (keyboard.enter()) {
                    if (selected == 1) {
                        // Perform actions for button 1
                        death = false;
                        deathMenu.run();
                        goldDisplay.setVisibility(true);
                        player.reset();
                        selected = 0;
                    } else if (selected == 2) {
                        // Perform actions for button 2
                        deathMenu.run();
                        menu = false;
                        store = true;
                        selected = 0;
                        StoreOne.open(boughtItemOne,boughtItemTwo,boughtItemThree,boughtItemFour);
                    }
                }
            }

                if (!death && !menu) {
                    player.setVisibility(true);
                    if (keyboard.istGedrueckt('w')) {
                        player.setRotation(10, 0, 0);
                        player.moveUp();
                    }
                    if (keyboard.istGedrueckt('a')) {
                        player.setRotation(0, 0, 10);
                        player.moveLeft();
                    }
                    if (keyboard.istGedrueckt('s')) {
                        player.setRotation(-10, 0, 0);
                        player.moveDown();
                    }
                    if (keyboard.istGedrueckt('d')) {
                        player.setRotation(0, 0, -10);
                        player.moveRight();
                    }
                    for (int i = 0; i < asteroidCount; i++) {
                        asteroid[i].move(0);
                        asteroid[i].setVisibility(true);
                        if (asteroid[i].hit()) {
                            deathMenu.onDeath();
                            death = true;
                            onDeath = true;
                            break;
                        }
                    }
                    for (int i = 0; i < coinCount; i++) {
                        gold[i].move(0);
                        gold[i].setVisibility(true);
                    }
                    timer.run();
                }
                if (!keyboard.istGedrueckt('w') && !keyboard.istGedrueckt('a') && !keyboard.istGedrueckt('s') && !keyboard.istGedrueckt('d') || death) {
                    player.setRotation(0, 0, 0);
                    if (ship == "MFalcon") {
                        player.rotate(90, 0, 0);
                    }
                }
                Sys.warte();
            }
        }
    }