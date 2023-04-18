package Player;

import GLOOP.*;
import Player.Passt.Model;

import java.io.File;

public class Player {
    Model player;
    public String model;
    public boolean is_deleted = false;

    public Player(String pModel){
        model = pModel;
    }
    public void build(){
        if (model == "TFighter"){
            player = new Model(0,0,0,0,0,0,new File("src/Model/t-fighter.stl"),model);
            player.skaliere(0.15);
            is_deleted = false;
        }
        if (model == "MFalcon"){
            player = new Model(0,0,0,0,0,0,new File("src/Model/m-falcon.stl"),model);
            player.drehe(90,0,0);
            player.skaliere(3.0);
            is_deleted = false;
        }
    }
    public void moveLeft(double movefaster){
        if (this.getX() < -500 || this.getX() == -500){}
        else {
            player.verschiebe(-1-movefaster, 0, 0);
        }
    }
    public void moveRight(double movefaster){
        if (this.getX() > 500 || this.getX() == 500){}
        else {
            player.verschiebe(1+movefaster, 0, 0);
        }
    }
    public void moveDown(double movefaster){
        if (this.getZ() < -500 || this.getZ() == -500){}
        else {
            player.verschiebe(0, 0, -1-movefaster);
        }
    }
    public void moveUp(double movefaster){
        if (this.getZ() > 500 || this.getZ() == 500){}
        else {
            player.verschiebe(0, 0, 1+movefaster);
        }
    }

    public void setRotation(double x,double y,double z){
        player.setzeDrehung(x,y,z);
    }
    public void rotate(double x, double y, double z){
        player.drehe(x,y,z);
    }

    public double getX(){
        return player.gibX();
    }
    public double getY(){
        return player.gibY();
    }
    public double getZ(){
        return player.gibZ();
    }

    public void setVisibility(boolean visible){
        player.setzeSichtbarkeit(visible);
    }
    public void reset(){
        player.setzeSichtbarkeit(true);
        player.setzePosition(0,0,0);
    }
    public void delete(){
        player.loesche();
        is_deleted = true;
    }
    public void createPlayer(String pModel){
        if (is_deleted){
            model = pModel;
            build();
        }
    }

}
