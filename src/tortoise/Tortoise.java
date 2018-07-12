package tortoise;

class ThreadRunner extends Thread {

    private String name;
    private int restValue, speed;

    ThreadRunner(String Name, int RestValue, int Speed) {
        name = Name;
        restValue = RestValue;
        speed = Speed;
    }

    public void run() {
        int distance = 0;
        while (!isInterrupted() && distance < 1000) {
            try {
                int rand = (int) (Math.random() * 100);
                if (restValue <= rand) {
                    distance += speed;
                    System.out.println(name + " : " + distance);
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(name + ": You beat me fair and square." + "\n");
                break;
            }
        }
        if (distance >= 1000) {
            System.out.println(name + ": I finished!" + "\n");
            Tortoise.finished(Thread.currentThread(), name);
        }
    }
}

public class Tortoise {

    public static Thread tortoise;
    public static Thread hare;

    public static void main(String[] args) {

        tortoise = new ThreadRunner("Tortoise", 0, 10);
        hare = new ThreadRunner("Hare", 90, 100);

        System.out.println("Get set... Go!");
        tortoise.start();
        hare.start();
         Racer racer = new Racer();
       
        
//        Thread tortoise = new Thread(racer, "Tortoise");
//        
//        Thread hare = new Thread(racer, "Hare");
//        
//        //And were off
//        tortoise.start();
//        hare.start();

    }

    public static synchronized void finished(Thread winner, String winnerName) {

        if (winner.equals(hare)) {
            System.out.println("The race is over! The hare is the winner" + "\n");
            tortoise.interrupt();

        }
        if (winner.equals(tortoise)) {
            System.out.println("The race is over! The tortoise is the winner" + "\n");
            hare.interrupt();

        }

    }

}
