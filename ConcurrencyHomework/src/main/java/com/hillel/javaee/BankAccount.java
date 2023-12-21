package com.hillel.javaee;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int id;
    private String ownerName;
    private double moneyBalance;
    private static final double AVAILABLE_OVERDRAFT = -600;
    ReentrantLock lock = new ReentrantLock();

    public BankAccount(int id, String ownerName, double moneyBalance) {
        this.id = id;
        this.ownerName = ownerName;
        this.moneyBalance = moneyBalance;
    }

    public BankAccount() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getMoneyBalance() {
        return moneyBalance;
    }

    public void setMoneyBalance(double moneyBalance) {
        this.moneyBalance = moneyBalance;
    }

    public void withdrawalMoney(double moneyQuantity) {
        double oldMoneyBalance = this.moneyBalance;
        this.moneyBalance -= moneyQuantity;
        if (this.moneyBalance < 0) {
            System.out.println("Your balance is too low for withdraw, operation declined");
            this.moneyBalance = oldMoneyBalance;
        }
    }

    public void payOnline(double moneyQuantity) {
        double oldMoneyBalance = this.moneyBalance;
        this.moneyBalance -= moneyQuantity;
        if (this.moneyBalance < AVAILABLE_OVERDRAFT) {
            System.out.println("Insufficient funds for payment, operation declined");
            this.moneyBalance = oldMoneyBalance;
        }
    }

    public void depositMoney(double moneyQuantity) {
        this.moneyBalance += moneyQuantity;
    }

    static class BankOperations implements Callable<Double> {

        private final int id;
        private final double depositCount;
        private final double withdrawalCount;
        private final BankAccount bankAccount;
        private double onlinePaymentPrice;
        private static final long MAX_WAIT = 4000;
        private static final long startTime = System.currentTimeMillis();

        public BankOperations(int id, double depositCount, double withdrawalCount, BankAccount bankAccount, double onlinePaymentPrice) {
            this.id = id;
            this.depositCount = depositCount;
            this.withdrawalCount = withdrawalCount;
            this.bankAccount = bankAccount;
            this.onlinePaymentPrice = onlinePaymentPrice;
        }

        public BankOperations(int id, double depositCount, double withdrawalCount, BankAccount bankAccount) {
            this.id = id;
            this.depositCount = depositCount;
            this.withdrawalCount = withdrawalCount;
            this.bankAccount = bankAccount;
        }


        @Override
        public Double call() throws Exception {
            bankAccount.lock.lockInterruptibly();
            int actionNumber = ThreadLocalRandom.current().nextInt(1, 4);
            System.out.println("Money balance before new operation: " + bankAccount.moneyBalance);
            return switch (actionNumber) {
                case 1 -> performWithdrawal();
                case 2 -> performOnlinePayment();
                case 3 -> performDeposit();
                default -> bankAccount.moneyBalance;
            };
        }


        public Double performWithdrawal() throws InterruptedException {
            double oldMoney = bankAccount.moneyBalance;
            System.out.println("Withdraw some money :" + withdrawalCount + " by Thread - " + this.id);
            bankAccount.withdrawalMoney(withdrawalCount);
            if (ThreadLocalRandom.current().nextInt(1, 5) == 4) {
                Thread.sleep(5000);
            } else Thread.sleep(2000);
            if((System.currentTimeMillis()-startTime)>MAX_WAIT){
                System.out.println("Thread " + id + " is interrupted due to long wait");
                System.out.println("---------------------------------------------------");
                bankAccount.moneyBalance = oldMoney;
                bankAccount.lock.unlock();
                Thread.currentThread().interrupt();
                return bankAccount.moneyBalance;
            }
            System.out.println("After withdrawal :" + bankAccount.moneyBalance + " by Thread - " + this.id);
            Thread.sleep(2000);
            if (ThreadLocalRandom.current().nextInt(1, 4) == 3) {
                System.out.println("    Withdraw some money again.");
                System.out.println("    Withdraw some money :" + withdrawalCount + " by Thread - " + this.id);
                bankAccount.withdrawalMoney(withdrawalCount);
                System.out.println("After withdrawal :" + bankAccount.moneyBalance + " by Thread - " + this.id);
            }
            System.out.println("---------------------------------------------------");
            bankAccount.lock.unlock();
            return bankAccount.moneyBalance;
        }

        public Double performOnlinePayment() throws InterruptedException {
            System.out.println("Online payment for :" + onlinePaymentPrice + " by Thread - " + this.id);
            bankAccount.payOnline(onlinePaymentPrice);
            System.out.println("After online payment :" + bankAccount.moneyBalance + " by Thread - " + this.id);
            System.out.println("---------------------------------------------------");
            Thread.sleep(2000);
            bankAccount.lock.unlock();
            return bankAccount.moneyBalance;
        }

        public Double performDeposit() throws InterruptedException {
            System.out.println("Deposit some money :" + depositCount + " by Thread - " + this.id);
            bankAccount.depositMoney(depositCount);
            System.out.println("After deposit :" + bankAccount.moneyBalance + " by Thread - " + this.id);
            System.out.println("---------------------------------------------------");
            Thread.sleep(2000);
            bankAccount.lock.unlock();
            return bankAccount.moneyBalance;
        }
    }
}


