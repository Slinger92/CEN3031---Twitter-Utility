package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

public class UserManual extends JPanel{
	
	private BorderLayout blayout;
	private JTextArea ta;
	private JScrollPane sp;
	private Font font;
	private JButton BackButton, WebButton;
	
		public UserManual() throws URISyntaxException{
			
			setLayout(new BorderLayout());
			
			ta = new JTextArea(13, 69);
			add(ta, blayout.CENTER);
			
			sp = new JScrollPane(ta);
			add(sp);
			ta.setEditable(false);
			ta.setLineWrap(true);
			ta.setWrapStyleWord(true);
			
			font = new Font("Comic Sans", Font.BOLD, 13);
			ta.setFont(font);
			
			try{				
				ta.read(new FileReader("UserManual.txt"),null);
	    	
			}catch(IOException ioe){}
			
			BackButton = new JButton("Home");
			BackButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) { 
					removeAll();
					add(new MainControls());
					revalidate();
					repaint();
				}
			});
			
			WebButton = new JButton("Website");
			WebButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						String URL = "http://cen3031.caseybaer.com/";
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
					}catch(Exception p){
						JOptionPane.showMessageDialog(null, p.getMessage());
					}
				}
			});
			
			add(WebButton, blayout.NORTH);
			add(BackButton, blayout.SOUTH);
			
		}		
}