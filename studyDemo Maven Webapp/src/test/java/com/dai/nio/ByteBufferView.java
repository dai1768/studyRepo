package com.dai.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteBufferView {
	public static final Logger log = LoggerFactory.getLogger(ByteBufferView.class);
	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		ByteOrder order = byteBuffer.order();
		log.info("byteBuffer order:{}",order);
		
		byteBuffer.order(ByteOrder.BIG_ENDIAN);
		CharBuffer charBuffer = byteBuffer.asCharBuffer();
		fillBuffer(charBuffer);
		readBuffer(charBuffer);
		byteBuffer.rewind();
		while(byteBuffer.hasRemaining()){
			System.out.println(byteBuffer.get());
		}
	}
	
	public static void fillBuffer(CharBuffer buffer){
		buffer.clear();
		char  c = 'A';
		int i = 0;
		while(buffer.hasRemaining()){
			buffer.put((char)(c + (++i)));
		}
	}
	
	public static void readBuffer(CharBuffer buffer){
		buffer.rewind();
		char[] charArr = new char[buffer.remaining()];
		int i = 0;
		while(buffer.hasRemaining()){
			charArr[i++] = buffer.get();
		}
		log.info("char array :{}",String.valueOf(charArr));
	}

}
