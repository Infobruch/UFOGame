import GLOOP.*;
import UI.DeathMenu.DeathMenu;
import UI.InGame.GoldUI;
import UI.InGame.Timer;

public class Game {
    private GLKamera cam1;
    private GLLicht light;
    private GLTastatur keyboard;
    private GLHimmel himmel;
    private Player.Player player;
    private Asteroid[] asteroid;
    private Gold[] coins;
    private UI.InGame.GoldUI goldDisplay;
    private UI.InGame.Timer timer;
    private UI.DeathMenu.DeathMenu deathMenu;
    int asteroidCount, coinCount = 0;
    boolean turnUp, turnDown, turnLeft, turnRight = false;
    boolean death = false;
    int selected;

    public Game() {
        asteroidCount = 150;
        coinCount = 10;
        cam1 = new GLKamera();
        cam1.setzePosition(0, -600, 200);
        cam1.setzeBlickpunkt(0, 0, 200);
        cam1.setzeScheitelrichtung(0, 0, 1);
        light = new GLLicht(-5000, -10000, 0);
        keyboard = new GLTastatur();
        himmel = new GLHimmel("src/img/Sterne.jpg");
        player = new Player.Player("TFighter");

        goldDisplay = new GoldUI();
        goldDisplay.goldUI();

        timer = new Timer();

        deathMenu = new DeathMenu();
        deathMenu.build();

        GLTextur asteroidTex = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[asteroidCount];
        for (int i = 0; i < asteroidCount; i++) {
            asteroid[i] = new Asteroid(player, asteroidTex);
        }

        GLTextur coinTex = new GLTextur("src/img/coin.jpg");
        coins = new Gold[coinCount];
        for (int i = 0; i < coinCount; i++) {
            coins[i] = new Gold(player, coinTex, goldDisplay);
        }
    }

    public void run() {
        timer.build();
        System.out.println("test");
        while (!keyboard.esc()) {
            if (death) {
                for (int i = 0; i < asteroidCount; i++) {
                    asteroid[i].setVisibility(false);
                }
                for (int i = 0; i < coinCount; i++) {
                    coins[i].setVisibility(false);
                }
                if (keyboard.oben()) {
                    selected = 1;
                    deathMenu.onSelected(selected);
                } else if (keyboard.unten()) {
                    selected = 2;
                    deathMenu.onSelected(selected);
                }
                if (selected == 1 && keyboard.enter()) {
                    death = false;
                    deathMenu.run();
                } else if (selected == 2 && keyboard.enter()) {
                }
            }
            if (!death) {
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
                timer.run();
            }
            if (!keyboard.istGedrueckt('w') && !keyboard.istGedrueckt('a') && !keyboard.istGedrueckt('s') && !keyboard.istGedrueckt('d') || death) {
                player.setRotation(0, 0, 0);
            }

            if (!death) {
                for (int i = 0; i < asteroidCount; i++) {
                    asteroid[i].move();
                    if (asteroid[i].hit()) {
                        deathMenu.onDeath();
                        death = true;
                        break;
                    }
                }
                for (int i = 0; i < coinCount; i++)
                    coins[i].move();
                Sys.warte();
            }
            //Sys.beenden();
        }
    }
}