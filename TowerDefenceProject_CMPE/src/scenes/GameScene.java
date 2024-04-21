package scenes;

import java.awt.image.BufferedImage;

import main.GAME;

public class GameScene {
	
	protected int animationIndex;
	protected int ANIMATION_SPEED = 25;
	protected int tick;
	
	protected GAME Game;
	public GameScene(GAME Game) {
		this.Game = Game;
		
	}
	
	public GAME getGame() {
		return Game;
	}
	
	protected boolean isAnimation(int spriteID) {
		return Game.getTileManager().isSpriteAnimation(spriteID);
	}
	
	protected void updateTick() {
		tick++;
		if(tick >= ANIMATION_SPEED) {
			tick = 0;
			animationIndex++;
			if(animationIndex  >= 4) {
				animationIndex = 0;
			}
		}
		
	}
	
	protected BufferedImage getSprite(int spriteId) {
		return Game.getTileManager().getSprite(spriteId);
	}
	
	protected BufferedImage getSprite(int spriteId, int animationIndex) {
		return Game.getTileManager().getAniSprite(spriteId, animationIndex);
	}


}
