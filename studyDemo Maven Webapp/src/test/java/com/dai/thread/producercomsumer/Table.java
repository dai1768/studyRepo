package com.dai.thread.producercomsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Table {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String [] buffer;
	private int head;
	private int tail;
	private int count;
	
	public Table(){
		buffer = new String[3];
		this.head = 0;
		this.count = 0;
		this.tail = 0;
	}
	public Table(int count){
		buffer = new String[count];
		this.head = 0;
		this.count = 0;
		this.tail = 0;
		
	}
	public synchronized void put(String cake) throws InterruptedException{
		log.info("threadName:{},put {}",Thread.currentThread().getName(),cake);
		while(count > buffer.length){
			this.wait();
		}
		buffer[tail] = cake;
		count ++;
		tail  = ( tail+1 ) % buffer.length;
		this.notifyAll();
	}
	public synchronized String take() throws InterruptedException{
		while (count <= 0){
			this.wait();
		}
		String cake  = buffer[head];
		head = (head +1 ) % buffer.length;
		count --;
		this.notifyAll();
		log.info("theadName : {} ,take {} " , Thread.currentThread().getName(),cake);
		return cake;
	}
}
