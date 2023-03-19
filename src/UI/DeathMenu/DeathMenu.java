//TODO: Death Menu

package UI.DeathMenu;

import GLOOP.*;
import UI.MainMenu.Menu;

public class DeathMenu {
    GLTafel restartButton, menuButton;
    int selected = 0;
    boolean first = true;
    public void build(){
        restartButton = new GLTafel(0,0,0,100,50);
        restartButton.setzeSichtbarkeit(false);
        restartButton.setzeTextur("src/img/UI/restart.png");
        restartButton.drehe(90,180,0);

        menuButton = new GLTafel(0,0,0,100,50);
        menuButton.setzeSichtbarkeit(false);
        menuButton.setzeTextur("src/img/UI/menu.png");
        menuButton.drehe(90,180,0);
        menuButton.verschiebe(0,0,-60);
        System.out.println("Succeeded with building the Buttons");
    }
    public void onDeath(){
        restartButton.setzeSichtbarkeit(true);
        menuButton.setzeSichtbarkeit(true);
        System.out.println("Succeeded with displaying the Buttons");
    }
    public void onSelected(int button){
            if (button == 1 && selected != 1) {
                restartButton.setzeTextur("src/img/UI/selected-restart.png");
                menuButton.setzeTextur("src/img/UI/menu.png");
                selected = 1;
            } else if (button == 2 && selected != 2) {
                restartButton.setzeTextur("src/img/UI/restart.png");
                menuButton.setzeTextur("src/img/UI/selected-menu.png");
                selected = 2;
            }
    }
    public void run(){
        restartButton.setzeSichtbarkeit(false);
        restartButton.setzeTextur("src/img/UI/border.png");
        restartButton.setzeTextur("src/img/UI/restart.png");
        menuButton.setzeSichtbarkeit(false);
        menuButton.setzeTextur("src/img/UI/border.png");
        menuButton.setzeTextur("src/img/UI/menu.png");
        first = true;
        }
    }