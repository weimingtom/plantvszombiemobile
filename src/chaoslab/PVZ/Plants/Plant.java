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
	public static final int PLANT_STATE_WAVE 			= 0;
	public static final int PLANT_STATE_ATTACK 			= 0;
	public static final int PLANT_STATE_SPECIAL_ACTION	= 0;
	

	protected static final int WAVE_INTERVAL = 1;
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

