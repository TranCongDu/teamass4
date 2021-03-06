package vn.vanlanguni.ponggame;

/*
 * PONG GAME REQUIREMENTS
 * This simple "tennis like" game features two paddles and a ball, 
 * the goal is to defeat your opponent by being the first one to gain 3 point,
 *  a player gets a point once the opponent misses a ball. 
 *  The game can be played with two human players, one on the left and one on 
 *  the right. They use keyboard to start/restart game and control the paddles. 
 *  The ball and two paddles should be red and separating lines should be green. 
 *  Players score should be blue and background should be black.
 *  Keyboard requirements:
 *  + P key: start
 *  + Space key: restart
 *  + W/S key: move paddle up/down
 *  + Up/Down key: move paddle up/down
 *  
 *  Version: 0.5
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * 
 * @author Invisible Man
 * 
 */
public class PongPanel extends JPanel implements ActionListener, KeyListener {
	String namePlayer1;
	String namePlayer2;
	
	ImageIcon ballnormal = new ImageIcon(new ImageIcon("image/ballnomarl.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	ImageIcon balldep = new ImageIcon("image/balldep.png");
	ImageIcon ball1 = new ImageIcon(new ImageIcon("image/ball1.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	JRadioButton rdb1 = new JRadioButton("Wold Cup 2014");
	JRadioButton rdb2 = new JRadioButton("Wold Cup 2010");
	JRadioButton rdb3 = new JRadioButton("Wold Cup 2006");
	
	static int numberball = 0;

	
	private static int kc=0;
	private static int hieu=0;
	private static int kc2=0;
	private static int hieu2=0;
	private static final long serialVersionUID = -1097341635155021546L;

	boolean showTitleScreen = true;
	boolean playing;
	boolean gameOver;

	/** Background. */
	private Color backgroundColor = Color.BLACK;

	/** State on the control keys. */
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;

	/** The ball: position, diameter */
	private int ballX = 250;
	private int ballY = 250;
	private int diameter = 40;
	private int ballDeltaX = -1;
	private int ballDeltaY = 3;

	/** Player 1's paddle: position and size */
	private int playerOneX = 0;
	private int playerOneY = 250;
	private int playerOneWidth = 10;
	private int playerOneHeight = 50;

	/** Player 2's paddle: position and size */
	private int playerTwoX = 475;
	private int playerTwoY = 250;
	private int playerTwoWidth = 10;
	private int playerTwoHeight = 50;

	/** Speed of the paddle - How fast the paddle move. */
	private int paddleSpeed = 5;

	/** Player score, show on upper left and right. */
	private int playerOneScore;
	private int playerTwoScore;

	ImageIcon background = new ImageIcon("image/a.jpg");
	ImageIcon manhinhbatdau = new ImageIcon("image/b.jpg");
	ImageIcon gameover = new ImageIcon("image/c.jpg");

	/** Construct a PongPanel. */
	public PongPanel() {
		setBackground(backgroundColor);
		Sound.play("Sounds/StartGame.wav");
		
		//add combonment

		// listen to key presses
		setFocusable(true);
		addKeyListener(this);

		// call step() 60 fps
		Timer timer = new Timer(1000 / 60, this);
		timer.start();
	}

	/** Implement actionPerformed */
	public void actionPerformed(ActionEvent e) {
		step();
	}

	/** Repeated task */
	public void step() {

		if (playing) {
			
			/* Playing mode */

			// move player 1
			// Move up if after moving, paddle is not outside the screen
			if (upPressed && playerTwoY - paddleSpeed > 0) {
				playerTwoY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (downPressed && playerTwoY + playerTwoHeight + paddleSpeed < getHeight()) {
				playerTwoY += paddleSpeed;
			}

			// move player 2
			// Move up if after moving paddle is not outside the screen
			if (wPressed && playerOneY - paddleSpeed > 0) {
				playerOneY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (sPressed && playerOneY + playerOneHeight + paddleSpeed < getHeight()) {
				playerOneY += paddleSpeed;
			}

			/*
			 * where will the ball be after it moves? calculate 4 corners: Left,
			 * Right, Top, Bottom of the ball used to determine whether the ball
			 * was out yet
			 */
			int nextBallLeft = ballX + ballDeltaX;
			int nextBallRight = ballX + diameter + ballDeltaX;
			// FIXME Something not quite right here
			int nextBallTop = ballY + ballDeltaY;
			int nextBallBottom = ballY + diameter + ballDeltaY;

			// Player 1's paddle position
			int playerOneRight = playerOneX + playerOneWidth;
			int playerOneTop = playerOneY;
			int playerOneBottom = playerOneY + playerOneHeight;

			// Player 2's paddle position
			float playerTwoLeft = playerTwoX;
			float playerTwoTop = playerTwoY;
			float playerTwoBottom = playerTwoY + playerTwoHeight;

			// ball bounces off top and bottom of screen
			if (nextBallTop < 0 || nextBallBottom > getHeight()) {
				ballDeltaY *= -1;
Sound.play("Sounds/pong.wav");
			}
			kc = playerOneHeight/5;
			// will the ball go off the left side?
			if (nextBallLeft < playerOneRight) {
				// is it going to miss the paddle?
				if (nextBallTop > playerOneBottom || nextBallBottom < playerOneTop) {

					playerTwoScore++;
					ballDeltaY = 3;
					// Player 2 Win, restart the game
					if (playerTwoScore == 3) {
						playing = false;
						gameOver = true;
						Sound.play("Sounds/gameOver.wav");
					}
					ballX = 250;
					ballY = 250;
				} else {
					//cham vao cho khac nhau thi bong se di theo huong khac
					
					hieu = nextBallTop+(diameter/2);
					if(ballDeltaY > 0){

							if ((hieu > playerOneTop)&&( hieu <= (playerOneTop+(kc))) || (hieu > (playerOneTop+(kc*4)) && (hieu <playerOneBottom))){
								ballDeltaY = 5;
							}
							
							if(((hieu > (playerOneTop+(kc))) && (hieu  < (playerOneTop+(kc*2))))
									||
								((hieu > (playerOneTop+(kc*3))) && (hieu  < (playerOneTop+(kc*4))))){
								ballDeltaY = 4;
							}
							if(((hieu > (playerOneTop+(kc*2))) && (hieu  < (playerOneTop+(kc*3))))){
								ballDeltaY = 3;
							}
							
						
						
					}
						else{

							if ((hieu > playerOneTop)&&( hieu <= (playerOneTop+(kc))) || (hieu > (playerOneTop+(kc*4)) && (hieu <playerOneBottom))){
								ballDeltaY = -5;
							}
							
							if(((hieu > (playerOneTop+(kc))) && (hieu  < (playerOneTop+(kc*2))))
									||
								((hieu > (playerOneTop+(kc*3))) && (hieu  < (playerOneTop+(kc*4))))){
								ballDeltaY = -4;
							}
							if(((hieu > (playerOneTop+(kc*2))) && (hieu  < (playerOneTop+(kc*3))))){
								ballDeltaY = -3;
							}
							
					}
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
					Sound.play("Sounds/hit.wav");
				
				}
			}

			// will the ball go off the right side?
			if (nextBallRight > playerTwoLeft) {
				// is it going to miss the paddle?
				if (nextBallTop > playerTwoBottom || nextBallBottom < playerTwoTop) {

					playerOneScore++;
					ballDeltaY = 3;
					// Player 1 Win, restart the game
					if (playerOneScore == 3) {
						playing = false;
						gameOver = true;
Sound.play("Sounds/gameOver.wav");
					}
					ballX = 250;
					ballY = 250;
				} else {
					//cham vao banh se bat lai khac
					kc2 = playerTwoHeight/5;
					hieu = nextBallTop+(diameter/2);
					if(ballDeltaY > 0){
						
							if ((hieu > playerTwoTop)&&( hieu <= (playerTwoTop+(kc2))) || (hieu > (playerTwoTop+(kc2*4)) && (hieu <playerOneBottom))){
								ballDeltaY = 5;
							}
							
							if(((hieu > (playerTwoTop+(kc2))) && (hieu  < (playerTwoTop+(kc2*2))))
									||
								((hieu > (playerTwoTop+(kc2*3))) && (hieu  < (playerTwoTop+(kc2*4))))){
								ballDeltaY = 4;
							}
							if(((hieu > (playerTwoTop+(kc2*2))) && (hieu  < (playerTwoTop+(kc2*3))))){
								ballDeltaY = 3;
							}
							
						
						
					}
						else{

							if ((hieu > playerTwoTop)&&( hieu <= (playerTwoTop+(kc2))) || (hieu > (playerTwoTop+(kc2*4)) && (hieu <playerOneBottom))){
								ballDeltaY = -5;
							}
							
							if(((hieu > (playerTwoTop+(kc2))) && (hieu  < (playerTwoTop+(kc2*2))))
									||
								((hieu > (playerTwoTop+(kc2*3))) && (hieu  < (playerTwoTop+(kc2*4))))){
								ballDeltaY = -4;
							}
							if(((hieu > (playerTwoTop+(kc2*2))) && (hieu  < (playerTwoTop+(kc2*3))))){
								ballDeltaY = -3;
							}
							
					}
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					ballDeltaX *= -1;
					Sound.play("Sounds/hit.wav");
				}
			}

			// move the ball
			ballX += ballDeltaX;
			ballY += ballDeltaY;
			
		}

		// stuff has moved, tell this JPanel to repaint itself
		repaint();
	}

	/** Paint the game screen. */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (showTitleScreen) {
			Sound.play("Sounds/StartGame.wav");
			playerOneScore = 0;
			playerTwoScore = 0;
			ballDeltaY = 3;
			
			add(rdb1);
			add(rdb2);
			add(rdb3);
			//button group
			ButtonGroup btg = new ButtonGroup();
			btg.add(rdb1);
			btg.add(rdb2);
			btg.add(rdb3);
			//set Location
			rdb1.setBounds(50, 10, 130, 30);
			rdb2.setBounds(200, 10, 130, 30);
			rdb3.setBounds(350, 10, 130, 30);
			if(rdb1.isSelected()){
				numberball =0;
			}
			else{
				if(rdb2.isSelected()){
					numberball=1;
				}
				else{
					if(rdb3.isSelected()){
						numberball=2;
					}
				}
			}
			if(numberball==0){
				g.drawImage(ballnormal.getImage(), ballX, ballY, diameter, diameter, null);
				
			}
			else{ if(numberball == 1){
					g.drawImage(ball1.getImage(), ballX, ballY, diameter, diameter, null);
				}
			else{if(numberball == 2){
						g.drawImage(balldep.getImage(), ballX, ballY, diameter, diameter, null);
					}
				}
			}
			
			/* Show welcome screen */
		
			// Draw game title and start message
			g.drawImage(manhinhbatdau.getImage(), 0, 0, 500, 500, null);
			g.setColor(Color.YELLOW);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
			g.drawString("Pong Game", 30, 400);

			// FIXME Wellcome message below show smaller than game title
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
			g.drawString("Press 'P' to play.", 40, 480);

			g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

		} else if (playing) {
			rdb1.setBounds(0, 0, 0, 0);
			rdb2.setBounds(0, 0, 0, 0);
			rdb3.setBounds(0, 0, 0, 0);
			g.drawImage(background.getImage(), 0, 0, 500, 500, null);
			/* Game is playing */
			if (numberball == 0) {
				g.drawImage(ballnormal.getImage(), ballX, ballY, diameter, diameter, null);
			} else if (numberball == 1) {
				g.drawImage(balldep.getImage(), ballX, ballY, diameter, diameter, null);
			} else if (numberball == 2) {
				g.drawImage(ball1.getImage(), ballX, ballY, diameter, diameter, null);
			}
			// set the coordinate limit
			int playerOneRight = playerOneX + playerOneWidth;
			int playerTwoLeft = playerTwoX;

			// draw dashed line down center
			for (int lineY = 0; lineY < getHeight(); lineY += 50) {
				g.setColor(Color.GREEN);
				g.drawLine(250, lineY, 250, lineY + 25);

			}

			// draw "goal lines" on each side
			g.setColor(Color.GREEN);
			g.drawLine(playerOneRight, 0, playerOneRight, getHeight());
			g.drawLine(playerTwoLeft, 0, playerTwoLeft, getHeight());

			// draw the scores
			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.DIALOG, Font.ITALIC, 36));
			g.drawString(String.valueOf(playerOneScore), 100, 100); // Player 1
			g.setColor(Color.GREEN);
			g.drawString(namePlayer1, 50, 50); // score
			g.setColor(Color.BLUE);
			g.drawString(String.valueOf(playerTwoScore), 400, 100); // Player 2
			g.setColor(Color.GREEN);
			g.drawString(namePlayer2, 350, 50); // score

	//		g.drawString(String.valueOf(ballDeltaY), 100, 400); // Player 1
		
			
			//draw the ball



			// draw the paddles
			g.fillRect(playerOneX, playerOneY, playerOneWidth, playerOneHeight);
			g.fillRect(playerTwoX, playerTwoY, playerTwoWidth, playerTwoHeight);
		} else if (gameOver) {

			/* Show End game screen with winner name and score */
			g.drawImage(gameover.getImage(), 0, 0, 500, 500, null);
			// Draw scores
			// TODO Set Blue color
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.setColor(Color.YELLOW);
			g.drawString(String.valueOf(playerOneScore), 100, 100);
			g.drawString(String.valueOf(playerTwoScore), 400, 100);

			// Draw the winner name
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			if (playerOneScore > playerTwoScore) {
				g.setColor(Color.BLUE);
				g.drawString(namePlayer1 + " win", 200, 200);
			} else {
				g.setColor(Color.BLUE);
				g.drawString(namePlayer2 + " win", 200, 200);
			}

			// Draw Restart message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
			g.setColor(Color.YELLOW);
			g.drawString("Press 'SPACE' to restart game.", 180, 280);
			// TODO Draw a restart message
			
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (showTitleScreen) {
			if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
				showTitleScreen = false;
				playing = true;

			}
		} else if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			}
		} else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) {
			gameOver = false;
			showTitleScreen = true;
			playerOneY = 250;
			playerTwoY = 250;
			ballX = 250;
			ballY = 250;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
	}

}
