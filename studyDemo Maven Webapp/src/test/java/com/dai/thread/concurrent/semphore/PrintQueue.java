package com.dai.thread.concurrent.semphore;

import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class PrintQueue {
	private Semaphore semaphore= new Semaphore(1);
	
	public void print(String doc){
		try {
			semaphore.acquire();
			for (char c : doc.toCharArray()) {
				System.out.print(c);
				Thread.sleep(100L);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
}
