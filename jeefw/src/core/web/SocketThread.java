package core.web;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.jeefw.controller.collection.CollectionCnController;
import com.jeefw.service.collection.CollectionService;

public class SocketThread extends Thread {
	private static final Log log = LogFactory.getLog(SocketThread.class);
	Integer count = 0;
	private ServletContext servletContext;
	private ServerSocket serverSocket;

	public SocketThread(ServerSocket serverSocket, ServletContext servletContext) {
		this.servletContext = servletContext;
		// 从web.xml中context-param节点获取socket端口
		String port = this.servletContext.getInitParameter("socketPort");
		if (serverSocket == null) {
			try {
				log.info("--------------开启端口:" + port);
				this.serverSocket = new ServerSocket(Integer.parseInt(port));
//				this.serverSocket.setSoTimeout(10000);
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
				log.info("Server SocketThread start:" + count);
				if (socket != null) {
					BufferedWriter bufferedWriter = null;
					try {
						System.out.println("Accept the Client: " + socket);
						log.info("Accept the Client: " + socket);
						// 设置IO句柄

//						BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
//						 String a = null;
//						while((a=in.readLine())!=null){  
//				            System.out.println(a);  
//				            CollectionService collectionService = null;
//				            if(collectionService == null){
//				            	collectionService = new CollectionServiceImpl();
//				            }
//				            	collectionService.saveCollectionInfo(a);
//				        }  
						
						
						//字节流
						BufferedInputStream bis = new BufferedInputStream(
		                        socket.getInputStream());

		                DataInputStream dis = new DataInputStream(bis);
		                byte[] bytes = new byte[1]; // 一次读取一个byte
		                String ret = "";
		                
		                //获取service类
		                String path = this.getClass().getClassLoader().getResource("").getPath();
		                System.out.println("path = " + path);
		                String filepath = path + "applicationContext.xml";
		                ApplicationContext ctx = new FileSystemXmlApplicationContext(filepath);
		                CollectionService collectionService =(CollectionService) ctx.getBean("collectionService");
		                
		                while (dis.read(bytes) != -1) {
		                    ret += bytesToHexString(bytes) + " ";
		                    if (dis.available() == 0) { //一个请求
		                    	log.info("reiceve message:" + ret);
		                    	log.info("reiceve message(hexStringToString(ret)):" + hexStringToString(ret));
		                    	if(ret.indexOf("fa") > -1){
		                    		collectionService.saveCollectionInfo(ret);
		                    	}else if(hexStringToString(ret).indexOf("fa") > -1){
		                    		collectionService.saveCollectionInfo(hexStringToString(ret));
		                    	}else{
		                    		log.error("接收的信息不是以‘fa’开头的信息");
		                    	}
		                    }
		                }
						
						//写入输出
						bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						bufferedWriter.append("F5 10 01 09 01 12 05 31 32 33 34 02 01 B9 31");  
						bufferedWriter.flush();  
						bufferedWriter.close();  
					}
					finally
					{
						log.info("close the Server socket and the io.");
						
						socket.close();
						//closeServerSocket();
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
	
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }
}