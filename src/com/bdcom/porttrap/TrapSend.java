package com.bdcom.porttrap;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TrapSend implements Runnable{
	public String ip;
	public int port=162;
	public long interval=100;//����
	public TrapSend(String ip,int port,long interval)
	{
		this.ip=ip;
		this.port=port;
		this.interval=interval;
	}
	static byte[] shutdownDatas = new byte[]{
			0x30 ,(byte) 0x82 ,0x00 ,(byte)0xd6 ,0x02 ,0x01 

			,0x01 ,0x04 ,0x05 ,0x62 ,0x64 ,0x63 ,0x6f ,0x6d 
			,(byte)0xa7 ,(byte)0x82 ,0x00 ,(byte)0xc8 ,0x02 ,0x02 ,0x2c ,(byte)0x8e 

			,0x02 ,0x01 ,0x00 ,0x02 ,0x01 ,0x00 ,0x30 ,(byte)0x82 
			,0x00 ,(byte)0xba ,0x30 ,(byte)0x82 ,0x00 ,0x10 ,0x06 ,0x08 

			,0x2b ,0x06 ,0x01 ,0x02 ,0x01 ,0x01 ,0x03 ,0x00 
			,0x43 ,0x04 ,0x1c ,(byte)0xb5 ,0x19 ,(byte)0xd1 ,0x30 ,(byte)0x82 

			,0x00 ,0x17 ,0x06 ,0x0a ,0x2b ,0x06 ,0x01 ,0x06 
			,0x03 ,0x01 ,0x01 ,0x04 ,0x01 ,0x00 ,0x06 ,0x09 

			,0x2b ,0x06 ,0x01 ,0x06 ,0x03 ,0x01 ,0x01 ,0x05
			,0x03 ,0x30 ,(byte)0x82 ,0x00 ,0x0f ,0x06 ,0x0a ,0x2b 

			,0x06 ,0x01 ,0x02 ,0x01 ,0x02 ,0x02 ,0x01 ,0x01
			,0x06 ,0x02 ,0x01 ,0x06 ,0x30 ,(byte)0x82 ,0x00 ,0x1d 

			,0x06 ,0x0a ,0x2b ,0x06 ,0x01 ,0x02 ,0x01 ,0x02 
			,0x02 ,0x01 ,0x02 ,0x06 ,0x04 ,0x0f ,0x47 ,0x69 

			,0x67 ,0x61 ,0x45 ,0x74 ,0x68 ,0x65 ,0x72 ,0x6e 
			,0x65 ,0x74 ,0x30 ,0x2f ,0x32 ,0x30 ,(byte)0x82 ,0x00 

			,0x0f ,0x06 ,0x0a ,0x2b ,0x06 ,0x01 ,0x02 ,0x01
			,0x02 ,0x02 ,0x01 ,0x03 ,0x06 ,0x02 ,0x01 ,0x06 

			,0x30 ,(byte)0x82 ,0x00 ,0x0f ,0x06 ,0x0a ,0x2b ,0x06 
			,0x01 ,0x02 ,0x01 ,0x02 ,0x02 ,0x01 ,0x07 ,0x06 

			,0x02 ,0x01 ,0x02 ,0x30 ,(byte)0x82 ,0x00 ,0x0f ,0x06 
			,0x0a ,0x2b ,0x06 ,0x01 ,0x02 ,0x01 ,0x02 ,0x02 

			,0x01 ,0x08 ,0x06 ,0x02 ,0x01 ,0x02 ,0x30 ,(byte)0x82
			,0x00 ,0x1a ,0x06 ,0x0a ,0x2b ,0x06 ,0x01 ,0x06 

			,0x03 ,0x01 ,0x01 ,0x04 ,0x03 ,0x00 ,0x06 ,0x0c 
			,0x2b ,0x06 ,0x01 ,0x04 ,0x01 ,(byte)0x82 ,(byte)0xa7 ,0x47 
			,0x01 ,(byte)0x81 ,0x40 , 0x00
	};
	
	private  boolean goOnflag = true;
	public static void main(String[] args) {
		
		new Thread(new TrapSend("172.16.21.125",162,100)).start();
	}
	@Override
	public void run() {
		DatagramSocket ds = null; 
		InetAddress destination = null ;
		try {
			ds = new DatagramSocket(8999);
			destination = InetAddress.getByName(ip);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		while( goOnflag ){
			DatagramPacket dp = new DatagramPacket(shutdownDatas, shutdownDatas.length, destination , port);    
			try {  
				ds.send(dp);  //��������  
				Thread.sleep(interval);
			} catch (Exception e) {  
				e.printStackTrace();
			}  
		}
		ds.close();
	}
	public void stop(){
		goOnflag=false;
	}
	
}
