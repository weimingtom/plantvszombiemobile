package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.Particle;

public class IronHatZombie extends RoadBlockZombie{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4657755621039499270L;

	public IronHatZombie(String name, Particle particles[],Bitmap bitmap[],int cost,AbstractItem item){
		super(name,particles,bitmap,cost,item);
	}

}