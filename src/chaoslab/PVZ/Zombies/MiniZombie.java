package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Particle;

public class MiniZombie extends Zombie{
	
	public MiniZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mHealthPoint = 10;
		mMoveSpeed = 10.0f;
	}

}