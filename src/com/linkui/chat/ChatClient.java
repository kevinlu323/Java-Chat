package com.linkui.chat;

import java.awt.*;

public class ChatClient extends Frame{
	public static void main(String[] args){
		new ChatClient().launchFrame();
	}
	
	public void launchFrame(){
		this.setLocation(200, 300);
		this.setSize(300, 300);
		this.setVisible(true);
	}
}
