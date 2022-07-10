package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import FinalProject22.FinalProject22;
import FinalProject22.KeyHandler;

public class Enemy extends Entity {

	FinalProject22 fp22;
	KeyHandler keyH;
	
	private int shotSchedule;
	
	public Enemy(FinalProject22 fp22, KeyHandler keyH) {
		
		this.fp22 = fp22;
		this.keyH = keyH;
		
		setDefaultValues();
		getEnemyImage();
		
	}
	
	public void setDefaultValues() {
		
		//enemy
		x = 235 * fp22.scale;
		y = 60 * fp22.scale;
		//bullets
		projectileSpeed = 2 * fp22.scale;
		projectileX = 234 * fp22.scale;
		projectileY = 80 * fp22.scale;
		projectile = new ArrayList();
		
	}
	
	//Enemy images
	public void getEnemyImage() {
		
		try {
			
			enemyDef = ImageIO.read(getClass().getResourceAsStream("/enemy/enemy_default.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//BULLETS
	public void shoot() {
		
		Dot bullet = new Dot(projectileX, projectileY, projectileSpeed, 2 * fp22.scale, Color.white, fp22);
		projectile.add(bullet);
		
	}
	
	public void update() {
		
		spriteCounter++;
		
		for(int i = 0; i < projectile.size(); i++) {
			
			Dot b = (Dot)projectile.get(i);
			if(b.getVis() == true) {
				b.move();
			}
			else {
				projectile.remove(i);
			}
			
		}
		

		
		if(spriteCounter > 40) {
			shotSchedule = (int)(Math.random()*5);
			if(shotSchedule == 2) {
				shoot();
			}
			spriteCounter = 0;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		//drawing enemy
		BufferedImage image = null;
		
		image = enemyDef;
		
		g2.drawImage(image, x, y, fp22.charSize, fp22.charSize, null);
		
		//drawing bullets
		for(int i = 0; i < projectile.size(); i++) {
			
			Dot b = (Dot)projectile.get(i);
			b.draw(g2);
			
		}
		
	}
	
}
