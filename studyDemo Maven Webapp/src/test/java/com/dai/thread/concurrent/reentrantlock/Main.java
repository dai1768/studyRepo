package com.dai.thread.concurrent.reentrantlock;

public class Main {

	public static void main(String[] args) {
		ReentrantLockDemo rt = new ReentrantLockDemo();
		for (int i = 0; i < 100; i++) {
			new Thread(rt,"name-"+i).start();
		}
	}
}
