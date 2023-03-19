//TODO: Menu
package UI.MainMenu;

import GLOOP.*;
public class Menu {
    GLTafel startButton, menuButton, exitButton;
    int selected = 0;
    public void build(){
        startButton = new GLTafel(0,0,0,100,50);
        startButton.setzeSichtbarkeit(false);
        startButton.setzeTextur("src/img/UI/start.png");
        startButton.drehe(90,180,0);

        menuButton = new GLTafel(0,0,0,100,50);
        menuButton.setzeSichtbarkeit(false);
        menuButton.setzeTextur("src/img/UI/menu.png");
        menuButton.drehe(90,180,0);
        menuButton.verschiebe(0,0,-60);

        exitButton = new GLTafel(0,0,0,100,50);
        exitButton.setzeSichtbarkeit(false);
        exitButton.setzeTextur("src/img/UI/exit.png");
        exitButton.drehe(90,180,0);
        exitButton.verschiebe(0,0,-120);
        System.out.println("Succeeded with building the Buttons");
    }
    public void open(){
        startButton.setzeSichtbarkeit(true);
        menuButton.setzeSichtbarkeit(true);
        exitButton.setzeSichtbarkeit(true);
        //System.out.println("Succeeded with displaying the Buttons");
    }
    public void onSelected(int button){
        if (button == 1) {
            if (!(selected == 1)){
                startButton.setzeTextur("src/img/UI/start.png");
            }
            selected = 1;
            startButton.setzeTextur("src/img/UI/selected-start.png");
            System.out.println("Succeeded with changing texture: start");
        } else if (button == 2) {
            if (!(selected == 2)){
                menuButton.setzeTextur("src/img/UI/menu.png");
            }
            selected = 2;
            menuButton.setzeTextur("src/img/UI/selected-menu.png");
            System.out.println("Succeeded with changing texture: menu");
        }else {
            System.out.println("Wrong number");
        }
    }
    public void run(){
        startButton.setzeSichtbarkeit(false);
        startButton.setzeTextur("src/img/UI/border.png");
        startButton.setzeTextur("src/img/UI/start.png");
        menuButton.setzeSichtbarkeit(false);
        menuButton.setzeTextur("src/img/UI/border.png");
        menuButton.setzeTextur("src/img/UI/menu.png");
        exitButton.setzeSichtbarkeit(false);
        exitButton.setzeTextur("src/img/UI/border.png");
        exitButton.setzeTextur("src/img/UI/exit.png");
    }
}

