package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Particle;

public class SoccerZombie extends RoadBlockZombie{

	public SoccerZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mItemHealth = 100;
		mHasItem = true;
	}
}