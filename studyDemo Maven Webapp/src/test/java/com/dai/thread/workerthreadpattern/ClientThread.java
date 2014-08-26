package com.dai.thread.workerthreadpattern;

import java.util.Random;

public class ClientThread extends Thread {
	private final Channel channel ;
	private static final Random random  = new Random();
	public ClientThread(String name ,Channel channel){
		super(name);
		this.channel = channel;
	}
	
	public void run(){
		for (int i = 0; true; i++) {
			Request request = new Request(this.getName(),i);
			channel.putRequest(request);
			try {
				Thread.sleep(random.nextInt(2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
