package com.linkui.chat;

import java.awt.*;

public class ChatClient extends Frame{
	TextField tf = new TextField();
	TextArea ta = new TextArea();
	
	public static void main(String[] args){
		new ChatClient().launchFrame();
	}
	
	public void launchFrame(){
		this.setLocation(200, 300);
		this.setSize(300, 300);
		this.add(tf, BorderLayout.SOUTH);
		this.add(ta, BorderLayout.NORTH);
		pack();
		this.setVisible(true);
	}
}
