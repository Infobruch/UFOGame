import GLOOP.*;
public class Asteroid{
    private GLKugel asteroid;
    private UFO UFO;

    public Asteroid(UFO pUFO, GLTextur pTexture){
        int x = (int)(Math.random()*3000 - 1500);
        int y = (int)(Math.random()*4000);
        int z = (int)(Math.random()*3000 - 1500);
        asteroid = new GLKugel(x,y,z, 50, pTexture);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
        UFO = pUFO;
    }

    public void move(){
        asteroid.verschiebe(0,-2,0);
        asteroid.drehe(0.1,0.1,0.1);
        if (asteroid.gibY()<-300) this.reset();
        if (this.hit()) UFO.explode();
    }

    private boolean hit(){
        double distance = Math.sqrt(Math.pow( asteroid.gibX()-UFO.giveX(), 2 ) + Math.pow( asteroid.gibY()-UFO.giveY(), 2 ) + Math.pow( asteroid.gibZ()-UFO.giveZ(), 2 ));
        if (distance<70) return true;
        else return false;
    }

    private void reset(){
        int x = (int)(Math.random()*3000 - 1700);
        int z = (int)(Math.random()*3000 - 1600);
        asteroid.setzePosition(x,4000,z);
        asteroid.setzeSkalierung(1,Math.random()+0.3,Math.random()+0.3);
    }
}
