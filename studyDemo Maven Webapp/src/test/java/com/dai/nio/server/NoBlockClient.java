package com.dai.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoBlockClient {
	private  final Logger log = LoggerFactory.getLogger(NoBlockClient.class);
	private String host = "localhost";
	private int port = 8889;
	private SocketChannel socketChannel;
	private Selector selector ;
	private ByteBuffer buf ;
	public NoBlockClient(){
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress(host,port));
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_WRITE |SelectionKey.OP_READ );
			buf = ByteBuffer.allocate(8*1024);
			int keyNum= -1;
			while(true){
				keyNum = selector.select();
				if(keyNum ==0){
					TimeUnit.SECONDS.sleep(1L);
					System.out.println("waiting ...");
					continue;
				}
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();
				while(it.hasNext()){
					SelectionKey selectionKey = it.next();
					if(selectionKey.isReadable()){
						System.out.println("do read..");
						doRead(selectionKey);
						SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
						buf.clear();
						int len =-1;
						byte[] dst = new byte[buf.capacity()];
						len = socketChannel.read(buf);
						buf.flip();
						buf.get(dst,0,buf.remaining());
						String s = new String(dst);
						log.info(s);
						if(s.equals("0")){
							socketChannel.close();
						}
						buf.clear();
						
						socketChannel.read(buf);
						
					}
					if(selectionKey.isWritable()){
						System.out.println(" do write");
						SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
						buf.clear();
						buf.put(("get message").getBytes());
						buf.flip();
						socketChannel.write(buf);
					}
					if(selectionKey.isConnectable()){
						System.out.println(" do connect");
						SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
						boolean isFinishedConn  = socketChannel.finishConnect();
						System.out.println("is finished :" +isFinishedConn);
						
						//socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE );
						//socketChannel.write(buf.put("aaa".getBytes()));
						//buf.clear();
					}
					it.remove();
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void doRead(SelectionKey selectionKey) {
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
		buf.clear();
		try {
			int len = 0;
			len =socketChannel.read(buf);
			buf.flip();
			byte[] dst = new byte[buf.remaining()]; 
			buf.get(dst);
			System.out.println(new String(dst));
			buf.clear();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("===========");
		
	}
	public static void main(String[] args) {
		NoBlockClient client = new NoBlockClient();
	}
}
