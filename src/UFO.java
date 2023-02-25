import GLOOP.*;

import java.io.File;

public class UFO {
    private GLTorus base;
    Model ufo;
    public UFO(){
        base = new GLTorus(0,0,0,50,20);
        base.skaliere (0.5,1,0.5);
        base.setzeQualitaet(24);
        ufo = new Model(0,0,0,10,10,10,new File("C:/Users/jornw/IdeaProjects/UFOGame/src/test.stl"));
    }
 
    public void moveLeft(){
        if (this.giveX() < -500 || this.giveX() == -500){}
        else {
            base.verschiebe(-1, 0, 0);
        }
    }

    public void moveRight(){
        if (this.giveX() > 500 || this.giveX() == 500){}
        else {
            base.verschiebe(1, 0, 0);
        }
    }

    public void moveDown(){
        if (this.giveZ() < -500 || this.giveZ() == -500){}
        else {
            base.verschiebe(0, 0, -1);
        }
    }
    public void moveUp(){
        if (this.giveZ() > 500 || this.giveZ() == 500){}
        else {
            base.verschiebe(0, 0, 1);
        }
    }

    public double giveX(){
        return base.gibX();
    }

    public double giveY(){
        return base.gibY();
    }

    public double giveZ(){
        return base.gibZ();
    }
    public void explode(){
        double n = Math.random();
        for (int i=0; i< 2000; i++){
            base.verschiebe(-n,n,-n);
            base.drehe(-n,-n,n);

            Sys.warte();
        }
        Sys.beenden();
    }

}
