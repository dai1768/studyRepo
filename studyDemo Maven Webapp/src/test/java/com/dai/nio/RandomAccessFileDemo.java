package com.dai.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
	public static void main(String[] args) {
		RandomAccessFile raf = null;
		File file = new File("H:/copy/b.txt");
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			raf = new RandomAccessFile(file,"rw");
			raf.writeInt(257);
			raf.seek(3);
			System.out.println(raf.getFilePointer());
			System.out.println((byte)raf.readByte());
			System.out.println("==========");
			raf.seek(0);
			int data  = -1;
			while((data = raf.read())!=-1){
				System.out.print(data);
			}
			;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
