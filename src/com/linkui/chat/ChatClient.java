package com.linkui.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient extends Frame{
	TextField tf = new TextField();
	TextArea ta = new TextArea();
	Socket s;
	DataOutputStream dos;
	
	public static void main(String[] args){
		new ChatClient().launchFrame();
	}
	
	public void launchFrame(){
		this.setLocation(200, 300);
		this.setSize(300, 300);
		this.add(tf, BorderLayout.SOUTH);
		this.add(ta, BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				disconnect();
				System.exit(0);
			}
			
		});
		tf.addActionListener(new TFListener());
		this.setVisible(true);
		this.connect();
	}
	
	public void connect(){
		try {
			s = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Connected to server");
		} catch (UnknownHostException e) {
			System.out.println("Cannot fing server!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try{
			dos.close();
			s.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private class TFListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tf.getText().trim();
			ta.setText(str);
			tf.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
