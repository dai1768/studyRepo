package com.dai.thread.producercomsumer;

public class Main {
	public static void main(String[] args) {
		Table table = new Table(3);
		MakerThread mt1 = new MakerThread("makeThread-1", table, 1000L);
		MakerThread mt2 = new MakerThread("makeThread-2", table, 2000L);
		MakerThread mt3 = new MakerThread("makeThread-3", table, 6000L);
		
		EaterThread et1 = new EaterThread("eatThread-1", table);
		EaterThread et2 = new EaterThread("eatThread-1", table);
		EaterThread et3 = new EaterThread("eatThread-1", table);
		EaterThread et4 = new EaterThread("eatThread-1", table);
		EaterThread et5 = new EaterThread("eatThread-1", table);
		
		mt1.start();
		mt2.start();
		mt3.start();
		et1.start();
		et2.start();
		et3.start();
		et4.start();
		et5.start();
		
		
	}

}
