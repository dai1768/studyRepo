package com.dai.nio.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoBlockServerChannel {
	private  final Logger log = LoggerFactory.getLogger(NoBlockServerChannel.class);
	public static  final int PORT = 8889;
	private ServerSocketChannel serverChannel;
	private Selector selector ;
	private ByteBuffer buf ;
	private int num = 0;
	
	public NoBlockServerChannel(){
		try {
			serverChannel =  ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			ServerSocket serverSocket = serverChannel.socket();
			serverSocket.bind(new InetSocketAddress(PORT));
			selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT );
			int selectNum = -1;
			buf = ByteBuffer.allocate(8*1024);
			System.out.println("listening port:"+PORT);
			while(true){
				selectNum = selector.select();
				if(selectNum == 0){
					TimeUnit.SECONDS.sleep(1L);
					System.out.println("wait...");
					continue;
				}
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey > it = selectedKeys.iterator();
				while( it.hasNext()){
					SelectionKey selectionKey = it.next();
					if(selectionKey.isAcceptable()){
						log.info(" accepting");
						doAccept(selectionKey);
					}
					if(selectionKey.isConnectable()){
						log.info(" connecting");
						doConnect(selectionKey);
					}
					if(selectionKey.isReadable()){
						log.info(" doReading");
						doRead(selectionKey);
					}
					if(selectionKey.isWritable()){
						log.info(" doWriting");
						doWrite(selectionKey);
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
		SocketChannel channel =(SocketChannel)selectionKey.channel();
		buf.clear();
		try {
			channel.register(selector, SelectionKey.OP_WRITE);
			int len =-1;
			byte[] dst = new byte[buf.capacity()];
			len = channel.read(buf);
			buf.flip();
			buf.get(dst,0,buf.remaining());
			String s = new String(dst);
			log.info(s);
			System.out.println(buf.position());
			buf.compact();
        			
		} catch (IOException e) {
			try {
				channel.finishConnect();
				channel.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	private void doWrite(SelectionKey selectionKey) throws IOException {
		SocketChannel socketChannel =(SocketChannel)selectionKey.channel();
		socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
		buf.clear();
		buf.put(("hello " + (num ++) +"").getBytes());
		buf.flip();
		socketChannel.write(buf);
		log.info("socketChannel validOps :{} " ,socketChannel.validOps());	
		selectionKey.cancel();
	}

	private void doConnect(SelectionKey selectionKey) {
		// TODO Auto-generated method stub
		
	}

	private void doAccept(SelectionKey selectionKey) {
		log.info("accpet ing...");
		try {
			SocketChannel socketChannel = serverChannel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
		//	buf.put(("hello " + (num ++) +"").getBytes());
		//	buf.flip();
		//	socketChannel.write(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//(SocketChannel)selectionKey.channel();
	}
	public static void main(String[] args) {
		NoBlockServerChannel server = new NoBlockServerChannel();
	}
}
