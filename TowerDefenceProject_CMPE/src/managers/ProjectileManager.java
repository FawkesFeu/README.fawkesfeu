package managers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Helpers.LoadSave;
import enemies.Enemy;
import object.Projectile;
import object.Tower;
import scenes.Playing;
import static Helpers.Constants.Towers.*;
import static Helpers.Constants.Projectiles.*;

public class ProjectileManager {
	
	private Playing playing;
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private BufferedImage[] proj_imgs, explo_imgs;
	private int proj_id = 0;
	private boolean drawExplo;
	private int exploTick, exploIndex;
	private Point2D.Float exploPos;
	
	public ProjectileManager(Playing playing) {
		this.playing = playing;
		
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		
		proj_imgs = new BufferedImage[3];
		
		for(int i = 0; i<3; i++) {
			proj_imgs[i] = atlas.getSubimage((7+i)*32, 32, 32, 32); 
		}
		
		explo_imgs = new BufferedImage[7];
		
		for(int i = 0; i < 7; i++) {
			explo_imgs[i] = atlas.getSubimage(i*32, 32*2, 32, 32);
		}
		
	}

	public void update() {
		for(Projectile p : projectiles) {
			if(p.isActive()) {
				p.move();
				if(isProjHittingEnemy(p)) {
					p.setActive(false);
					if(p.getProjectileType() == BOMB) {
						drawExplo = true;
						exploPos = p.getPos();
						explodeOnEnemies(p);
					}
				}else {
				}
			}
		}
		
		if(drawExplo) {
			exploTick++;
			if(exploTick >= 12) {
				exploTick = 0;
				exploIndex++;
				if(exploIndex >= 7) {
					exploIndex = 0;
					drawExplo = false;
				}
			}
		}
	}
	
	private void explodeOnEnemies(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			
			if(e.isAlive()) {
				float radius = 40.0f;
				float xDist = Math.abs(p.getPos().x - e.getX());
				float yDist = Math.abs(p.getPos().y - e.getY());
				float realDist = (float) Math.hypot(xDist, yDist);
				
				if(realDist <= radius) {
					e.hurt(p.getDmg());
				}
				
			}
		}
		
	}


	private boolean isProjHittingEnemy(Projectile p) {
		for(Enemy e : playing.getEnemyManager().getEnemies()) {
			if(e.isAlive()) {
				if (e.getBounds().contains(p.getPos())) {
					e.hurt(p.getDmg());
					if(p.getProjectileType() == CHAINS) {
						e.slow();
					}
					return true;
				}
			}
		}
		
		return false;
	}

	public void newProjectile(Tower t, Enemy e) {
		int type = getProjType(t);
		
		int xDist = (int) Math.abs(t.getX()-e.getX());
		int yDist = (int) Math.abs(t.getY()-e.getY());
		
		int totDist = xDist + yDist;
		
		float xPer = (float) xDist / totDist;
		
		float xSpeed = xPer * Helpers.Constants.Projectiles.GetSpeed(type);
		float ySpeed = Helpers.Constants.Projectiles.GetSpeed(type) - xSpeed;
		
		if(t.getX()>e.getX()) {
			xSpeed *= -1;
		}
		
		if(t.getY() > e.getY()) {
			ySpeed *= -1;
		}
		
		projectiles.add(new Projectile(t.getX()+16, t.getY()+16, xSpeed, ySpeed, t.getDmg(), proj_id++, type));
	}
	
	private int getProjType(Tower t) {
		switch(t.getTowerType()) {
		case ARCHER:
			return ARROW;
		case CANNON:
			return BOMB;
		case WIZARD:
			return CHAINS;
		}
		return 0;
	}

	public void draw(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		for(Projectile p : projectiles) {
			if(p.isActive()) {
				g.drawImage(proj_imgs[p.getProjectileType()], (int) p.getPos().x, (int) p.getPos().y, null);
			}
		}
		
		drawBomb(g2d);
	}


	private void drawBomb(Graphics2D g2d) {
		if(drawExplo) {
			g2d.drawImage(explo_imgs[exploIndex], (int) exploPos.x, (int) exploPos.y, null);
			
		}
	}
	
	

}
