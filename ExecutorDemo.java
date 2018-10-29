package multiThread;

import java.util.concurrent.*;

public class ExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		executor.execute(new PrintChar('a', 50));
		executor.execute(new PrintChar('b', 30));
		executor.execute(new PrintNum(45));
		
		executor.shutdown();
	}

}
