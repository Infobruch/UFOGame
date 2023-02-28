import GLOOP.*;

public class Game {
    GLKamera cam1;
    GLLicht light;
    GLTastatur keyboard;
    GLHimmel himmel;
    Player.Player player;
    Asteroid[] asteroid;
    boolean turnUp,turnDown,turnLeft,turnRight = false;

    public Game(){
        cam1 = new GLKamera();
        cam1.setzePosition(0,-600,200);
        cam1.setzeBlickpunkt(0,0,200);
        cam1.setzeScheitelrichtung(0,0,1);

        light  = new GLLicht(-5000,-10000,0);

        keyboard = new GLTastatur();

        himmel = new GLHimmel("src/img/sternhimmel.jpg");

        player = new Player.Player("MFalcon");

        GLTextur texture = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[150];
        for (int i=0; i<150; i++){
            asteroid[i] = new Asteroid(player, texture);
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

            for (int i=0; i<150; i++)
                asteroid[i].move();
            Sys.warte();
        }
        Sys.beenden();
    }
}