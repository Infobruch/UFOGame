import GLOOP.*;
public class Coins{
    private GLKugel coin;
    private Player.Player player;
    public Coins(Player.Player pPlayer, GLTextur pTexture){
        int x = (int)(Math.random()*3000 - 1700);
        int y = (int)(Math.random()*4000);
        int z = (int)(Math.random()*3000 - 1700);
        coin = new GLKugel(x,y,z, 20, pTexture);
        coin.setzeSkalierung(1,Math.random()+0.3,1);
        player = pPlayer;
    }

    public void move(){
        coin.verschiebe(0,-2,0);
        coin.drehe(0.1,0.1,0.1);
        if (coin.gibY()<-300) this.reset();
        if (hit()){
            coin.setzeSichtbarkeit(false);
        }
    }

    private boolean hit(){
        double distance = Math.sqrt(Math.pow( coin.gibX()- player.giveX(), 2 ) + Math.pow( coin.gibY()- player.giveY(), 2 ) + Math.pow( coin.gibZ()- player.giveZ(), 2 ));
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
        coin.setzePosition(x,4000,z);
        coin.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
    }
}