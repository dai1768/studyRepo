package com.dai.thread.producercomsumer;

import java.util.Random;

public class EaterThread extends Thread {
	private Table table;
	private final Random random;
	
	public EaterThread(String name , Table table){
		super(name);
		this.table = table;
		random = new Random();
	}
	
	public void run (){
		try {
			while(true){
				Thread.sleep(random.nextInt(1000));
				table.take();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
