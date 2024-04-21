package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.GAME;
import ui.MyButton;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {
	
	private MyButton bPlaying, bQuit, bEdit;

	public Menu(GAME Game) {
		super(Game);
		bPlaying = new MyButton("Play", 270, 206, 100, 30);
		bEdit = new MyButton("Edit", 270, 306, 100, 30);
		bQuit = new MyButton("Quit", 270, 406, 100, 30);
	}

	@Override
	public void render(Graphics G) {
		G.setColor(Color.DARK_GRAY);
		G.fillRect(0, 0, 640, 800);
		G.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bPlaying.draw(G);
		bQuit.draw(G);
		bEdit.draw(G);
		G.setColor(Color.white);
		G.setFont(new Font("Algerian", Font.BOLD, 25));
		G.drawString("REVENGE OF ATTILA", 195, 140);
		
	}

	@Override
	public void mouseClicked(int x, int y) {
		if(bPlaying.getBounds().contains(x,y)) {
			SetGameState(PLAYING);
		}
		if(bQuit.getBounds().contains(x,y)) {
			System.exit(0);
		}
		if(bEdit.getBounds().contains(x, y)) {
			SetGameState(EDIT);
		}

	}

	@Override
	public void mouseMoved(int x, int y) {
		bPlaying.setMouseOver(false);
		if(bPlaying.getBounds().contains(x,y)) {
			bPlaying.setMouseOver(true);
		} 
		
		bQuit.setMouseOver(false);
		if(bQuit.getBounds().contains(x,y)) {
			bQuit.setMouseOver(true);
		} 
		
		bEdit.setMouseOver(false);
		if(bEdit.getBounds().contains(x, y)) {
			bEdit.setMouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		
		if(bPlaying.getBounds().contains(x,y)) {
			bPlaying.setMousePressed(true);
		} 
		
	
		if(bQuit.getBounds().contains(x,y)) {
			bQuit.setMousePressed(true);
		} 
		
		if(bEdit.getBounds().contains(x, y)) {
			bEdit.setMousePressed(true);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {
		resetButtons();
	}

	private void resetButtons() {
		bPlaying.resetBooleans();
		bQuit.resetBooleans();
		bEdit.resetBooleans();
	}

	@Override
	public void mouseDragged(int x, int y) {
	}
	
}
