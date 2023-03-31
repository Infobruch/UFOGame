package UI.Store;

import GLOOP.*;

public class StorePageOne {
    GLTafel StoreNameDisplay,itemOne,itemTwo,itemThree,itemFour,nextButton;
    boolean visible = false;
    boolean boughtItemOne,boughtItemTwo,boughtItemThree,boughtItemFour;
    int selected = 0;
    public void build(){
        StoreNameDisplay = new GLTafel(0,0,0,200,150);
        StoreNameDisplay.setzeSichtbarkeit(visible);
        StoreNameDisplay.setzeTextur("src/img/transparent.png");
        StoreNameDisplay.drehe(90,0,0);
        StoreNameDisplay.setzeText("Store",50);
        StoreNameDisplay.setzeTextfarbe(1,1,1);
        StoreNameDisplay.verschiebe(0,0,400);

        nextButton = new GLTafel(0,0,0,100,50);
        nextButton.setzeSichtbarkeit(visible);
        nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
        nextButton.drehe(90,180,0);
        nextButton.verschiebe(230,0,200);

        itemOne = new GLTafel(0,0,0,70,100);
        itemOne.setzeSichtbarkeit(visible);
        itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
        itemOne.drehe(90,0,0);
        itemOne.verschiebe(-130,0,200);

        itemTwo = new GLTafel(0,0,0,70,100);
        itemTwo.setzeSichtbarkeit(visible);
        itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
        itemTwo.drehe(90,0,0);
        itemTwo.verschiebe(-45,0,200);

        itemThree = new GLTafel(0,0,0,70,100);
        itemThree.setzeSichtbarkeit(visible);
        itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
        itemThree.drehe(90,0,0);
        itemThree.verschiebe(40,0,200);

        itemFour = new GLTafel(0,0,0,70,100);
        itemFour.setzeSichtbarkeit(visible);
        itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
        itemFour.drehe(90,0,0);
        itemFour.verschiebe(125,0,200);
    }
    public void open(boolean bItemOne, boolean bItemTwo, boolean bItemThree, boolean bItemFour){
        boughtItemOne = bItemOne;
        boughtItemTwo = bItemTwo;
        boughtItemThree = bItemThree;
        boughtItemFour = bItemFour;
        visible = false;
        StoreNameDisplay.setzeSichtbarkeit(visible);
        nextButton.setzeSichtbarkeit(visible);
        itemOne.setzeSichtbarkeit(visible);
        itemTwo.setzeSichtbarkeit(visible);
        itemThree.setzeSichtbarkeit(visible);
        itemFour.setzeSichtbarkeit(visible);
    }
    public void onSelected(int button){
        if (button == 1 && selected != 1) {
            itemOne.setzeTextur("src/img/UI/Upgrades/selected-MoreGold.png");
            itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
            selected = 1;
        } else if (button == 2 && selected != 2) {
            itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            itemTwo.setzeTextur("src/img/UI/Upgrades/selected-SlowerAsteroids.png");
            itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
            selected = 2;
        }else if (button == 3 && selected != 3) {
            itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            itemThree.setzeTextur("src/img/UI/Upgrades/selected-smallerAsteroids.png");
            itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
            selected = 3;
        }else if (button == 4 && selected != 4) {
            itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            itemFour.setzeTextur("src/img/UI/Upgrades/selected-fasterMoving.png");
            nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
            selected = 4;
        }else if (button == 5 && selected != 5) {
            itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            nextButton.setzeTextur("src/img/UI/Icons/selected-arrow.png");
            selected = 5;
        }
    }
    public void run(){
        visible = false;
        selected = 0;
        itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
        itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
        itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
        itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
        nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
        itemOne.setzeSichtbarkeit(visible);
        itemTwo.setzeSichtbarkeit(visible);
        itemThree.setzeSichtbarkeit(visible);
        itemFour.setzeSichtbarkeit(visible);
        nextButton.setzeSichtbarkeit(visible);
        StoreNameDisplay.setzeSichtbarkeit(visible);
    }
}