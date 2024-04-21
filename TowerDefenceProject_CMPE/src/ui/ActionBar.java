package ui;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import Helpers.Constants.Towers;
import object.Tower;
import scenes.Playing;

public class ActionBar extends Bar {
	
	private MyButton bMenu;
	private Playing playing;
	private MyButton[] towerButtons;
	private Tower selectedTower;
	private Tower displayedTower;
	private DecimalFormat formatter;
	private boolean showTowerCost;
	private int gold = 100;
	private int towerCostType;
	private MyButton sellTower, upgradeTower;
	
	public ActionBar (int x, int y, int width, int height, Playing playing) {
		super(x,y,width,height);
		this.playing = playing;
		formatter = new DecimalFormat("0.0");
		initButtons();
	}
	
	private void initButtons() {
		bMenu = new MyButton("Menu", 2,642,100,30);
		towerButtons = new MyButton[3];
		
		int w = 50;
		int h = 50;
		int xStart = 106;
		int yStart = 642;
		int xOffset = (int) (w*1.1f);
		
		for(int i = 0; i<towerButtons.length; i++) {
			towerButtons[i] = new MyButton("", xStart+xOffset*i, yStart, w, h, i);
		}
		
		sellTower = new MyButton("Sell", 420, 702, 80,25);
		upgradeTower = new MyButton("Upgrade", 545, 702, 80,25);
		
	}
	
