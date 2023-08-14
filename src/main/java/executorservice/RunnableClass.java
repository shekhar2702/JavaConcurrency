package executorservice;

import com.example.concurrencypractice.ConcurrencyPracticeApplication;

public class RunnableClass implements Runnable{

    private int num;
    RunnableClass(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(this.num + "th. prime number is: " + this.calculatePrime(this.num));
    }

    public int calculatePrime(int n) {
        int initialNumber = 1;
        int numberCount = 0;
        while(numberCount < n) {
            int i = 2;
            initialNumber++;
            for(i = 2;i<=initialNumber && initialNumber % i!=0;i++) {

            }
            if(i == initialNumber)
                numberCount++;
        }
        return initialNumber;
    }
}
