package main;

import javax.swing.JFrame;
import Helpers.LoadSave;
import managers.TileManager;
import scenes.Editing;
import scenes.Menu;
import scenes.Playing;


public class GAME extends JFrame implements Runnable{
	
	private SCREEN GameScreen;
	private Thread gameThread;
	private Render render;
	private Menu menu;
	private Playing playing;

	private Editing editing;
	
	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	private TileManager tileManager;
	
	public GAME () {
		
		tileManager = new TileManager(); 
		render = new Render(this);
		GameScreen = new SCREEN(this);
		menu = new Menu(this);
		playing = new Playing(this);
		editing = new Editing(this);
		
		createDefaultLevel();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(GameScreen);
		pack();
		setVisible(true);		
	}
	
	private void createDefaultLevel() {
		int[] arr = new int[400];
		
		for (int i = 0; i<arr.length; i++) {
			arr[i] = 0;
		}
		
		LoadSave.CreateLevel("new_level", arr);
		
	}

	private void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	private void updateGame() {
		switch(GameStates.gameState) {
		case EDIT:
			editing.update();
			break;
		case MENU:
			break;
		case PLAYING:
			playing.update();
			break;
		case QUIT:
			break;
		default:
			break;
		
		}
	}
	

	public static void main(String[] args) {
		
		GAME Game = new GAME();
		Game.GameScreen.initInputs();
		Game.start();
		
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		
		long lastTimeCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;
		
		long now;
		
		
		while (true) {
			
		now = System.nanoTime();
		
		if(now - lastFrame >= timePerFrame) {
			repaint();
			lastFrame = now;
			frames++;
		} else {
		}
		
		if(now - lastUpdate >= timePerUpdate) {
			updateGame();
			lastUpdate = now;
			updates++;
		} else {	
		}
		
		if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
			System.out.println("FPS: " + frames + " | Update Per Second: " + updates);
			frames = 0;
			updates = 0;
			lastTimeCheck = System.currentTimeMillis();
		}
		
		}
		
	}
	
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}
	
	public Editing getEditor() {
		return editing;
	}
	
	public TileManager getTileManager() {
		return tileManager;
	}
	
}
