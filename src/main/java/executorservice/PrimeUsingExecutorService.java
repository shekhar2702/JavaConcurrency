package executorservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.*;

@SpringBootApplication
public class PrimeUsingExecutorService {
    public static void main(String args[]) {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);//same as above but since the ExecutorService don't provide some specific implementations here I used the specific class.
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1); //same explanation as above.
        /*
        * Types of executors:-FixedThreadPool,CachedThreadPool,ScheduledThreadPool,SingleThreadPool
        * CachedThreadPool creates new threads on demand unlike FixedThreadPool same is the behaviour with
        * ScheduledThreadPool but in this some sort of scheduling is also there in addition to cachedThreadPool feature
        * SingleThreadPool is one in which only 1 thread is alloted and is ran asyncronously.Joked as NodeJS mode.
        * */

        Runnable monitor = new Runnable() {
            @Override
            public void run() {
                System.out.println("Total running threads: " + executorService.getActiveCount() + " ,Total completed threads: " + executorService.getCompletedTaskCount());
            }
        };

        scheduledThreadPoolExecutor.scheduleAtFixedRate(monitor,1,5, TimeUnit.SECONDS);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the nth. number value whose prime number needs to be printed");
            int inputNum = sc.nextInt();
            if(inputNum == 0) {
                System.out.println("Breaking out of the loop");
                break;
            }
            Runnable r = new Runnable() {

                @Override
                public void run() {
                    System.out.println(inputNum + " th. prime number is: " + PrimeUsingExecutorService.calculatePrime(inputNum));
                }
            };
            executorService.submit(r);
        }
        executorService.shutdown();
        System.out.println("Last line of the program");
    }

    public static int calculatePrime(int n) {
        int initialNumber = 1;
        int numberCount = 0;
        while (numberCount < n) {
            int i = 2;
            initialNumber++;
            for (i = 2; i <= initialNumber && initialNumber % i != 0; i++) {

            }
            if (i == initialNumber)
                numberCount++;
        }
        return initialNumber;
    }
}
