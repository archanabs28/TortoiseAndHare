/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortoise;

/**
 *
 * @author Archana
 */
import java.util.ArrayList;
 
 
public class ThreadRun extends Thread {
     
    public static final int MARATHON_DISTANCE = 1000;
     
     
    private String runnersName;
    private int restPercentage, runnersSpeed;
    private volatile int distance;
    private MarathonRace myMarathonRace = new MarathonRace();
 
    //Constructors
    ThreadRun(){
        runnersName = "";
        restPercentage = 0;
        runnersSpeed = 0;       
    }
     
     
    ThreadRun(String runnersName, int runnersSpeed, int restPercentage)
    {
        this.runnersName = runnersName;
        this.restPercentage = restPercentage;
        this.runnersSpeed = runnersSpeed;
    }
      
    // Getter
    public String getRunnersName() {
        return runnersName;
    }
     
    //Setter
    public void setRunnersName(String runnersName) {
        this.runnersName = runnersName;
    }
     
    public void setRestPercentage(int restPercentage) {
        this.restPercentage = restPercentage;
    }
     
    public void setRunnersSpeed(int runnersSpeed) {
        this.runnersSpeed = runnersSpeed;
    }
     
    
     
    public void run() {  
            while (!isInterrupted() && distance < MARATHON_DISTANCE) {
                try
                {
                     
                    int randonNumber = (int) (Math.random() * 101);
                    if (randonNumber <= restPercentage) //Thread rests if random is less or equal to restTime
                    {             
                        Thread.sleep(100);
                    }
                    else
                    {
                        synchronized (getClass()) {
                            if (isInterrupted()) {
                                break;
                            }
                          
                            distance += runnersSpeed;
                            System.out.println(runnersName + " : " + distance);
                          
                            if (distance >= MARATHON_DISTANCE) {
                                System.out.println(runnersName + ": I finished!" + "\n");
                                myMarathonRace.finished(Thread.currentThread(), runnersName);
                            }
                        }
                    }
                     
                }
                catch (InterruptedException e){
                    break;
                }
            }
                     
    }
 
}
 
 