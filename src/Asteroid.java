import GLOOP.*;
import UI.DeathMenu.DeathMenu;

public class Asteroid{
    private GLKugel asteroid;
    private Player.Player player;
    public Asteroid(Player.Player pPlayer, GLTextur pTexture){
        int x = (int)(Math.random()*3000 - 1700);
        int y = (int)(Math.random()*4000);
        int z = (int)(Math.random()*3000 - 1700);
        asteroid = new GLKugel(x,y,z, 50, pTexture);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
        player = pPlayer;
    }

    public void move(){
        asteroid.verschiebe(0,-2,0);
        asteroid.drehe(0.1,0.1,0.1);
        if (asteroid.gibY()<-300) this.reset();
    }

    public boolean hit(){
        double distance = Math.sqrt(Math.pow( asteroid.gibX()- player.giveX(), 2 ) + Math.pow( asteroid.gibY()- player.giveY(), 2 ) + Math.pow( asteroid.gibZ()- player.giveZ(), 2 ));
        if (player.model == "TFighter") {
            if (distance < 100) return true;
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
        asteroid.setzePosition(x,4000,z);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
    }
}