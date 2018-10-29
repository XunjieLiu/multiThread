package multiThread;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadCooperation {
	private static Account account = new Account();

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		
		executor.shutdown();

	}
	
	public static class DepositTask implements Runnable{
		public void run() {
			try {
				while(true) {
					account.deposit((int)(Math.random() * 10 + 1));
					Thread.sleep(1000);
				}
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static class WithdrawTask implements Runnable{
		public void run() {
			while(true) {
				System.out.println("Withdraw task");
				account.withdraw((int)(Math.random() * 10 + 1));
			}		
		}
	}
	
	private static class Account{
		private static Lock myLock = new ReentrantLock();
		private static Condition newDeposit = myLock.newCondition();
		
		private int balance = 0;
		
		public int getBalance() {
			return balance;
		}
		
		public void withdraw(int amount) {
			myLock.lock();
			
			try {
				while(balance < amount) {
					System.out.println("Wait for deposit");
					newDeposit.await();
				}
				
				balance -= amount;
				System.out.println("Withdrew" + amount + "\t\tBalance: " + getBalance());
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			finally {
				myLock.unlock();
			}
		}
		
		public void deposit(int amount) {
			myLock.lock();
			
			try {
				balance =+ amount;
				System.out.println("Deposit: " + amount + "\t\tBalance: " + getBalance());
			}finally {
				myLock.unlock();
			}
		}
	}

}
