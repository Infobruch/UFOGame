//TODO: Menu
package UI.MainMenu;

import GLOOP.*;
public class Menu {
    GLTafel startButton, storeButton;
    int selected = 0;
    public void build(){
        startButton = new GLTafel(0,0,0,100,50);
        startButton.setzeSichtbarkeit(false);
        startButton.setzeTextur("src/img/UI/start.png");
        startButton.drehe(90,180,0);

        storeButton = new GLTafel(0,0,0,100,50);
        storeButton.setzeSichtbarkeit(false);
        storeButton.setzeTextur("src/img/UI/menu.png");
        storeButton.drehe(90,180,0);
        storeButton.verschiebe(0,0,-60);


        System.out.println("Succeeded with building the Buttons");
    }
    public void open(){
        startButton.setzeSichtbarkeit(true);
        storeButton.setzeSichtbarkeit(true);

        //System.out.println("Succeeded with displaying the Buttons");
    }
    public void onSelected(int button){
        if (button == 1 && selected != 1) {
            startButton.setzeTextur("src/img/UI/selected-start.png");
            storeButton.setzeTextur("src/img/UI/menu.png");
            selected = 1;
        } else if (button == 2 && selected != 2) {
            startButton.setzeTextur("src/img/UI/start.png");
            storeButton.setzeTextur("src/img/UI/selected-menu.png");
            selected = 2;
        }
    }
    public void run(){
        startButton.setzeSichtbarkeit(false);
        startButton.setzeTextur("src/img/UI/border.png");
        startButton.setzeTextur("src/img/UI/start.png");
        storeButton.setzeSichtbarkeit(false);
        storeButton.setzeTextur("src/img/UI/border.png");
        storeButton.setzeTextur("src/img/UI/menu.png");

    }
}

