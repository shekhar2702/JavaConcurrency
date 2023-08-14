package executorservice;

import com.example.concurrencypractice.ConcurrencyPracticeApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

@SpringBootApplication
public class CallableToReturnValueFromThreadUnlikeRun {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        /*
         * Unlike normal runnable which runs a run() method and returns nothing or void,
         * The callable is used to return a value and its not a one-way flow unlike runnable.
         * Runnable can be used with both threads and executorService but callable can only be used with
         * executorService.The returned value is received using Future object.
         * */
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>();
        while (true) {
            System.out.println("Enter the number");
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            if (n == 0)
                break;

            Callable<Integer> c = new Callable<>() {
                @Override
                public Integer call() throws Exception {
//                    System.out.println("Reached inside callable");
//                    Thread.sleep(2000);
//                    return "Returned from callable";
                    return ConcurrencyPracticeApplication.calculatePrime(n);
                }
            };

//The future object is going to be null or empty until the callable method is processed.
//            Future<String> result = executorService.submit(c);
//            System.out.println("Doing some work in main thread");
//            System.out.println(result.toString());
            futureList.add(executorService.submit(c));
        }
        Iterator<Future<Integer>> results = futureList.iterator();
        while (results.hasNext()) {
            Future<Integer> future = results.next();
            if(future.isDone()) {
                System.out.println("Prime number result: " + future.get());
                results.remove();
            }
        }
//        String r = result.get();//remember that this is a blocking call and it blocks the main thread.Hence using the above approach
        executorService.shutdown();
//        System.out.println(r);

    }

}
