package forkjoin;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class ForkJoin {
    public static void main(String[] args) {
        int[] inputArr = {113456,12,13,14,15,1,2,3,4,5,6,7,8};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CalculatePrimeTask calculatePrimeTask = new CalculatePrimeTask(inputArr,0,inputArr.length - 1);
        Integer result = forkJoinPool.invoke(calculatePrimeTask);
        System.out.println("Result is : " + result);
    }
}
