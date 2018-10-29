package multiThread;


public class threadTest {

	public static void main(String[] args) {
		// Creat tasks
		Runnable char1 = new PrintChar('a', 50);
		Runnable char2 = new PrintChar('b', 30);
		Runnable nums = new PrintNum(45);
		
		// Creat threads
		Thread thread1 = new Thread(char1);
		Thread thread2 = new Thread(char2);
		Thread thread3 = new Thread(nums);
		
		// Æô¶¯£¡£¡£¡
		thread1.start();
		thread2.start();
		thread3.start();
	}
	
	

}

class PrintChar implements Runnable{
	private char charToPrint;
	private int times;
	
	public PrintChar(char c, int t) {
		this.charToPrint = c;
		this.times = t;
	}
	
	public void run() {
		for(int i = 0; i < times; i ++) {
			System.out.print(charToPrint);
		}
	}
}

class PrintNum implements Runnable{
	private int lastNum;
	
	public PrintNum(int n) {
		this.lastNum = n;
	}
	
	public void run() {
		for(int i = 0; i < lastNum; i ++) {
			System.out.print(" " + i);
		}
	}
}
