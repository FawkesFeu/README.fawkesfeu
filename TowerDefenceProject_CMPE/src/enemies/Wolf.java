package enemies;

import static Helpers.Constants.Enemies.*;

import managers.EnemyManager;

public class Wolf extends Enemy {

	public Wolf(float x, float y, int ID,EnemyManager enemyManager) {
		super(x, y, ID, WOLF,enemyManager);
		
	}

}
