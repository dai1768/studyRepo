package com.dai.thread.concurrent.condition;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Take implements Runnable {
	private BlockQueue queue;
	private Logger log = LoggerFactory.getLogger(getClass());
	public Take(BlockQueue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object obj;
				obj = queue.take();
				Thread.sleep(new Random().nextInt(3000));
				log.info("{} take obj : {}",Thread.currentThread().getName(),obj.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
