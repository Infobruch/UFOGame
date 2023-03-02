package UI.InGame;

import GLOOP.*;
public class CoinUI {
    GLTafel coinDisplay;
    int coins = 0;
    public void CoinUI(){
        coinDisplay = new GLTafel(0,0,0,50,25);
        coinDisplay.drehe(90,0,0);
        coinDisplay.setzePosition(575,50,560);
        coinDisplay.setzeText("Coins: "+ coins,25);
    }

    public void AddCoin(int addedCoins){
        coins = coins + addedCoins;
        System.out.println(coins);
        coinDisplay.setzeText("Coins: "+ coins/2,25);
    }
    public void RemoveCoins(int removedCoins){
        coins = coins + removedCoins;
        coinDisplay.setzeText("Coins: "+ coins,25);
    }
}
