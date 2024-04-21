package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GAME;
import main.GameStates;

public class MyMouseListener implements MouseListener, MouseMotionListener {
	
    private GAME Game;
	public MyMouseListener(GAME Game) {
		this.Game = Game;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			Game.getMenu().mouseDragged(e.getX(), e.getY());
			break;
		case PLAYING:
			Game.getPlaying().mouseDragged(e.getX(), e.getY());
			break;
		case EDIT:
			Game.getEditor().mouseDragged(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			Game.getMenu().mouseMoved(e.getX(), e.getY());
			break;
		case PLAYING:
			Game.getPlaying().mouseMoved(e.getX(), e.getY());
			break;
		case EDIT:
			Game.getEditor().mouseMoved(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 ) {
			
			switch(GameStates.gameState) {
			case MENU:
				Game.getMenu().mouseClicked(e.getX(), e.getY());
				break;
			case PLAYING:
				Game.getPlaying().mouseClicked(e.getX(), e.getY());
				break;
			case EDIT:
				Game.getEditor().mouseClicked(e.getX(), e.getY());
				break;
			default:
				break;
			
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			Game.getMenu().mousePressed(e.getX(), e.getY());
			break;
		case PLAYING:
			Game.getPlaying().mousePressed(e.getX(), e.getY());
			break;
		case EDIT:
			Game.getEditor().mousePressed(e.getX(), e.getY());
			break;
		default:
			break;
		
		}

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		switch(GameStates.gameState) {
		case MENU:
			Game.getMenu().mouseReleased(e.getX(), e.getY());
			break;
		case PLAYING:
			Game.getPlaying().mouseReleased(e.getX(), e.getY());
			break;
		case EDIT:
			Game.getEditor().mouseReleased(e.getX(), e.getY());
			break;
		default:
			break;
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
