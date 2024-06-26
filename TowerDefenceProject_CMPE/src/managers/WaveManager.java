package managers;

import java.util.ArrayList;
import java.util.Arrays;

import events.Wave;
import scenes.Playing;

public class WaveManager {
	
	private Playing playing;
	private ArrayList<Wave> waves = new ArrayList<>();	
	private int enemySpawnTickLimit = 1 * 60;
	private int enemySpawnTick = enemySpawnTickLimit;
	private int enemyIndex, waveIndex;
	private int waveTickLimit = 5 * 60;
	private int waveTick = 0;
	private boolean waveStartTimer, waveTickTimerOver;

	public WaveManager(Playing playing) {
		this.setPlaying(playing);
		
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,1,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3,3,3,3,3,3,3,3))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,0,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,3,3,3,1,1,1,2,2,2,2,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,0,0,0,1,1,1,2,2,2,2,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,0,0,0,1,1,1,2,2,2,2,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,0,0,0,1,1,1,2,2,2,2,0,0,0,1,1,1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,2,2,2,2,2,2))));
		
	}
	
	public void update() {
		if(enemySpawnTick < enemySpawnTickLimit)
		enemySpawnTick++;
		
		if(waveStartTimer) {
			waveTick++;
			if(waveTick >= waveTickLimit) {
				waveTickTimerOver = true;
			}
		}
	}
	
	public void increaseWaveIndex() {
		waveIndex++;
		waveTickTimerOver = false;
		waveStartTimer = false;
	}
	
	public int getNextEnemy() {
		enemySpawnTick = 0;
		return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
	}
	
	public ArrayList<Wave> getWaves(){
		return waves;
	}

	public boolean isTimeForNewEnemy() {
		return enemySpawnTick >= enemySpawnTickLimit;
	}
	
	public boolean isThereMoreEnemiesInWave() {
		return enemyIndex < waves.get(waveIndex).getEnemyList().size();
	}

	public boolean isThereMoreWaves() {
		return waveIndex + 1 < waves.size();
	}

	public void startWaveTimer() {
		waveStartTimer = true;
	}

	public boolean isWaveTimerOver() {
		return waveTickTimerOver;
	}

	public void resetEnemyIndex() {
		enemyIndex = 0;
	}
	
	public int getWaveIndex() {
		return waveIndex;
	}
	
	public float getTimeLeft() {
		float ticksLeft = waveTickLimit - waveTick;
		return ticksLeft / 60.0f;
	}

	public boolean isWaveTimerStarted() {
		return waveStartTimer;
	}

	public Playing getPlaying() {
		return playing;
	}

	public void setPlaying(Playing playing) {
		this.playing = playing;
	}

}
