import GLOOP.*;
import java.io.File;

public class Player {
    Model player;

    public Player(){
        player = new Model(0,0,0,10,10,10,new File("src/Model/t-fighter.stl"));
        //player.drehe(90,0,0);
        //player.skaliere(3.0);
        player.skaliere(0.15);
    }

    public void moveLeft(){
        if (this.giveX() < -500 || this.giveX() == -500){}
        else {
            player.verschiebe(-1, 0, 0);
        }
    }
    public void moveRight(){
        if (this.giveX() > 500 || this.giveX() == 500){}
        else {
            player.verschiebe(1, 0, 0);
        }
    }
    public void moveDown(){
        if (this.giveZ() < -500 || this.giveZ() == -500){}
        else {
            player.verschiebe(0, 0, -1);
        }
    }
    public void moveUp(){
        if (this.giveZ() > 500 || this.giveZ() == 500){}
        else {
            player.verschiebe(0, 0, 1);
        }
    }

    public void rotate(double x,double y,double z){
        player.drehe(x,y,z);
    }

    public double giveX(){
        return player.gibX();
    }
    public double giveY(){
        return player.gibY();
    }
    public double giveZ(){
        return player.gibZ();
    }

    public void explode(){
        double n = Math.random();
        for (int i=0; i< 2000; i++){
            player.verschiebe(-n,n,-n);
            player.drehe(-n,-n,n);
            Sys.warte();
        }
        Sys.beenden();
    }
}