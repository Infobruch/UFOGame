package UI.Store;

import GLOOP.*;

public class StorePageOne {
    GLTafel StoreNameDisplay,itemOne,itemTwo,itemThree,itemFour;
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
        StoreNameDisplay.verschiebe(-25,0,400);

        itemOne = new GLTafel(0,0,0,70,100);
        itemOne.setzeSichtbarkeit(visible);
        itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
        itemOne.drehe(90,0,0);
        itemOne.verschiebe(-150,0,200);

        itemTwo = new GLTafel(0,0,0,70,100);
        itemTwo.setzeSichtbarkeit(visible);
        itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
        itemTwo.drehe(90,0,0);
        itemTwo.verschiebe(-65,0,200);

        itemThree = new GLTafel(0,0,0,70,100);
        itemThree.setzeSichtbarkeit(visible);
        itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
        itemThree.drehe(90,0,0);
        itemThree.verschiebe(20,0,200);

        itemFour = new GLTafel(0,0,0,70,100);
        itemFour.setzeSichtbarkeit(visible);
        itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
        itemFour.drehe(90,0,0);
        itemFour.verschiebe(105,0,200);
    }
    public void open(boolean bItemOne, boolean bItemTwo, boolean bItemThree, boolean bItemFour){
        boughtItemOne = bItemOne;
        boughtItemTwo = bItemTwo;
        boughtItemThree = bItemThree;
        boughtItemFour = bItemFour;
        visible = true;

        StoreNameDisplay.setzeSichtbarkeit(visible);
        itemOne.setzeSichtbarkeit(visible);
        itemTwo.setzeSichtbarkeit(visible);
        itemThree.setzeSichtbarkeit(visible);
        itemFour.setzeSichtbarkeit(visible);

        if (bItemOne) {
            itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
        }else if(bItemTwo) {
            itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
        } else if (bItemThree) {
            itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
        } else if (bItemFour) {
            itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
        }

        if (boughtItemThree) {
            itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
        }

        if (boughtItemFour) {
            itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
        }
    }
    public void onSelected(int button){
        if (button == 1 && selected != 1) {
            if (boughtItemOne){
                itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
            }else {
                itemOne.setzeTextur("src/img/UI/Upgrades/selected-MoreGold.png");
            }

            if (boughtItemTwo) {
                itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
            }else {
                itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            }

            if (boughtItemThree) {
                itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
            }else {
                itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            }

            if (boughtItemFour) {
                itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
            }else {
                itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            }

            selected = 1;
        } else if (button == 2 && selected != 2) {
            if (boughtItemOne){
                itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
            }else {
                itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            }

            if (boughtItemTwo) {
                itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
            }else {
                itemTwo.setzeTextur("src/img/UI/Upgrades/selected-SlowerAsteroids.png");
            }

            if (boughtItemThree) {
                itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
            }else {
                itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            }

            if (boughtItemFour) {
                itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
            }else {
                itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            }

            selected = 2;
        }else if (button == 3 && selected != 3) {
            if (boughtItemOne){
                itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
            }else {
                itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            }

            if (boughtItemTwo) {
                itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
            }else {
                itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            }

            if (boughtItemThree) {
                itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
            }else {
                itemThree.setzeTextur("src/img/UI/Upgrades/selected-smallerAsteroids.png");
            }

            if (boughtItemFour) {
                itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
            }else {
                itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");
            }

            selected = 3;
        }else if (button == 4 && selected != 4) {
            if (boughtItemOne){
                itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
            }else {
                itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
            }

            if (boughtItemTwo) {
                itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
            }else {
                itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
            }

            if (boughtItemThree) {
                itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
            }else {
                itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
            }

            if (boughtItemFour) {
                itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
            }else {
                itemFour.setzeTextur("src/img/UI/Upgrades/selected-fasterMoving.png");
            }

            selected = 4;
        }
    }
    public void bought(int item){
        if (item == 1){
            itemOne.setzeTextur("src/img/UI/Upgrades/bought-MoreGold.png");
            boughtItemOne = true;
        }else if (item == 2){
            itemTwo.setzeTextur("src/img/UI/Upgrades/bought-SlowerAsteroids.png");
            boughtItemTwo = true;
        }else if (item == 3){
            itemThree.setzeTextur("src/img/UI/Upgrades/bought-smallerAsteroids.png");
            boughtItemThree = true;
        }else if (item == 4){
            itemFour.setzeTextur("src/img/UI/Upgrades/bought-fasterMoving.png");
            boughtItemFour = true;
        }
    }
    public void run(){
        visible = false;
        selected = 0;
        itemOne.setzeTextur("src/img/UI/Upgrades/MoreGold.png");
        itemTwo.setzeTextur("src/img/UI/Upgrades/SlowerAsteroids.png");
        itemThree.setzeTextur("src/img/UI/Upgrades/smallerAsteroids.png");
        itemFour.setzeTextur("src/img/UI/Upgrades/fasterMoving.png");

        itemOne.setzeSichtbarkeit(visible);
        itemTwo.setzeSichtbarkeit(visible);
        itemThree.setzeSichtbarkeit(visible);
        itemFour.setzeSichtbarkeit(visible);

        StoreNameDisplay.setzeSichtbarkeit(visible);
    }
}