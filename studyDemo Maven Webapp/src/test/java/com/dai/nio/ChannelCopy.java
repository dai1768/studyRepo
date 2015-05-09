package com.dai.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChannelCopy {
	public static final Logger log = LoggerFactory.getLogger(ByteBufferView.class);
	
	public static void copy(ReadableByteChannel src,WritableByteChannel dst) throws IOException{
		long st = System.currentTimeMillis();
		long et = 0L;
		ByteBuffer buf = ByteBuffer.allocate(32*1024);
		while(src.read(buf)!=-1){
			buf.flip();
			dst.write(buf);
			buf.compact();
		}
		buf.flip();
		while(buf.hasRemaining()){
			dst.write(buf);
		}
		et = System.currentTimeMillis();
		log.info("copy finished cost :{}", et- st);
	}
	
	public static void copy2(ReadableByteChannel src ,WritableByteChannel dst) throws IOException{
		long st = System.currentTimeMillis();
		long et = 0L;
		ByteBuffer buf = ByteBuffer.allocate(32*1024);
		while(src.read(buf)!=-1){
			buf.flip();
			while(buf.hasRemaining()){
				dst.write(buf);
			}
			buf.clear();
		}
		et = System.currentTimeMillis();
		log.info("copy finished cost :{}", et- st);
	}
	public static void main(String[] args){
		File file = new File("H:/copy/Boot2.rar");
		File fileDest = new File("H:/copy/boot.rar");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel srcChannel = null;
		FileChannel dstChannel = null;
		try{
		 fis = new FileInputStream(file);
		 fos = new FileOutputStream(fileDest);
		 srcChannel = fis.getChannel();
		 dstChannel = fos.getChannel();
		copy(srcChannel, dstChannel);
		//copy2(srcChannel, dstChannel);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				dstChannel.close();
				srcChannel.close();
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