	private void drawButtons(Graphics G) {
		G.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bMenu.draw(G);
		
		for(MyButton b : towerButtons) {
			
			G.setColor(Color.LIGHT_GRAY);
			
			G.fillRect(b.x,b.y,b.width,b.height);
			
			G.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x,b.y,b.width,b.height,null);
			
			drawButtonFeedback(G,b);
		}
	}


	public void draw(Graphics G) {
		G.setColor(new Color(220, 150, 150));
		G.fillRect(x, y, width, height);
		
		drawButtons(G);
		
		drawDisplayedTower(G);
		
		drawWaveInfo(G);
		
		drawGoldAmount(G);
		
		if(showTowerCost) {
			drawTowerCost(G);
		}
	
	}
	
	private void drawTowerCost(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(280,642,120,50);
		g.setColor(Color.black);
		g.drawRect(280,642,120,50);
		g.drawString("" + getTowerCostName(), 285, 660);
		g.drawString("Cost: " + getTowerCostCost() + "g", 285, 685);
		
		if(isTowerCostMoreThanCurrentGold()) {
			g.setColor(Color.red);
			g.drawString("YOU ARE BROKEE!!!", 265, 718);
		}
	}

	private boolean isTowerCostMoreThanCurrentGold() {
		return getTowerCostCost() > gold;
	}

	private int getTowerCostCost() {
		return Helpers.Constants.Towers.GetTowerCost(towerCostType);
	}

	private String getTowerCostName() {
		return Helpers.Constants.Towers.getName(towerCostType);
	}

	private void drawGoldAmount(Graphics g) {
		g.drawString("Gold: " + gold, 105, 725);
		
	}

	private void drawWaveInfo(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
		drawWaveTimerInfo(g);
		drawEnemiesLeftInfo(g);
		drawWavesLeftInfo(g);
	}
	
	private void drawWavesLeftInfo(Graphics g) {
		int current = playing.getWaveManager().getWaveIndex();
		int size = playing.getWaveManager().getWaves().size();
		g.drawString("Wave " + (current + 1) + "/" + size, 425,770);
		
	}

	private void drawEnemiesLeftInfo(Graphics g) {
		int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
		g.drawString("Enemies Left: " + remaining, 425, 790);
		
	}

	private void drawWaveTimerInfo(Graphics g) {
		if(playing.getWaveManager().isWaveTimerStarted()) {
		
			float timeLeft = playing.getWaveManager().getTimeLeft();
			String formattedText = formatter.format(timeLeft);
			g.drawString("Time Left: "+ formattedText, 425, 750);
		}
		
	}

	private void drawDisplayedTower(Graphics g) {
		if(displayedTower != null) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(410,645,220,85);
			g.setColor(Color.black);
			g.drawRect(410,645,220,85);
			g.drawRect(420,650,50,50);
			g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50,50,null);
			g.setFont(new Font("Times New Roman", Font.BOLD, 15));
			g.drawString("" + Towers.getName(displayedTower.getTowerType()), 480, 660);
			g.drawString("ID: " + displayedTower.getId(), 480, 675);
			
			drawSelectedTowerBorder(g);
			drawDisplayedTowerRange(g);
			
			sellTower.draw(g);
			drawButtonFeedback(g,sellTower);
			
			if(displayedTower.getTier() < 3  && gold >= getUpgradeAmount(displayedTower)) {
				upgradeTower.draw(g);
				drawButtonFeedback(g,upgradeTower);
			}

			if(sellTower.isMouseOver()) {
				g.setColor(Color.red);
				g.drawString("Sell for: " + getSellAmount(displayedTower) + "g",480,695);
			}else if(upgradeTower.isMouseOver() && gold >= getUpgradeAmount(displayedTower)) {
				g.setColor(Color.green);
				g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "g",480,695);
			}
			
		}
		
	}

	private int getUpgradeAmount(Tower displayedTower) {
		return  (int) (Helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType()) * 0.3f);
	}

	private int getSellAmount(Tower displayedTower) {
		return Helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2;
	}

	private void drawDisplayedTowerRange(Graphics g) {
		g.setColor(Color.ORANGE);
		g.drawOval(displayedTower.getX()+ 16 -(int)(displayedTower.getRange()*2)/2,displayedTower.getY()+ 16-(int)(displayedTower.getRange()*2)/2,(int)(displayedTower.getRange()*2),(int)(displayedTower.getRange()*2));
	}

	private void drawSelectedTowerBorder(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(displayedTower.getX(),displayedTower.getY(),32,32);
	}

	public void displayTower(Tower t) {
		displayedTower = t;
	}
	
	public void mouseClicked(int x, int y) {
		if (bMenu.getBounds().contains(x,y))
			SetGameState(MENU);
		else {
			
			if(displayedTower != null) {
				if(sellTower.getBounds().contains(x, y)) {
					sellTowerClicked();
					return;
				}
				else if(upgradeTower.getBounds().contains(x, y) && displayedTower.getTier() < 3  && gold >= getUpgradeAmount(displayedTower)) {
					upgradeTowerClicked();
					return;
				}
			}
			
			
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					if(!isGoldEnoughForTower(b.getId()))
						return;
					selectedTower = new Tower(0,0,-1,b.getId());
					playing.setSelectedTower(selectedTower);
					return;
				}
			}
			
		}
		
	}

	private void upgradeTowerClicked() {
		playing.upgradeTower(displayedTower);
		gold -= getUpgradeAmount(displayedTower);
	}

	private void sellTowerClicked() {
		playing.removeTower(displayedTower);
		gold += Helpers.Constants.Towers.GetTowerCost(displayedTower.getTowerType())/2;
		displayedTower = null;
		
	}

	private boolean isGoldEnoughForTower(int towerType) {
		return gold >= Helpers.Constants.Towers.GetTowerCost(towerType);
	}

	public void mouseMoved(int x, int y) {
		bMenu.setMouseOver(false);
		showTowerCost = false;
		sellTower.setMouseOver(false);
		upgradeTower.setMouseOver(false);
		
		
		for(MyButton b : towerButtons) {
			b.setMouseOver(false);
		}
		
		if(bMenu.getBounds().contains(x,y)) {
			bMenu.setMouseOver(true);
		}else {
			
			if(displayedTower != null){
				if(sellTower.getBounds().contains(x, y)) {
					sellTower.setMouseOver(true);
					return;
				}
				else if(upgradeTower.getBounds().contains(x, y)&& displayedTower.getTier() < 3) {
					upgradeTower.setMouseOver(true);
					return;
				}
			}
			
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMouseOver(true);	
					showTowerCost = true;
					towerCostType = b.getId();
					return;
				}
			}
		}
	}

	public void mousePressed(int x, int y) {
		if(bMenu.getBounds().contains(x,y)) {
			bMenu.setMousePressed(true);
		}else {
			
			if(displayedTower != null) {
				if(sellTower.getBounds().contains(x, y)) {
					sellTower.setMousePressed(true);
					return;
				}
				else if(upgradeTower.getBounds().contains(x, y)&& displayedTower.getTier() < 3) {
					upgradeTower.setMousePressed(true);
					return;
			}
			
			for(MyButton b : towerButtons) {
				if(b.getBounds().contains(x, y)) {
					b.setMousePressed(true);
				}
			}
			
			}
			
		}
	}

	public void mouseReleased(int x, int y) {
		bMenu.resetBooleans();
		for(MyButton b : towerButtons) {
			b.resetBooleans();
		}
		sellTower.resetBooleans();
		upgradeTower.resetBooleans();
	}

	public void payForTowerType(int towerType) {
		this.gold -= Helpers.Constants.Towers.GetTowerCost(towerType);
		
	}

	public void addGold(int getReward) {
		this.gold += getReward;
	}
	
}
