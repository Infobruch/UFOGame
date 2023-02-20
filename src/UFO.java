import GLOOP.*;
public class UFO {
    private GLTorus base;
    private GLKugel cockpit;
    private GLKegel wing1, wing2;
    //[...]

    public UFO(){
        base = new GLTorus(0,0,0,50,20);
        base.skaliere (0.5,1,0.5);
        base.setzeQualitaet(24);

        cockpit = new GLKugel(0,0,0,25);
        cockpit.setzeMaterial(GLMaterial.GOLD);

        wing1 = new GLKegel(-60,-10,0,15,70);
        wing1.skaliere(0.2,1,1);
        wing1.drehe(0,90,0, -60,10,0);
        wing1.drehe(0,0,45, 0,0,0);
        wing1.setzeFarbe(0,0,1);

        wing2 = new GLKegel(60,-10,0,15,70);
        wing2.skaliere(0.2,1,1);
        wing2.drehe(0,-90,0, 60,10,0);
        wing2.drehe(0,0,-45, 0,0,0);
        wing2.setzeFarbe(0,0,1);

    }
 
    public void moveLeft(){
        base.verschiebe(-1,0,0);

        cockpit.verschiebe(-1,0,0);

        wing1.verschiebe(-1,0,0);
        wing2.verschiebe(-1,0,0);
    }

    public void moveRight(){
        base.verschiebe(1,0,0);

        cockpit.verschiebe(1,0,0);

        wing1.verschiebe(1,0,0);
        wing2.verschiebe(1,0,0);
    }

    public void moveDown(){
        base.verschiebe(0,0,-1);

        cockpit.verschiebe(0,0,-1);

        wing1.verschiebe(0,0,-1);
        wing2.verschiebe(0,0,-1);
    }

    public void moveUp(){
        base.verschiebe(0,0,1);

        cockpit.verschiebe(0,0,1);

        wing1.verschiebe(0,0,1);
        wing2.verschiebe(0,0,1);
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

            cockpit.verschiebe(-n,-n,-n);
            cockpit.drehe(-n,n,n);

            wing1.verschiebe(-n,n,-n);
            wing2.verschiebe(-n,-n,n);

            Sys.warte();
        }
        Sys.beenden();
    }

}
