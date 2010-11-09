package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.Particle;

public class SoccerZombie extends RoadBlockZombie{

	public SoccerZombie(String name, Particle particles[],Bitmap bitmap[],int cost,AbstractItem item){
		super(name,particles,bitmap,cost,item);
	}
}