package semaphores;

import com.example.concurrencypractice.ConcurrencyPracticeApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

@SpringBootApplication
public class Semaphores {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);//number of threads that can access a critical section.Similar to locks but here multiple threads maybe given a chance to access the critical secton unlike locks where only 1 thread is allowed to access it.
        while (true) {
            System.out.println("Enter number to calculate nth. prime");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if(n == 0) {
                break;
            }
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(n + " th. prime number is: " + ConcurrencyPracticeApplication.calculatePrime(n));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    finally {
                        semaphore.release();
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
