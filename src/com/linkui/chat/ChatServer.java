package com.linkui.chat;

import java.io.*;
import java.net.*;

public class ChatServer {
	public static void main(String[] args){
		try {
			ServerSocket ss = new ServerSocket(8888);
			while(true){
				Socket s = ss.accept();
				System.out.println("A client is connected");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = dis.readUTF();
				System.out.println(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
