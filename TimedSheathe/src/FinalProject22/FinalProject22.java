package FinalProject22;

//Final Graphics Project
//Edward Hong, Block 4

import javax.swing.*;

import entity.Dot;
import entity.Enemy;
import entity.Player;

import java.awt.*;
import java.awt.event.*;

//start of class
public class FinalProject22 extends JPanel {
	
	//Data fields:
	
	public static int gameState = 0;
	
	public final int scale = 4;
	
	final int originalW = 320;
	final int originalH = 180;
	final int WIDTH = originalW * scale;
	final int HEIGHT = originalH * scale;
	
	public final int charSize = 60 * scale;

	private Timer t;
	KeyHandler keyH = new KeyHandler();
	
	Player player;
	Enemy enemy;
	
	public FinalProject22() {
		
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
		//Listens for keys
		addKeyListener(keyH);
		setFocusable(true);
		
		player = new Player(this, keyH);
		enemy = new Enemy(this, keyH);
		
		//Timer
		t = new Timer(1, new T1Listener());
		t.start();
		
	}
	
	//ActionListener class linked to the timer
	private class T1Listener implements ActionListener {
		
		//actionPerformed executes every set millisecond by Timer
		public void actionPerformed(ActionEvent e) {
			
			//Updates game information (key presses, etc.)
			update();
			
			//redraws the screen with updated information
			repaint();
			
		}
		
	}
	
	public void update() {
		
		player.update();
		enemy.update();
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		if(gameState == 0) {
			
			g2.setColor(Color.black);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			
			g2.setColor(Color.white);
			g2.setFont(new Font("SansSerif", Font.BOLD, 24 * scale));
			g2.drawString("Timed Sheathe", 75 * scale, 90 * scale);
			g2.setFont(new Font("SansSerif", Font.BOLD, 12 * scale));
			g2.drawString("Press Enter to Start", 100 * scale, 120 * scale);
			
			if(keyH.enterPress == true) {
				gameState = 1;
			}
			
		}
		if(gameState == 1) {
			
			player.draw(g2);
			enemy.draw(g2);
			
			g2.setColor(Color.white);
			g2.setFont(new Font("SansSerif", Font.PLAIN, 6 * scale));
			g2.drawString("SCORE: " + Dot.score, 20 * scale, 20 * scale);
			
			g2.dispose();
			
		}
		if(gameState == 2) {
			
			g2.setColor(Color.black);
			g2.fillRect(0, 0, WIDTH, HEIGHT);
			
			g2.setColor(Color.white);
			g2.setFont(new Font("SansSerif", Font.BOLD, 12 * scale));
			g2.drawString("SCORE: " + Dot.score, 20 * scale, 20 * scale);
			g2.drawString("GAME OVER", 120 * scale, 80 * scale);
			g2.drawString("Press [Enter] to Try Again", 100 * scale, 120 * scale);
			g2.drawString("Press [Escape] to Exit", 100 * scale, 135 * scale);
			
			if(keyH.enterPress == true) {
				Dot.score = 0;
				gameState = 1;
			}
			if(keyH.escPress == true) {
				System.exit(0);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		//Creates JFrame object and sets title bar
		JFrame frame = new JFrame("Timed Sheathe");
		
		FinalProject22 finalproject22 = new FinalProject22();
		frame.add(finalproject22);
		frame.pack();
		
		//Sets initial location of JFrame
		frame.setLocation(0, 0);
		//Allows JFrame to be closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Loads FinalProject22 JPanel into this JFrame (matches class name)
		frame.setContentPane(new FinalProject22());
		//Sets JFrame visible
		frame.setVisible(true);
		//Prevents resize
		frame.setResizable(false);
		
	}
	
}