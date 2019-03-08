package com.boot.demo.socket.bio2;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 为了改进这种一连接一线程的模型，我们可以使用线程池来管理这些线程，实现1个或多个线程处理N个客户端的模型（但是底层还是使用的同步阻塞I/O），
 * 通常被称为“伪异步I/O模型”。
 *
 * 我们知道，如果使用CachedThreadPool线程池（不限制线程数量），其实除了能自动帮我们管理线程（复用），看起来也就像是1:1的客户端：线程数模型，
 * 而使用FixedThreadPool我们就有效的控制了线程的最大数量，保证了系统有限的资源的控制，实现了N:M的伪异步I/O模型。
 * 但是，正因为限制了线程数量，如果发生大量并发请求，超过最大数量的线程就只能等待，直到线程池中的有空闲的线程可以被复用。
 * 而对Socket的输入流就行读取时，会一直阻塞，直到发生：
 *       有数据可读
 *       可用数据以及读取完毕
 *       发生空指针或I/O异常
 * 所以在读取数据较慢时（比如数据量大、网络传输慢等），大量并发的情况下，其他接入的消息，只能一直等待，这就是最大的弊端。
 * 而后面即将介绍的NIO，就能解决这个难题。
 *
 */
public class Server {

	final static int PORT = 8765;

	public static void main(String[] args) {
		ServerSocket server = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			server = new ServerSocket(PORT);
			System.out.println("server start");
			Socket socket = null;
			HandlerExecutorPool executorPool = new HandlerExecutorPool(50, 1000);
			while(true){
				socket = server.accept();
				executorPool.execute(new ServerHandler(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null){
				try {
					in.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(server != null){
				try {
					server.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
			server = null;				
		}
	
	}
	
	
}
