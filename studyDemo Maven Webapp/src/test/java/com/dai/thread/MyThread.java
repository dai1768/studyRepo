package com.dai.thread;

public class MyThread extends Thread {
	int i=0;
	public void run(){
		System.out.println(++i);
	}
}
