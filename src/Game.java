//Steuerung mit WASD und Pfeiltasten
//esc um aus dem Shop zu kommen
//Backspace um das Program zu beenden (Speichert Gleichzeitig alle Daten)
//Daten = Zeit, Gold, gekaufte Items

import GLOOP.*;
import UI.DeathMenu.DeathMenu;
import UI.InGame.GoldUI;
import UI.InGame.Timer;
import UI.MainMenu.Menu;
import UI.Store.StorePageOne;
import UI.Store.StorePageTwo;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

public class Game {
    private final GLKamera cam1;
    private final GLLicht light;
    private final GLTastatur keyboard;
    private final GLNebel fog;
    private final GLHimmel himmel;
    GLTafel topTimeDisplay;
    private final Player.Player player;
    private final Asteroid[] asteroid;
    private Asteroid[] asteroid2;
    private final Gold[] gold;
    private final UI.InGame.GoldUI goldDisplay;
    private final UI.InGame.Timer timer;
    private final UI.DeathMenu.DeathMenu deathMenu;
    private final UI.MainMenu.Menu mainMenu;
    private final UI.Store.StorePageOne StoreOne;
    int asteroidCount = 200;
    int asteroidCount2 = 0;
    int coinCount = 15;
    boolean boughtItemOne,boughtItemTwo,boughtItemThree,boughtItemFour = false;
    boolean death,onDeath,store,storeFirst = false;
    boolean menu = true;
    int selected;
    int zeit,goldCount = 0;
    double time,oldTime,topTime,oldTopTime = 0;
    String ship = "MFalcon";

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

        timer = new Timer();

        GLTextur asteroidTex = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[asteroidCount];
        for (int i = 0; i < asteroidCount; i++) {
            asteroid[i] = new Asteroid(player, asteroidTex);
            asteroid[i].setVisibility(false);
            asteroid[i].move(5000,false);
        }

        GLTextur coinTex = new GLTextur("src/img/coin.jpg");
        gold = new Gold[coinCount];
        for (int i = 0; i < coinCount; i++) {
            gold[i] = new Gold(player, coinTex, goldDisplay);
            gold[i].setVisibility(false);
            gold[i].move(5000);
        }
        goldCount = goldDisplay.getGold();

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            prop.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
        boughtItemOne = Boolean.parseBoolean(prop.getProperty("bItemOne"));
        boughtItemTwo = Boolean.parseBoolean(prop.getProperty("bItemTwo"));
        boughtItemThree = Boolean.parseBoolean(prop.getProperty("bItemThree"));
        boughtItemFour = Boolean.parseBoolean(prop.getProperty("bItemFour"));
        goldCount = Integer.parseInt(prop.getProperty("Gold"));
        topTime = Double.parseDouble(prop.getProperty("TopTime"));
        goldDisplay.setGold(goldCount);

