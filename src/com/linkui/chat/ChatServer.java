package com.linkui.chat;

import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args){
		boolean started = false;
		try {
			ServerSocket ss = new ServerSocket(8888);
			started = true;
			while(started){
				boolean isConnected = false;
				Socket s = ss.accept();
				isConnected = true;
				System.out.println("A client is connected");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				while(isConnected){
					String str = dis.readUTF();
					System.out.println(str);
				}
				dis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
