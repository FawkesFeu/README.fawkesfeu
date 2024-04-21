package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GAME;
import main.GameStates;

import static main.GameStates.*;

public class KeyboardListener implements KeyListener {

	private GAME Game;
	public KeyboardListener(GAME Game) {
		this.Game = Game;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(GameStates.gameState == EDIT) {
			Game.getEditor().keyPressed(e);
		}else if(GameStates.gameState == PLAYING) {
			Game.getPlaying().keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
