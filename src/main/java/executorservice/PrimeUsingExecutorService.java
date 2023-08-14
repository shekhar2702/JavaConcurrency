package executorservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class PrimeUsingExecutorService {
    public static void main(String args[]) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

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
