package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
;

public class MainFrame extends JFrame {

	private static JPanel currentPanel;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		currentPanel = new JPanel();
		currentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(currentPanel);
		setTitle("Twitter API Scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 370);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnConfigurableOptions = new JMenu("Configurable Options");
		menuBar.add(mnConfigurableOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmApiSettings = new JMenuItem("API Settings");
		mntmApiSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().removeAll(); // removes existing panel
				revalidate();
				getContentPane().add(new UserCredentials()); // adds new user credentials panel
				revalidate();
				repaint();
			}
		});
		
		JMenuItem mntmUserManual = new JMenuItem("User Manual");
		mntmUserManual.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().removeAll(); // removes existing panel
				revalidate();
				try {
					getContentPane().add(new UserManual()); // adds user manual panel
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				} 
				revalidate();
				repaint();
			}
		});
		
		JMenuItem mntmWebsiteLink = new JMenuItem("Go to Website");
		mntmWebsiteLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String URL = "http://cen3031.caseybaer.com/";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
				}catch(Exception p){
					JOptionPane.showMessageDialog(null, p.getMessage());
				}
			}
		});
		
		mntmUserManual.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmUserManual);
		
		mntmWebsiteLink.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmWebsiteLink);
		
		mntmApiSettings.setHorizontalAlignment(SwingConstants.LEFT);
		mnConfigurableOptions.add(mntmApiSettings);

		JMenuItem mntmTweetEncryption = new JMenuItem("Tweet Encryption");
		mntmTweetEncryption.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().removeAll(); // removes existing panel
				revalidate();
				getContentPane().add(new TweetEncryptionPanel()); // adds new tweet encryption panel
				revalidate();
				repaint();
			}
		});
		mnConfigurableOptions.add(mntmTweetEncryption);
		File settingsFile = new File (util.TwitterParser.KEY_FILE);
		if (settingsFile.exists() && !settingsFile.isDirectory()) {// loads API settings from file if file exists
			util.TwitterParser.loadAPISettingsFromFile(this); // 
		}
		getContentPane().add(new MainControls());
		revalidate();
		repaint();
	}
}
