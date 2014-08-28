package com.dai.thread.concurrent.condition;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Put implements Runnable {
	private BlockQueue queue;
	private Logger log = LoggerFactory.getLogger(getClass());
	public Put(BlockQueue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object obj = new Random().nextInt(9999)+"";
				Thread.sleep(new Random().nextInt(2000));
				queue.put(obj);
				log.info("{} put obj : {}",Thread.currentThread().getName(),obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
