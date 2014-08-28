package com.dai.thread.concurrent.condition;

public class Main {
	public static void main(String[] args) {
		BlockQueue queue = new BlockQueue(10);
		Put p = new Put(queue);
		Take t = new Take(queue);
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(p).start();
		new Thread(t).start();
	}
}
