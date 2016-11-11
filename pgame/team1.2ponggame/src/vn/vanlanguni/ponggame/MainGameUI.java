/*
 * 
 * 
 * 
 * 
 */
package vn.vanlanguni.ponggame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * 
 * @author Invisible Man
 *
 */
public class MainGameUI extends JFrame{
	private static final int _HEIGHT = 500;
	private static final int _WIDTH = 500;
	private PongPanel pongPanel;
	
	//Implement paddle image
	public MainGameUI(){
		setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
		setLayout(new BorderLayout());
		setTitle("Pong Game - K21T Ltd.");
setDefaultCloseOperation(EXIT_ON_CLOSE);
		pongPanel = new PongPanel();
		getContentPane().add(pongPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

    public static void main(String[] args) {
       MainGameUI mainFrame = new MainGameUI();
       mainFrame.setVisible(true);
    }
}