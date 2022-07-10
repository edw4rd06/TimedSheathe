package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import FinalProject22.FinalProject22;
import FinalProject22.KeyHandler;

public class Player extends Entity {

	FinalProject22 fp22;
	KeyHandler keyH;
	
	public static boolean swordBox = false;
	
	public Player(FinalProject22 fp22, KeyHandler keyH) {
		
		this.fp22 = fp22;
		this.keyH = keyH;
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		
		x = 25 * fp22.scale;
		y = 60 * fp22.scale;
		projectileSpeed = 20;
		
	}
	
	public void getPlayerImage() {
		
		try {
			
			playerDef = ImageIO.read(getClass().getResourceAsStream("/player/player_default.png"));
			
			unsheathe1 = ImageIO.read(getClass().getResourceAsStream("/player/player_unsheathe_1.png"));
			unsheathe2 = ImageIO.read(getClass().getResourceAsStream("/player/player_unsheathe_2.png"));
			unsheathe3 = ImageIO.read(getClass().getResourceAsStream("/player/player_unsheathe_3.png"));
			parry1 = ImageIO.read(getClass().getResourceAsStream("/player/player_parry_1.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void parrying() {
		
		spriteCounter++;
		
		if(spriteCounter <= 2) {
			spriteNum = 1;
		}
		if(spriteCounter > 2 && spriteCounter <= 4) {
			spriteNum = 2;
		}
		if(spriteCounter > 4 && spriteCounter <= 8) {
			spriteNum = 3;
		}
		if(spriteCounter > 8 && spriteCounter <= 16) {
			spriteNum = 4;
			swordBox = true;
		}
		if(spriteCounter > 16 && spriteCounter <= 18) {
			spriteNum = 3;
			swordBox = false;
		}
		if(spriteCounter > 18 && spriteCounter <= 20) {
			spriteNum = 2;
		}
		if(spriteCounter > 20 && spriteCounter <= 24) {
			spriteNum = 1;
			spriteCounter = 0;
			parry = false;
		}
		
	}
	
	public void update() {
		
		if(keyH.rightPress == true) {
			parry = true;
		}
		if(parry == true) {
			parrying();
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		if(parry == false) {
			image = playerDef;
			g2.drawImage(image, x, y, fp22.charSize, fp22.charSize, null);
		}
		
		if(parry == true) {
			if(spriteNum == 1) {
				image = unsheathe1;
			}
			if(spriteNum == 2) {
				image = unsheathe2;
			}
			if(spriteNum == 3) {
				image = unsheathe3;
			}
			if(spriteNum == 4) {
				image = parry1;
			}
			g2.drawImage(image, x, y, fp22.charSize * 2, fp22.charSize, null);
		}
		
	}
	
}
