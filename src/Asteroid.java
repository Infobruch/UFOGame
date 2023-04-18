import GLOOP.*;

public class Asteroid{
    private final GLKugel asteroid;
    private final Player.Player player;
    public Asteroid(Player.Player pPlayer, GLTextur pTexture){
        int x = (int)(Math.random()*3000 - 1700);
        int y = (int)(Math.random()*4000);
        int z = (int)(Math.random()*3000 - 1700);
        asteroid = new GLKugel(x,y,z, 50, pTexture);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
        player = pPlayer;
    }

    public void move(int tp,boolean slower){
        if (slower) {
            asteroid.verschiebe(0,(-2*0.9)+tp,0);
        }
        else {
            asteroid.verschiebe(0, -2 + tp, 0);
        }
        asteroid.drehe(0.1,0.1,0.1);
        if (asteroid.gibY()<-300) this.reset();
    }
    public void scale(double x, double y, double z){
        asteroid.setzeSkalierung(x,y,z);
    }
    public boolean hit(){
        double distance = Math.sqrt(Math.pow( asteroid.gibX()- player.getX(), 2 ) + Math.pow( asteroid.gibY()- player.getY(), 2 ) + Math.pow( asteroid.gibZ()- player.getZ(), 2 ));
        if (player.model == "TFighter") {
            return distance < 100;
        } else if (player.model == "MFalcon") {
            return distance < 80;
        }
        else return false;
    }

    public void setVisibility(boolean visible){
        asteroid.setzeSichtbarkeit(visible);
    }

    public void reset(){
        int x = (int)(Math.random()*3000 - 1700);
        int z = (int)(Math.random()*3000 - 1600);
        asteroid.setzePosition(x,4000,z);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
    }
}