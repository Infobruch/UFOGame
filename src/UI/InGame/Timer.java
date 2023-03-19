package UI.InGame;

import GLOOP.*;
import java.time.LocalTime;
public class Timer {
    GLTafel timer;
    double time = 0, deltaTime = 0;

    public void build(){
        timer = new GLTafel(0,0,0, 50, 25);
        timer.drehe(90,0,0);
        timer.setzeTextfarbe(1, 0, 0);
        timer.setzePosition(575,50,535);
        timer.setzeText("test",25);
        timer.setzeSichtbarkeit(false);
    }
    public void run(){
        timer.setzeSichtbarkeit(true);
        if(java.time.LocalTime.now().getSecond() - deltaTime == 1 || java.time.LocalTime.now().getSecond() - deltaTime == -59){
            time++;
            timer.setzeText("Timer: " + time, 25);
        }
        deltaTime = java.time.LocalTime.now().getSecond();
    }
    public void onDeath(){
        timer.setzeSichtbarkeit(false);
        deltaTime = 0;
        time = 0;
    }
}