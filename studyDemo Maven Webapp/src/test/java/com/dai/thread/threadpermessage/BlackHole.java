package com.dai.thread.threadpermessage;

public class BlackHole {
	public static void enter(Object obj){
		System.out.println("step 1");
		magic(obj);
		System.out.println("step 2");
		synchronized (obj) {
			System.out.println("never reachec here");
		}
	}
	
	public static void magic (final Object obj){
		Thread thread = new Thread(){
			public void run(){
				synchronized (obj){
					try {
						this.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(this){
						this.setName("lock");
						this.notifyAll();
						System.out.println("locked");
					}
					while(true){
						
					}
				}
			}
		};
		synchronized(thread){
			thread.setName("");
			thread.start();
				while(thread.getName().equals("")){
					try {
						System.out.println("start wait");
						thread.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("end wait");
			
		}
	}
	public static void main(String[] args) {
		Object obj = new Object();
		enter(obj );
	}
}
