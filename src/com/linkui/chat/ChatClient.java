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
		pack();
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		tf.addActionListener(new TFListener());
		this.setVisible(true);
	}
	
	private class TFListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tf.getText().trim();
			ta.setText(s);
			tf.setText("");
		}
		
	}
}
