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
    boolean turnUp, turnDown, turnLeft, turnRight = false;
    boolean death,onDeath = true;
    boolean menu = true;
    int deathSelected;
    int menuSelected;
    String ship = "TFighter";

    public Game() {
        cam1 = new GLKamera();
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
        while (true){
            if (keyboard.enter()) {break;}
        }

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
                    menuSelected = 1;
                    mainMenu.onSelected(menuSelected);
                } else if (keyboard.unten()) {
                    menuSelected = 2;
                    mainMenu.onSelected(menuSelected);
                }

                if (keyboard.enter()) {
                    if (menuSelected == 1) {
                        // Perform actions for button 1
                        death = false;
                        mainMenu.run();
                        goldDisplay.setVisibility(true);
                        player.reset();
                        menu = false;
                    } else if (menuSelected == 2) {
                        // Perform actions for button 2
                        deathMenu.run();
                        menu = true;
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
                    deathSelected = 1;
                    deathMenu.onSelected(deathSelected);
                } else if (keyboard.unten()) {
                    deathSelected = 2;
                    deathMenu.onSelected(deathSelected);
                }

                if (keyboard.enter()) {
                    if (deathSelected == 1) {
                        // Perform actions for button 1
                        death = false;
                        deathMenu.run();
                        goldDisplay.setVisibility(true);
                        player.reset();
                    } else if (deathSelected == 2) {
                        // Perform actions for button 2
                        deathMenu.run();
                        menu = true;
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
