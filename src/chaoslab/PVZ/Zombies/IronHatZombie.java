package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Particle;

public class IronHatZombie extends RoadBlockZombie{
	
	public IronHatZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mItemHealth = 80;
		mHasItem = true;
	}

}