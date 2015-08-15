package com.linkui.chat;

import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args){
		boolean started = false;
		ServerSocket ss = null;
		Socket s = null;
		DataInputStream dis = null;
		try {
			ss = new ServerSocket(8888);
		} catch (BindException e){
			System.out.println("Port is in use by others");
		} catch (IOException e){
			e.printStackTrace();
		}
		try{
			started = true;
			while(started){
				boolean isConnected = false;
				s = ss.accept();
				isConnected = true;
				System.out.println("A client is connected");
				dis = new DataInputStream(s.getInputStream());
				while(isConnected){
					String str = dis.readUTF();
					System.out.println(str);
				}
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
