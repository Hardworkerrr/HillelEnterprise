package com.hillel.javaee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BankAccount bankAccount = new BankAccount(1,"John Doe",1500);
        ExecutorService executor = Executors.newFixedThreadPool(7);
        List<Future<Double>> results = new ArrayList<>();
        results.add(executor.submit(new BankAccount.BankOperations(1,400,900,bankAccount,900)));
        results.add(executor.submit(new BankAccount.BankOperations(2,600,1200,bankAccount,1600)));
        results.add(executor.submit(new BankAccount.BankOperations(3,200,1100,bankAccount,1400)));
        results.add(executor.submit(new BankAccount.BankOperations(4,1000,700,bankAccount,200)));
        results.add(executor.submit(new BankAccount.BankOperations(5,1400,500,bankAccount, 700)));
        results.add(executor.submit(new BankAccount.BankOperations(6,750,600,bankAccount, 450)));
        results.add(executor.submit(new BankAccount.BankOperations(7,300,400,bankAccount, 500)));
        for (Future<Double> result:
             results) {
            while (!result.isDone()){
                Thread.sleep(10);
            }
        }
        System.out.println("Final money balance is : " + bankAccount.getMoneyBalance() + " in " + Thread.currentThread().getName());
        executor.shutdown();
    }
}
