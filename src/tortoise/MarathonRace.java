/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tortoise;

import java.util.ArrayList;

/**
 *
 * @author Archana
 */
public class MarathonRace {
     
     
     
    public static ArrayList<ThreadRun> runningThreads = new ArrayList<>();
     
     
    public static void main(String[] args) throws InterruptedException {
         
        MarathonRace myMarathonRace = new MarathonRace();
         
         
         
            runningThreads.add(new ThreadRun("Tortoise", 10, 0));
            runningThreads.add(new ThreadRun("Hare", 100, 90));
            //runningThreads.add(new ThreadRunner("Dog", 50, 40));
            //runningThreads.add(new ThreadRunner("Cat", 30, 75));
           // runningThreads.add(new ThreadRunner("Lion", 50, 40));
 
             
         
         
          
            myMarathonRace.runThreadsConcurrent();
            runningThreads.clear();
             
            myMarathonRace.hitEntertoContinue();
             
             
              
 
     
    }
     
     
    private void runThreadsConcurrent() throws InterruptedException
    {
        System.out.println("Get set... Go!");
         
         
        for(int i = 0; i < runningThreads.size(); i++ )
        {
            runningThreads.get(i).start();  
        }
         
        for(int i = 0; i < runningThreads.size(); i++ )
        {
            runningThreads.get(i).join();
 
        }
    }
     
    private void hitEntertoContinue()
    {
        System.out.println("Press Enter to continue");  
        try
        {
            System.in.read();
        }  
        catch(Exception e){} 
    }
     
     
     
    public  synchronized void finished(Thread winner, String winnerName) {      
        for(int i = 0; i < runningThreads.size(); i++ )
        {
            if(winnerName.equals(runningThreads.get(i).getRunnersName()))
                System.out.println("The race is over! The " + winnerName + " is the winner" + "\n");            
        }
        for(int i = 0; i < runningThreads.size(); i++ )
        {
            if(!winnerName.equals(runningThreads.get(i).getRunnersName()))
                runningThreads.get(i).interrupt();
        }
        for(int i = 0; i < runningThreads.size(); i++ )
        {
            if(!winnerName.equals(runningThreads.get(i).getRunnersName()))
                System.out.println(runningThreads.get(i).getRunnersName() + ": You beat me fair and square." + "\n");           
        }
    }
     
}