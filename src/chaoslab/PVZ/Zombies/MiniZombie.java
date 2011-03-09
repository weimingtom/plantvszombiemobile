package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Particle;

public class MiniZombie extends Zombie{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7990657649246583163L;

	public MiniZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mHealthPoint = 10;
		mMoveSpeed = 10.0f;
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 10;
	}

}