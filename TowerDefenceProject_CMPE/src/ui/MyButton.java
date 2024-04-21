package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	
	public int x, y, width, height, id;
	private String text;
	private Rectangle bounds;
	private boolean mouseOver, mousePressed;
	
	public MyButton(String text, int x , int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = -1;
		
		this.bounds = new Rectangle(x, y, width, height);
	}
	
	public MyButton(String text, int x , int y, int width, int height, int id) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
		
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void draw(Graphics G) {
	
		drawBody(G);

		drawBorder(G);

		drawText(G);
	
	}
	
	private void drawBorder(Graphics G) {
		G.setColor(Color.BLACK);
		G.drawRect(x, y, width, height);
		if(mousePressed) {
			G.drawRect(x+1, y+1, width-2, height-2);
			G.drawRect(x+2, y+2, width-4, height-4);
		}
	}

	private void drawBody(Graphics G) {
		if(mouseOver) {
			G.setColor(Color.GRAY);
		}
		else {
			G.setColor(Color.WHITE);
		}

		G.fillRect(x, y, width, height);
		
	}

	private void drawText(Graphics G) {
		int w = G.getFontMetrics().stringWidth(text);
		int h = G.getFontMetrics().getHeight();
		G.drawString(text, (x - w/2 + width/2), (y + h/2 + height/2));
		
	}
	
	public void resetBooleans() {
		this.mouseOver = false;
		this.mousePressed = false;
	}
	
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	
	public boolean isMousePressed() {
		return mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getId() {
		return id;
	}
}
