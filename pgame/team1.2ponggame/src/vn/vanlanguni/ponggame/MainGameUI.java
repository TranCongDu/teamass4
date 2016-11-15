package vn.vanlanguni.ponggame;

/*
 * 
 * 
 * 
 * 
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Invisible Man
 * 
 */
public class MainGameUI extends JFrame {
	private static final int _HEIGHT = 550;
	private static final int _WIDTH = 500;
	private PongPanel pongPanel;

	JLabel lblponggame = new JLabel("Pong Game");
	JLabel lblPlayer1 = new JLabel("Player 1:");
	JLabel lblPlayer2 = new JLabel("Player 2:");

	JTextField txtPlayer1 = new JTextField();
	JTextField txtPlayer2 = new JTextField();

	JButton bdongy = new JButton("Đồng Ý");
	int x = 10, y = 20, w = 100, h = 30;

	
	public MainGameUI() {
		setPreferredSize(new Dimension(_WIDTH, _HEIGHT));
		setLayout(new BorderLayout());
		// setSize(_WIDTH, _HEIGHT);
		setTitle("Pong Game - K21T Ltd.");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pongPanel = new PongPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		//add componment
		add(pongPanel);
		pongPanel.setBounds(0, 0, 500, 500);
		pongPanel.setVisible(false);
		SaveNamePlayer();
		aCtion();
		pack();

	}

	private Color backgroundColor = Color.BLACK;

	public void SaveNamePlayer() {
		setBackground(backgroundColor);
		add(txtPlayer1);
		add(txtPlayer2);

		add(lblponggame);
		add(lblPlayer1);
		add(lblPlayer2);
		add(bdongy);

		lblponggame.setBounds(x + 120, y, w + 200, h + 30);
		lblponggame.setFont(new Font("Pong Game", Font.BOLD, 36));
		lblPlayer1.setBounds(x, y + 110, w - 20, h);
		lblPlayer2.setBounds(x, y + 170, w - 20, h);
		txtPlayer1.setBounds(x + 90, y + 110, w + 140, h);
		txtPlayer2.setBounds(x + 90, y + 170, w + 140, h);
		bdongy.setBounds(x + 140, y + 230, w, h);

	}

	public void aCtion() {
		ActionListener bAc = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == bdongy) {
					pongPanel.setVisible(true);
					lblPlayer1.setVisible(false);
					lblPlayer2.setVisible(false);
					txtPlayer1.setVisible(false);
					txtPlayer2.setVisible(false);
					bdongy.setVisible(false);
					pongPanel.namePlayer1 = txtPlayer1.getText();
					pongPanel.namePlayer2 = txtPlayer2.getText();

				}
			}
		};
		KeyListener bKey = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
					pongPanel.showTitleScreen = false;
					pongPanel.playing = true;
				} else if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
					pongPanel.showTitleScreen = false;
					pongPanel.playing = false;
					pongPanel.gameOver = true;
				}
			}
		};
		bdongy.addKeyListener(bKey);
		bdongy.addActionListener(bAc);

	}

	public static void main(String[] args) {
		MainGameUI mainFrame = new MainGameUI();
		mainFrame.setVisible(true);
	}
}