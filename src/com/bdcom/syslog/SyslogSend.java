package com.bdcom.syslog;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SyslogSend implements Runnable{
	public String ip;
	public int port=514;
	public long interval=100;//∫¡√Î
	public SyslogSend(String ip,int port,long interval)
	{
		this.ip=ip;
		this.port=port;
		this.interval=interval;
	}
	static byte[] syslogDatas = new byte[]{
			 0x3c, 0x31, 0x39, 0x30, 0x3e, 0x4a, 0x61, 0x6e,
			 0x20, 0x20, 0x31, 0x20, 0x30, 0x33, 0x3a, 0x33,
			 0x32, 0x3a, 0x34, 0x34, 0x20, 0x53, 0x77, 0x69,
			 0x74, 0x63, 0x68, 0x20, 0x25, 0x43, 0x4d, 0x44,
			 0x2d, 0x36, 0x2d, 0x45, 0x58, 0x45, 0x43, 0x55,
			 0x54, 0x45, 0x3a, 0x20, 0x60, 0x65, 0x6e, 0x6e,
			 0x61, 0x60, 0x20, 0x72, 0x65, 0x74, 0x75, 0x72,
			 0x6e, 0x20, 0x2d, 0x32, 0x2c, 0x20, 0x61, 0x64,
			 0x6d, 0x69, 0x6e, 0x28, 0x76, 0x74, 0x79, 0x20,
			 0x30, 0x2c, 0x20, 0x31, 0x37, 0x32, 0x2e, 0x31,
			 0x36, 0x2e, 0x32, 0x31, 0x2e, 0x31, 0x32, 0x35,
			 0x29, 0x2e, 0x20, 0x00};
	
	private  boolean goOnflag = true;
	public static void main(String[] args) {
		
		new Thread(new SyslogSend("172.16.21.125",514,100)).start();
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
			DatagramPacket dp = new DatagramPacket(syslogDatas, syslogDatas.length, destination , port);    
			try {  
				ds.send(dp);  //∑¢ÀÕ ˝æ›  
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
