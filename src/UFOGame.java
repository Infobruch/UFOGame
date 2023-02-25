import GLOOP.*;
public class UFOGame{
    GLKamera cam1;
    GLLicht light;
    GLTastatur keyboard;
    GLHimmel himmel;
    UFO UFO;
    Asteroid[] asteroid;

    public UFOGame(){
        cam1 = new GLKamera();
        cam1.setzePosition(0,-600,200);
        cam1.setzeBlickpunkt(0,0,200);
        cam1.setzeScheitelrichtung(0,0,1);

        light  = new GLLicht(-5000,-10000,0);

        keyboard = new GLTastatur();

        himmel = new GLHimmel("src/img/Sterne.jpg");

        UFO = new UFO();

        GLTextur texture = new GLTextur("src/img/Krater.jpg");
        asteroid = new Asteroid[150];
        for (int i=0; i<150; i++){
            asteroid[i] = new Asteroid(UFO, texture);
        }

        run();
    }

    public void run(){
        while(!keyboard.esc()){
            if (keyboard.istGedrueckt('a')) UFO.moveLeft();
            if (keyboard.istGedrueckt('d')) UFO.moveRight();
            if (keyboard.istGedrueckt('s')) UFO.moveDown();
            if (keyboard.istGedrueckt('w')) UFO.moveUp();

            for (int i=0; i<150; i++)
                asteroid[i].move();

            Sys.warte();
        }
        Sys.beenden();
    }
}
