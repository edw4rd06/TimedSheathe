package entity;

//Animation Dot Class
//Edward Hong, Block 4
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import FinalProject22.FinalProject22;
import FinalProject22.KeyHandler;

public class Dot extends Entity {
	
	FinalProject22 fp22;
	KeyHandler keyH;

	private double x;
	private double y;
	private double diameter;
	private double radius;
	private Color color;
	private double speed;
	
	private int deflectionY;
	private int speedY;
	
	public static int score = 0;
	public static boolean gameOver = false;
	
	//"hitboxes" for player, enemy, sword
	int hitBoxPlayer;
	int hitBoxEnemy;
	int hitBoxSword;
	
	public Dot(double newX, double newY, double newSpeed, double newDiameter, Color newColor, FinalProject22 fp22) {
		this.x = newX;
		this.y = newY;
		this.speed = newSpeed;
		this.diameter = newDiameter;
		this.radius = newDiameter/2;
		this.color = newColor;
		
		this.fp22 = fp22;
		
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		
		projectileVis = true;
		
		hitBoxPlayer = 72 * fp22.scale;
		hitBoxSword = 95 * fp22.scale;
		
		speedY = 0;
		
	}
	
	public int getProjX() {
		return (int)x;
	}
	
	public int getProjY() {
		return (int)y;
	}
	
	public int getProjSpeed() {
		return (int)speed;
	}
	
	public boolean getVis() {
		return projectileVis;
	}
	
	public void move() {
		
		x -= speed;
		y -= speedY;
		
		if(Player.swordBox == true) {
			if(isCollisionSword()) {
				speed = -speed;
				score++;
				deflectionY = (int)(Math.random()*2);
				if(deflectionY == 0) {
					speedY = 2;
				}
				else if(deflectionY == 1) {
					speedY = -3;
				}
			}
		}
		
		if(isCollisionPlayer()) {
			projectileVis = false;
			fp22.gameState = 2;
		}
		
		if(y <= 0 || y >= 120*fp22.scale) {
			projectileVis = false;
		}
		
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int)(x - radius),(int)(y - radius),(int)diameter,(int)diameter);
	}
	
	public boolean isCollisionPlayer() {
		
		if(x - radius <= hitBoxPlayer) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isCollisionSword() {
		
		if(x - radius == hitBoxSword) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	public boolean isCollisionEnemy() {
		
		if(x + radius >= hitBoxEnemy) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}