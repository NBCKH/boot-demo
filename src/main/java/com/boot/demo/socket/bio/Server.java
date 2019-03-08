package com.boot.demo.socket.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 网络编程的基本模型是C/S模型，即两个进程间的通信。
 * 服务端提供IP和监听端口，客户端通过连接操作向服务端监听的地址发起连接请求，通过三次握手连接，如果连接成功建立，双方就可以通过套接字进行通信。
 * 传统的同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口；
 * Socket负责发起连接操作。连接成功后，双方通过输入和输出流进行同步阻塞式通信。
 *
 * 简单的描述一下BIO的服务端通信模型：采用BIO通信模型的服务端，通常由一个独立的Acceptor线程负责监听客户端的连接，
 * 它接收到客户端连接请求之后为每个客户端创建一个新的线程进行链路处理，处理完成后，通过输出流返回应答给客户端，线程销毁。
 * 即典型的一请求一应答通信模型。
 *
 *  该模型最大的问题就是缺乏弹性伸缩能力，当客户端并发访问量增加后，服务端的线程个数和客户端并发访问数呈1:1的正比关系，
 *  Java中的线程也是比较宝贵的系统资源，线程数量快速膨胀后，系统的性能将急剧下降，随着访问量的继续增大，系统最终就死-掉-了。
 */
public class Server {

	final static int PROT = 8765;
	
	public static void main(String[] args) {
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(PROT);
			System.out.println(" server start .. ");
			//进行阻塞
			Socket socket = server.accept();
			//新建一个线程执行客户端的任务
			new Thread(new ServerHandler(socket)).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(server != null){
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			server = null;
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
