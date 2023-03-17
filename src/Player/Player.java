package Player;

import GLOOP.*;
import Player.Passt.Model;

import java.io.File;

public class Player {
    Model player;
    public String model;

    public Player(String pmodel){
        model = pmodel;
        if (model == "TFighter"){
            player = new Model(0,0,0,0,0,0,new File("src/Model/t-fighter.stl"),model);
            player.skaliere(0.15);
        }
        if (model == "MFalcon"){
            player = new Model(0,0,0,0,0,0,new File("src/Model/m-falcon.stl"),model);
            player.drehe(90,0,0);
            player.skaliere(3.0);
        }
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

    public void setRotation(double x,double y,double z){
        player.setzeDrehung(x,y,z);
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
            player.verschiebe(n,n,-n);
            player.drehe(-n,n,n);
            Sys.warte();
        }
    }
}
