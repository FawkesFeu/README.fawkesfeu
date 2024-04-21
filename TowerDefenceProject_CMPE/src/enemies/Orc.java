package enemies;

import static Helpers.Constants.Enemies.*;

import managers.EnemyManager;

public class Orc extends Enemy{

	public Orc(float x, float y, int ID,EnemyManager enemyManager) {
		super(x, y, ID, ORC,enemyManager);
	}
	
	

}
