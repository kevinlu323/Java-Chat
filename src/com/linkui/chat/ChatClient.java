package com.linkui.chat;

import java.awt.*;
import java.awt.event.*;

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
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		pack();
		this.setVisible(true);
	}
}
