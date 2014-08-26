package com.dai.thread.concurrent.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable{
	private Lock lock = new ReentrantLock(true);
	private Random rnd = new Random();
	private int i=0;
	
	public void add(){
		lock.lock();
		try {
			this.i++;
			System.out.println(Thread.currentThread().getName()+" add " +i);
			Thread.sleep(rnd.nextInt(10));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}

	@Override
	public void run() {
		while (true) {
			this.add();
		}
	}
}
