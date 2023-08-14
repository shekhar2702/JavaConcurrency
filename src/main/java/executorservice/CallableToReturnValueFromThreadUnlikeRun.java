package executorservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Reached inside callable");
                Thread.sleep(2000);
                return "Returned from callable";
            }
        };
//The future object is going to be null or empty until the callable method is processed.
        Future<String>result = executorService.submit(c);
        System.out.println("Doing some work in main thread");
        System.out.println(result.toString());
        String r = result.get();
        executorService.shutdown();
        System.out.println(r);

    }

}
