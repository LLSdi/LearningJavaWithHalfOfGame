package com.callableimpl;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RandomNumber implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("-------");
        Thread.sleep(1000);
        return new Random().nextInt(100);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RandomNumber randomNumber = new RandomNumber();
        FutureTask futureTask = new FutureTask(randomNumber);
        Thread thread = new Thread(futureTask);
        thread.start();

        Integer number = (Integer)(futureTask.get());
        System.out.println(number);
    }
}
