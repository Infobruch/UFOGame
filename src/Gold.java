import GLOOP.*;

public class Gold {
    private GLKugel gold;
    private Player.Player player;
    UI.InGame.GoldUI goldUI;
    public Gold(Player.Player pPlayer, GLTextur pTexture, UI.InGame.GoldUI pGoldUI){
        int x = (int)(Math.random()*3000 - 1700);
        int y = (int)(Math.random()*4000);
        int z = (int)(Math.random()*3000 - 1700);
        gold = new GLKugel(x,y,z, 20, pTexture);
        gold.setzeSkalierung(1,Math.random()+0.3,1);
        player = pPlayer;
        goldUI = pGoldUI;
    }

    public void move(){
        gold.verschiebe(0,-2,0);
        //gold.drehe(0.1,0.1,0.1);
        if (gold.gibY()<-300) this.reset();
        if (hit()){
            gold.verschiebe(0,-100,0);
            gold.setzeSichtbarkeit(false);
            goldUI.addGold(1);
        }
    }

    private boolean hit(){
        double distance = Math.sqrt(Math.pow( gold.gibX()- player.giveX(), 2 ) + Math.pow( gold.gibY()- player.giveY(), 2 ) + Math.pow( gold.gibZ()- player.giveZ(), 2 ));
        if (player.model == "TFighter") {
            if (distance < 80) return true;
            else return false;
        } else if (player.model == "MFalcon") {
            if (distance < 80) return true;
            else return false;
        }
        else return false;
    }

    private void reset(){
        int x = (int)(Math.random()*3000 - 1700);
        int z = (int)(Math.random()*3000 - 1600);
        gold.setzePosition(x,4000,z);
        gold.setzeSkalierung(1,Math.random()+0.3,1);
        gold.setzeSichtbarkeit(true);
    }

    public void setVisibility(boolean visible){
        gold.setzeSichtbarkeit(visible);
    }
}