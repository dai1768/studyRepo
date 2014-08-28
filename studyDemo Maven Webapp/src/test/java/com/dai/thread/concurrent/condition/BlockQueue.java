package com.dai.thread.concurrent.condition;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue {
	private Object [] buffer ;
	private int count;
	private int head;
	private int tail;
	private Lock  lock = new ReentrantLock();
	
	private Condition notFullCon  = lock.newCondition();
	private Condition notEmptyCon  = lock.newCondition();
	
	private int maxSize = 10;

	public BlockQueue(int maxSize) {
		super();
		this.maxSize = maxSize;
		this.buffer= new Object[maxSize];
		this.tail = 0;
		this.head = 0;
		this.count =0;
	}
	
	public void put(Object obj){
		lock.lock();
		try {
			while(count >= maxSize){
				notFullCon.await();
			}
			buffer[tail++] = obj;
			tail = tail % maxSize;
			count++;
			notEmptyCon.signalAll();
			System.out.println(this);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public Object take(){
		lock.lock();
		Object obj = null;
		try {
			while(count <= 0){
				notEmptyCon.await();
			}
			obj = buffer[head++];
			head = head % maxSize;
			count --;
			notFullCon.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return obj;
	}

	@Override
	public String toString() {
		return "BlockQueue [buffer=" + Arrays.toString(buffer) + ", count="
				+ count + ", head=" + head + ", tail=" + tail + "]";
	}
	
}
