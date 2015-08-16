package com.linkui.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	Socket s = null;
	List<Client> clients = new ArrayList<>();

	public static void main(String[] args) {
		new ChatServer().start();
	}

	public void start() {
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e) {
			System.out.println("Port is in use by others");
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try { //try need to be added outside while() loop
			while (started) {
				s = ss.accept();
				System.out.println("A client is connected");
				Client c = new Client(s);
				new Thread(c).start();
				clients.add(c);
			}
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

	class Client implements Runnable {
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean isConnected = false;

		Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				isConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void send(String sentStr) {
			try {
				dos.writeUTF(sentStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while (isConnected) {
					String str = dis.readUTF();
					System.out.println(str);
					// Will not use Iterator here, Object lock is not needed.
					for (int i = 0; i < clients.size(); i++) {
						clients.get(i).send(str);
					}
				}
			} catch (EOFException e) {
				System.out.println("Client is disconnected.");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (s != null)
						s.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
