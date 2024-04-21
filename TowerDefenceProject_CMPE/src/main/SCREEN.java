package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardListener;
import inputs.MyMouseListener;

public class SCREEN extends JPanel{
	
	private GAME Game;
	private Dimension size;

	private KeyboardListener keyboardListener;
	private MyMouseListener myMouseListener;
	
	
	public SCREEN (GAME Game) {
		this.Game = Game;
		
		size = new Dimension(640, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void initInputs() {
		myMouseListener = new MyMouseListener(Game);
		keyboardListener = new KeyboardListener(Game);
		addMouseListener(myMouseListener);
		addMouseMotionListener(myMouseListener);
		addKeyListener(keyboardListener);
		requestFocus();
		
	}

	public void paintComponent(Graphics G) {
		super.paintComponent(G);
		Game.getRender().render(G);
	}


	


}