        topTimeDisplay = new GLTafel(0,0,0, 50, 25);
        topTimeDisplay.drehe(90,0,0);
        topTimeDisplay.verschiebe(0,0,400);
        topTimeDisplay.setzeText("Top Time: " + topTime,25);
        topTimeDisplay.setzeSichtbarkeit(false);

    }


    public void run() {
        timer.build();
        while (!keyboard.backspace()) {
            if (menu){
                topTimeDisplay.setzeSichtbarkeit(true);
                mainMenu.open();
                if (keyboard.oben() || keyboard.istGedrueckt('w')) {
                    selected = 1;
                    mainMenu.onSelected(selected);
                } else if (keyboard.unten() || keyboard.istGedrueckt('s'))  {
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
                        mainMenu.run();
                        menu = false;
                        selected = 0;
                        store = true;
                        storeFirst = true;
                    }
                }
            }
            if (store){
                topTimeDisplay.setzeSichtbarkeit(false);
                player.setVisibility(false);
                if (storeFirst){
                    StoreOne.open(boughtItemOne, boughtItemTwo, boughtItemThree, boughtItemFour);
                    storeFirst = false;
                }

                goldDisplay.setVisibility(true);
                if (keyboard.rechts() || keyboard.istGedrueckt('d')) {
                    // move right to the next button
                    if (selected < 4) {
                        selected++;
                        StoreOne.onSelected(selected);
                        Sys.warte(20);
                    } else {
                        selected = 1; // wrap around to the first button
                        StoreOne.onSelected(selected);
                        Sys.warte(20);
                    }
                } else if (keyboard.links() || keyboard.istGedrueckt('a')) {
                    // move left to the previous button
                    if (selected > 1) {
                        selected--;
                        StoreOne.onSelected(selected);
                        Sys.warte(20);
                    } else {
                        selected = 4; // wrap around to the last button
                        StoreOne.onSelected(selected);
                        Sys.warte(20);
                    }
                }
                if (keyboard.esc()){
                    store = false;
                    menu = true;
                    selected = 0;
                    StoreOne.run();
                    mainMenu.open();
                    goldDisplay.setVisibility(false);
                    System.out.println("Esc");
                }
                if (keyboard.enter()) {
                    // perform actions based on the selected button
                    switch (selected) {
                        case 1:
                            if (boughtItemOne) {
                                break;
                            }
                             if (goldDisplay.getGold() > 50||goldDisplay.getGold() == 50) {
                                 StoreOne.bought(1);
                                 goldDisplay.removeGold(50);
                                 boughtItemOne = true;
                                 selected = 0;
                                 System.out.println("bought 1");
                                 selected = 0;
                                 break;
                             }else {
                                 break;
                             }
                        case 2:
                            if (boughtItemTwo) {
                                break;
                            }
                            if (goldDisplay.getGold() > 10||goldDisplay.getGold() == 10) {
                                StoreOne.bought(2);
                                goldDisplay.removeGold(10);
                                boughtItemTwo = true;
                                System.out.println("bought 2");
                                selected = 0;
                                break;
                            }else {
                                break;
                            }
                        case 3:
                            if (boughtItemThree) {
                                break;
                            }
                            if (goldDisplay.getGold() > 30||goldDisplay.getGold() == 30) {
                                StoreOne.bought(3);
                                goldDisplay.removeGold(10);
                                for (int i = 0; i < asteroidCount; i++) {
                                asteroid[i].scale(1.16, 1.16, 1.16);
                            }
                                asteroidCount2 = (int) Math.round(asteroidCount* 0.16);
                                asteroid2 = new Asteroid[asteroidCount2];
                                GLTextur asteroidTex = new GLTextur("src/img/asteroid2.jpg");
                                for (int i = 0; i < asteroidCount2; i++) {
                                    asteroid2[i] = new Asteroid(player, asteroidTex);
                                    asteroid2[i].setVisibility(false);
                                    asteroid2[i].move(5000,false);
                                }
                                boughtItemThree = true;
                                System.out.println("bought 3");
                                selected = 0;
                                break;
                                }else {
                                    break;
                                }

                        case 4:
                            if (boughtItemFour) {
                                break;
                            }
                            //System.out.println(goldDisplay.getGold());
                            if (goldDisplay.getGold() == 20||goldDisplay.getGold()>20) {
                            goldDisplay.removeGold(20);
                            StoreOne.bought(4);
                            boughtItemFour = true;
                            System.out.println("bought 4");
                            selected = 0;
                            break;
                            }else {
                                break;
                            }

                        default:
                            // handle error if selected button is out of range
                            break;
                    }
                }
            }
            if (death && !menu) {
                if (onDeath) {
                    topTimeDisplay.setzeSichtbarkeit(true);
                    oldTopTime = topTime;
                    if (time > topTime) {
                        topTime = time;
                    } else if (oldTime>oldTopTime) {
                        topTime = oldTime;
                    }
                    topTimeDisplay.setzeText("Top Time: " + topTime,25);
                    oldTime = time;
                    time = timer.getTime();
                    if (time > topTime) {
                        topTime = time;
                        topTimeDisplay.setzeText("Top Time: " + topTime,25);
                    }
                    timer.onDeath();
                    goldDisplay.setVisibility(false);
                    player.setVisibility(false);
                    for (int i = 0; i < asteroidCount; i++) {
                        asteroid[i].setVisibility(false);
                        asteroid[i].move(5000 ,false);
                    }
                    for (int i = 0; i < asteroidCount2; i++) {
                        asteroid2[i].setVisibility(false);
                        asteroid2[i].move(5000 ,false);
                    }
                    for (int i = 0; i < coinCount; i++) {
                        gold[i].setVisibility(false);
                        gold[i].move(5000);
                    }
                    onDeath = false;
                    System.out.println();
                }
                if (keyboard.oben() || keyboard.istGedrueckt('w')) {
                    selected = 1;
                    deathMenu.onSelected(selected);
                } else if (keyboard.unten() || keyboard.istGedrueckt('s')) {
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
                        menu = true;
                        store = false;
                        mainMenu.open();
                        selected = 0;
                    }
                }
            }

                if (!death && !menu && !store){
                    topTimeDisplay.setzeSichtbarkeit(false);
                    player.setVisibility(true);
                    if (boughtItemOne) {
                    zeit++;
                    if (zeit == 500) {
                        zeit = 0;
                        goldDisplay.addGold(1);
                    }
                }
                    if (keyboard.istGedrueckt('w') || keyboard.oben()) {
                        if (ship == "MFalcon") player.setRotation(105, 0, 0);
                        if (ship == "TFighter") player.setRotation(0, 0, -10);
                        if(boughtItemFour){
                            player.moveUp(0.1);
                        }else {
                            player.moveUp(0);
                        }
                    }
                    if (keyboard.istGedrueckt('a') || keyboard.links()) {
                        if (ship == "MFalcon") player.setRotation(90, 0, 15);
                        if (ship == "TFighter") player.setRotation(0, 0, 10);
                        if(boughtItemFour){
                            player.moveLeft(0.1);
                        }else {
                            player.moveLeft(0);
                        }
                    }
                    if (keyboard.istGedrueckt('s') || keyboard.unten()) {
                        if (ship == "MFalcon") player.setRotation(80, 0, -10);
                        if (ship == "TFighter") player.setRotation(0, 0, -10);
                        if(boughtItemFour){
                            player.moveDown(0.1);
                        }else {
                            player.moveDown(0);
                        }
                    }
                    if (keyboard.istGedrueckt('d') || keyboard.rechts()){
                        if (ship == "MFalcon") player.setRotation(90, 0, -15);
                        if (ship == "TFighter") player.setRotation(0, 0, -10);
                        if(boughtItemFour){
                            player.moveRight(0.1);
                        }else {
                            player.moveRight(0);
                        }
                    }
                    if (boughtItemThree){
                        for (int i = 0; i < asteroidCount2; i++) {
                            if (boughtItemTwo) {
                                asteroid2[i].move(50, true);
                            } else {
                                asteroid2[i].move(0, false);
                            }
                            asteroid2[i].setVisibility(true);
                            if (asteroid2[i].hit()) {
                                deathMenu.onDeath();
                                death = true;
                                onDeath = true;
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < asteroidCount; i++) {
                        asteroid[i].move(0, boughtItemTwo);
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
                if (!keyboard.istGedrueckt('w') && !keyboard.istGedrueckt('a') && !keyboard.istGedrueckt('s') && !keyboard.istGedrueckt('d')  && !keyboard.links() && !keyboard.oben() && !keyboard.rechts() && !keyboard.unten() || death) {
                    player.setRotation(0, 0, 0);
                    if (ship == "MFalcon") {
                        player.rotate(90, 0, 0);
                    }
                }

                Sys.warte();
            }
            Properties prop = new Properties();



            goldCount = goldDisplay.getGold();
            prop.setProperty("bItemOne", Boolean.toString(boughtItemOne));
            prop.setProperty("bItemTwo", Boolean.toString(boughtItemTwo));
            prop.setProperty("bItemThree", Boolean.toString(boughtItemThree));
            prop.setProperty("bItemFour", Boolean.toString(boughtItemFour));
            prop.setProperty("Gold", Integer.toString(goldCount));
            prop.setProperty("TopTime", Double.toString(time));

            try (OutputStream output = new FileOutputStream("config.properties")) {
                prop.store(output, "Gold and Items bought");
                //System.out.println("Variables saved successfully.");
            } catch (IOException io) {
                io.printStackTrace();
            }
            Sys.beenden();
        }
    }