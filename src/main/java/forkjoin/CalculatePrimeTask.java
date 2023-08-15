package forkjoin;

import com.example.concurrencypractice.ConcurrencyPracticeApplication;

import java.util.concurrent.RecursiveTask;

public class CalculatePrimeTask extends RecursiveTask<Integer> {

    int[] arr;
    int start;
    int end;
    CalculatePrimeTask(int[] arr,int start,int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if(start == end) {
            return ConcurrencyPracticeApplication.calculatePrime(arr[start]);
        }
        if(end - start == 1) {
            return ConcurrencyPracticeApplication.calculatePrime(arr[start]) + ConcurrencyPracticeApplication.calculatePrime(arr[end]);
        }

        int mid = (start + end) / 2;
        CalculatePrimeTask calculatePrimeTask1 = new CalculatePrimeTask(arr,start,mid);
        CalculatePrimeTask calculatePrimeTask2 = new CalculatePrimeTask(arr,mid + 1,end);
        invokeAll(calculatePrimeTask1,calculatePrimeTask2);
        return calculatePrimeTask1.join() + calculatePrimeTask2.join();

    }
}
