package com.dai.thread.producercomsumer;

import java.util.Random;

public class MakerThread extends Thread {
	private Table table;
	private final Random random;
	private static int id = 0;
	
	public MakerThread(String name ,Table table, Long seed){
		super(name);
		random = new Random(seed);
		this.table = table;
	}
	
	public void run(){
		try {
			while(true){
				Thread.sleep(random.nextInt(1000));
				String cake = "cake No."+nextId() + " by "+getName();
				table.put(cake);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized int nextId(){
		return id++;
	}

}
