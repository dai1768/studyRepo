package com.dai.thread.workerthreadpattern;

public class Channel {
	private static final int MAX_REQUEST = 100;
	private final Request[] requestQueue;
	private int tail = 0 ;
	private int head = 0;
	private int count = 0;
	private final WorkerThread[] threadPool ;
	
	public Channel(int threads){
		this.requestQueue = new Request[MAX_REQUEST];
		this.threadPool = new WorkerThread[threads];
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i] = new WorkerThread("Worker-" + i, this);
		}
	}
	public synchronized void putRequest(Request request) {
		while(count >= requestQueue.length){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		requestQueue[tail] = request;
		tail = (tail +1) % requestQueue.length;
		count ++;
		this.notifyAll();
	}
	
	public synchronized Request takeRequest(){
		while(count <= 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Request request = requestQueue[head];
		head =  (head+1) % requestQueue.length;
		count--;
		notifyAll();
		return request;
	}
	
	public void startWorks() {
		for (int i = 0; i < threadPool.length; i++) {
			threadPool[i].start();
		}
	}
	

}
