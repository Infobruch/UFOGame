package UI.InGame;

import GLOOP.*;
public class GoldUI {
    GLTafel goldDisplay;
    int gold = 400;
    public void goldUI(){
        goldDisplay = new GLTafel(0,0,0,50,25);
        goldDisplay.drehe(90,0,0);
        goldDisplay.setzePosition(575,50,560);
        goldDisplay.setzeText("gold: "+ gold,25);
    }

    public void addGold(int addedGold){
        gold = gold + addedGold;
        System.out.println(gold);
        goldDisplay.setzeText("gold: "+ gold,25);
    }
    public void removeGold(int removedGold){
        gold = gold - removedGold;
        goldDisplay.setzeText("gold: "+ gold,25);
    }
    public int getGold(){
        return gold;
    }
    public void setGold(int newGold){
        gold = newGold;
        goldDisplay.setzeText("gold: "+ gold,25);
    }
    public void setVisibility(boolean visible){
        goldDisplay.setzeSichtbarkeit(visible);
    }
}
