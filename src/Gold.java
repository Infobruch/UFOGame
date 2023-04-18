import GLOOP.*;

public class Gold {
    private final GLKugel gold;
    private final Player.Player player;
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
    public void move(int tp){
        gold.verschiebe(0,-2+tp,0);
        //gold.drehe(0.1,0.1,0.1);
        if (gold.gibY()<-300) this.reset();
        if (hit()){
            gold.setzeSichtbarkeit(false);
            gold.verschiebe(0,-1000,0);
            goldUI.addGold(1);
        }
    }
    private boolean hit(){
        double distance = Math.sqrt(Math.pow( gold.gibX()- player.getX(), 2 ) + Math.pow( gold.gibY()- player.getY(), 2 ) + Math.pow( gold.gibZ()- player.getZ(), 2 ));
        if (player.model == "TFighter") {
            return distance < 80;
        } else if (player.model == "MFalcon") {
            return distance < 80;
        }
        else return false;
    }
    public void reset(){
        int x = (int)(Math.random()*3000 - 1700);
        int z = (int)(Math.random()*3000 - 1600);
        gold.setzePosition(x,4000,z);
        gold.setzeSkalierung(1,Math.random()+0.3,1);

    }
    public void setVisibility(boolean visible){
        gold.setzeSichtbarkeit(visible);
    }
}