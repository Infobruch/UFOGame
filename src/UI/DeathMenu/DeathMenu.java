//TODO: Death Menu

package UI.DeathMenu;

import GLOOP.*;
public class DeathMenu {
    GLTafel restartButton;
    public void build(){
        restartButton = new GLTafel(0,0,0,100,50);
        restartButton.setzeSichtbarkeit(false);
        restartButton.setzeTextur("src/img/UI/restart.png");
        restartButton.drehe(90,180,0);
        System.out.println("Success");
    }
    public void onDeath(){
        restartButton.setzeSichtbarkeit(true);
        System.out.println("Success");
    }
    public void onSelected(){
        restartButton.setzeTextur("src/img/UI/selected-restart.png");
    }
    public void run(){
        restartButton.setzeSichtbarkeit(false);
        restartButton.setzeTextur("src/img/UI/restart.png");
    }
}
