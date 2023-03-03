package UI.InGame;

import GLOOP.*;
public class CoinUI {
    GLTafel goldDisplay;
    int gold = 0;
    public void CoinUI(){
        goldDisplay = new GLTafel(0,0,0,50,25);
        goldDisplay.drehe(90,0,0);
        goldDisplay.setzePosition(575,50,560);
        goldDisplay.setzeText("gold: "+ gold,25);
    }

    public void AddCoin(int addedGold){
        gold = gold + addedGold;
        System.out.println(gold);
        goldDisplay.setzeText("gold: "+ gold,25);
    }
    public void RemoveGold(int removedGold){
        gold = gold + removedGold;
        goldDisplay.setzeText("gold: "+ gold,25);
    }
}
