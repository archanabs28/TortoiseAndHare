package tortoise;
/**
 * Racer was written to demonstrate my knowledge in Multi-Threading
 * It is a racer that is represented by the Tortoise and Hare story
 */

public class Racer implements Runnable {
    
    public static String winner;
    
    public void race() {
        
        //Change this value to tell the Hare when to sleep
        final int HARES_DEMISE = 65;
        
        for (int distance = 0; distance <= 100; distance++) {
            
            System.out.println("Distance covered by " + Thread.currentThread().getName() + " is " + distance + " meters.");
            
            //The Hare is over-confident and decides to slack off...Bad idea for the Hare
            if (distance == HARES_DEMISE && Thread.currentThread().getName().equals("Hare")) {
                
                //The Hare decides to take a nap midway through since he is so far ahead
                try {
                    System.out.println(Thread.currentThread().getName() + " is sleeeeeping ZZZZZZZZZ");
                    Thread.sleep(1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
            //Check if race is finished
            boolean isRaceWon = this.isRaceWon(distance);
            
            //Break out if true...Not dynamic but will do the trick
            if (isRaceWon) 
                break;
            
        }
        
    }
    
    private boolean isRaceWon(int totalDistance) {
        
        boolean isRaceWon = false;
        
        if ((Racer.winner == null) && (totalDistance == 100)) {
            
            String winnerName = Thread.currentThread().getName();
            
            Racer.winner = winnerName;
            
            System.out.println("Winner is: " + Racer.winner);
            
            isRaceWon = true;
            
        } else if (Racer.winner == null) {
            
            isRaceWon = false;
            
        } else if (Racer.winner != null) {
            
            isRaceWon = true;
            
        }
        
        return isRaceWon;
        
    }
    
    @Override
    public void run() {
        
        this.race();
        
    }
    
}
