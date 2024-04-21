package main;

import java.awt.Graphics;

public class Render {
	
	private GAME Game;

	
	public Render(GAME Game) {
		this.Game = Game;
	}
	
	public void render(Graphics G) {
		
		switch(GameStates.gameState) {
		
		case MENU:
			Game.getMenu().render(G);
			break;
		case PLAYING:
			Game.getPlaying().render(G);
			break;
		case EDIT:
			Game.getEditor().render(G);
			break;
		case QUIT:
			break;
		default:
			break;
		
		}
		
	}

}
