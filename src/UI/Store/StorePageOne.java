package UI.Store;

import GLOOP.*;

public class StorePageOne {
    GLTafel nextButton,itemOne,itemTwo,itemThree,itemFour;
    public void build(){
        nextButton = new GLTafel(0,0,0,100,50);
        nextButton.setzeSichtbarkeit(true);
        nextButton.setzeTextur("src/img/UI/Icons/arrow.png");
        nextButton.drehe(90,180,0);
        itemOne = new GLTafel(0,0,0,100,50);
        itemOne.setzeSichtbarkeit(true);
        itemOne.setzeTextur("src/img/UI/arrow.png");
        itemOne.drehe(90,180,0);
    }
    public void onDeath(){

    }
    public void onSelected(int button){

    }
    public void run(){

    }
}