package chaoslab.PVZ.Plants;

import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;
/**
 * 
 * @author Liu.zhenxing
 * define Plant class
 */
abstract public class Plant extends GameObject implements Eatable{
	public Plant(String name, Particle particles[], int cost) {
		super(name, particles, cost);	
	}
	
	public void Attack(Zombie target){
		//do nothing as default
	}
	
	public void ate(int harmPoint){
		mHealthPoint -= harmPoint;
		if (mHealthPoint <= 0){
			mHealthPoint = 0;
			this.onDie();
		}
	}
}

