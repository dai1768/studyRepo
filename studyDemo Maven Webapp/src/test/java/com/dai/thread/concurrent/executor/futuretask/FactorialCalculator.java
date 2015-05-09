package com.dai.thread.concurrent.executor.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer> {

	/**
	 * Number to calculate the factorial
	 */
	private Integer number;
	
	/**
	 * Constructor of the class. Initializes the attributes
	 * @param number Number to calculate the factorial
	 */
	public FactorialCalculator(Integer number){
		this.number=number;
	}
	/**
	 * Method called by the executor to execute this task and calculate the factorial of a
	 * number
	 */
	@Override
	public Integer call() throws Exception {
		int num, result =1;
		num = number.intValue();
		if(number == 0 || number ==1){
			result = 1;
		}else{
			for(int i =2; i<number ; i++){
				result *=i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.printf("%s :%d\n",Thread.currentThread().getName(),result);
		return result;
	}

}
