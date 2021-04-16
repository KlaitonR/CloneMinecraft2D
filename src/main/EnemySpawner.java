package main;

import entities.Enemy;

public class EnemySpawner {

	public int interval = 60;
	public int curTime = 0;
	
	public void tick() {
		curTime++;
		if(curTime == interval) {
			curTime = 0;
			Enemy en = new Enemy(16, 16, 16, 16, 1, null);
			Game.entities.add(en);
		}
	}
	
}
