package core.web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class SocketThread extends Thread {
	private static final Logger log = Logger.getLogger(SocketThread.class);
	Integer count = 0;
	private ServletContext servletContext;
	private ServerSocket serverSocket;

	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		if (serverSocket == null) {
			try {
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		try {
			Integer count = 0;
			while (!this.isInterrupted()) {
				// MyServer.rerfresh();

				Socket socket = serverSocket.accept();
				count++;
				System.out.println();
				log.info("Server SocketThread start:" + count);
				if (socket != null) {
					try {
						System.out.println("Accept the Client: " + socket);
						log.info("Accept the Client: " + socket);
						// 设置IO句柄

						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						 String a = null;
						while((a=in.readLine())!=null){  
				            System.out.println(a);  
				        }  
						
						//写入输出
						Writer writer = new OutputStreamWriter(socket.getOutputStream());  
				        writer.append("I am server message!!!");  
				        writer.flush();  
				        writer.close();  

					}
					finally
					{
						System.out.println("close the Server socket and the io.");
						log.info("close the Server socket and the io.");
						
						socket.close();
						serverSocket.close();
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
			log.error("SocketThread err:" + ex.getMessage());
		}
	}

	public void closeServerSocket() {
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}

		} catch (Exception ex) {
			System.out.println("SocketThread err:" + ex.getMessage());
			log.error("SocketThread err:" + ex.getMessage());
		}
	}
}