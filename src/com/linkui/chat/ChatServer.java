package com.linkui.chat;

import java.io.*;
import java.net.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	Socket s = null;
	
	public static void main(String[] args){
		new ChatServer().start();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e){
			System.out.println("Port is in use by others");
			System.exit(-1);
		} catch (IOException e){
			e.printStackTrace();
		}
		
		while(started){
			try {
				s = ss.accept();
				System.out.println("A client is connected");
				Client c = new Client(s);
				new Thread(c).start();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					ss.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Client implements Runnable{
		private Socket s = null;
		private DataInputStream dis = null;
		private boolean isConnected = false;
		
		Client (Socket s){
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				isConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try{
			while(isConnected){
				String str = dis.readUTF();
				System.out.println(str);
			}
			} catch (EOFException e) {
				System.out.println("Client is disconnected.");
			} catch (IOException e){
				e.printStackTrace();
			} finally {
				try{
					if(dis != null) dis.close();
					if(s != null) s.close();
				}
				catch (IOException e1){
					e1.printStackTrace();
				}
			}
		}
	}
}
