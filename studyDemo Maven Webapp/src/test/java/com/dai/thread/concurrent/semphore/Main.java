package com.dai.thread.concurrent.semphore;

public class Main {
	
	
	public static void main(String[] args) {
		PrintQueue queue = new PrintQueue();
		
		for (int i = 0; i <10; i++) {
			new Thread(new Job(queue)).start();
		}
	}
}
