package enemies;

import static Helpers.Constants.Enemies.*;

import managers.EnemyManager;

public class Bat extends Enemy {

	public Bat(float x, float y, int ID, EnemyManager enemyManager) {
		super(x, y, ID, BAT, enemyManager);
		
	}

}
