package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.GameConstants;
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
		mStand = GameConstants.STAND_PLANT;
	}
	
	public abstract void attack(ArrayList<Zombie> zombies);
	
	public void ate(int harmPoint){
		mHealthPoint -= harmPoint;
		if (mHealthPoint <= 0){
			mHealthPoint = 0;
			this.onDie();
		}
	}
}

