package managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Helpers.LoadSave;
import enemies.Enemy;
import object.Tower;
import scenes.Playing;

public class TowerManager {
	
	private Playing playing;
	private BufferedImage[] towerImgs;

	private ArrayList<Tower> towers = new ArrayList<>();
	
	private int towerAmount = 0;
	
	public TowerManager(Playing playing) {
		
		this.playing = playing;
		
		BufferedImage atlas = LoadSave.getSpriteAtlas();
		
		towerImgs = new BufferedImage[3];
		
		for(int i = 0; i<3; i++) {
			towerImgs[i] = atlas.getSubimage((4+i)*32, 32, 32, 32);
		}

	}
	
	public void addTower(Tower selectedTower, int xPos, int yPos) {
		towers.add(new Tower(xPos, yPos,towerAmount++,selectedTower.getTowerType()));
	}
	
	public void update() {
		for(Tower t : towers) {
			t.update();
			attackEnemyIfItIsInRange(t);
		}
	}
	
	private void attackEnemyIfItIsInRange(Tower t) {
		
			for(Enemy e : playing.getEnemyManager().getEnemies()) {
				if(e.isAlive()) {
					if(isEnemyInRange(t,e)) {
						if(t.isCooldownOver()) {
							playing.shootEnemy(t,e);
							t.resetCooldown();
						}
					}else {
					}
				}
			}
	}

	private boolean isEnemyInRange(Tower t, Enemy e) {
		int range = Helpers.Utils.GetHypoDistance(t.getX(), t.getY(), e.getX(), e.getY());
		return range < t.getRange();
	}

	public void draw(Graphics g) {
		for (Tower t : towers) {
			g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
		}
	}
	
	public Tower getTowerAt(int x, int y) {
		
		for(Tower t : towers) {
			if(t.getX() == x && t.getY() == y) {
				return t;
			}
		}
		
		return null;
	}
	
	public BufferedImage[] getTowerImgs() {
		return towerImgs;
	}

	public void removeTower(Tower displayedTower) {
		for(int i = 0; i<towers.size(); i++) {
			if(towers.get(i).getId() == displayedTower.getId()) {
				towers.remove(i);
			}
		}
		
	}

	public void upgradeTower(Tower displayedTower) {
		for(Tower t : towers) {
			if(t.getId() == displayedTower.getId()) {
				t.upgradeTower();
			}
		}
	}

}
