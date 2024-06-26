package object;

import java.awt.geom.Point2D;

public class Projectile {
	
	private Point2D.Float pos;
	private float xSpeed, ySpeed;
	private int id, projectileType, dmg;
	private boolean active = true;
	
	public Projectile(float x, float y, float xSpeed, float ySpeed,int dmg, int id, int projectileType) {
		pos = new Point2D.Float(x,y);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.id = id;
		this.dmg = dmg;
		this.projectileType = projectileType;
	}
	
	public void move() {
		pos.x += xSpeed;
		pos.y += ySpeed;
	}

	public Point2D.Float getPos() {
		return pos;
	}

	public void setPos(Point2D.Float pos) {
		this.pos = pos;
	}

	public int getId() {
		return id;
	}


	public int getProjectileType() {
		return projectileType;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getDmg() {
		return dmg;
	}


}
