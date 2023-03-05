//TODO: Death Menu

package UI.DeathMenu;

import GLOOP.*;
public class DeathMenu {
    GLTafel restartButton;
    public void build(){
        restartButton = new GLTafel(0,0,0,100,50);
        restartButton.setzeSichtbarkeit(false);
        restartButton.setzeTextur("src/img/UI/restart.png");
        restartButton.drehe(90,0,0);
    }
    public void onDeath(){
        restartButton.setzeTextur("src/img/UI/restart.png");
        restartButton.setzeSichtbarkeit(true);
    }
    public void onSelected(){
        restartButton.setzeTextur("src/img/UI/selected-restart.png");
    }
    public void run(){
        restartButton.setzeSichtbarkeit(false);
    }
}
