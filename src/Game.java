import GLOOP.*;
import UI.InGame.CoinUI;
import UI.InGame.Timer;

public class Game {
    GLKamera cam1;
    GLLicht light;
    GLTastatur keyboard;
    GLHimmel himmel;
    Player.Player player;
    Asteroid[] asteroid;
    Coins[] coins;
    UI.InGame.CoinUI coinDisplay;
    UI.InGame.Timer timer;
    int asteroidCount,coinCount = 0;
    boolean turnUp,turnDown,turnLeft,turnRight = false;

    public Game(){
        asteroidCount = 150;
        coinCount = 10;
        cam1 = new GLEntwicklerkamera();
        cam1.setzePosition(0,-600,200);
        cam1.setzeBlickpunkt(0,0,200);
        cam1.setzeScheitelrichtung(0,0,1);

        light  = new GLLicht(-5000,-10000,0);

        keyboard = new GLTastatur();

        himmel = new GLHimmel("src/img/sternhimmel.jpg");

        player = new Player.Player("TFighter");

        coinDisplay = new CoinUI();
        coinDisplay.CoinUI();

        timer = new Timer();
        timer.build();


        GLTextur asteroidTex = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[asteroidCount];
        for (int i=0; i<asteroidCount; i++){
            asteroid[i] = new Asteroid(player, asteroidTex);
        }

        GLTextur coinTex = new GLTextur("src/img/coin.jpg");
        coins = new Coins[coinCount];
        for (int i=0; i<coinCount; i++){
            coins[i] = new Coins(player, coinTex,coinDisplay);
        }
    }
    public void run(){
        while(!keyboard.esc()){
            if (keyboard.istGedrueckt('a')) {
                if (turnLeft){
                    player.moveLeft();
                }
                else {
                    player.moveLeft();
                    player.rotate(0,0,10);
                    turnLeft = true;
                }
            }
            else {
                if (turnLeft){
                    turnLeft = false;
                    player.rotate(0,0,-10);
                }
            }
            if (keyboard.istGedrueckt('d')) {
                if (turnRight){
                    player.moveRight();
                }
                else {
                    player.moveRight();
                    player.rotate( 0,0,-10);
                    turnRight = true;
                }
            }
            else {
                if (turnRight){
                    turnRight = false;
                    player.rotate(0,0,10);
                }
            }
            if (keyboard.istGedrueckt('s')) {
                if (turnDown){
                    player.moveDown();
                }
                else {
                    player.moveDown();
                    player.rotate(-10,0,0);
                    turnDown = true;
                }
            }
            else {
                if (turnDown){
                    turnDown = false;
                    player.rotate(10,0,0);
                }
            }
            if (keyboard.istGedrueckt('w')){
                if (turnUp){
                    player.moveUp();
                }
                else {
                    player.moveUp();
                    player.rotate(10,0,0);
                    turnUp = true;
                }
            }
            else {
                if (turnUp){
                    turnUp = false;
                    player.rotate(-10,0,0);
                }
            }
            timer.run();
            for (int i=0; i<asteroidCount; i++)
                asteroid[i].move();
            for (int i=0; i<coinCount; i++)
                coins[i].move();
            Sys.warte();
        }
        Sys.beenden();
    }
}