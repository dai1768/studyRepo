package com.dai.thread.workerthreadpattern;

import java.util.Random;

public class Request {
	private final String name;
	private final int number;
	private static final Random random = new Random();
	public Request(String name, int number) {
		this.name = name;
		this.number = number;
	}
	public void execute(){
		System.out.println(Thread.currentThread().getName() +" execute " +this);
		try {
			Thread.sleep(random.nextInt(2000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Request [name=" + name + ", number=" + number + "]";
	}
	
}
