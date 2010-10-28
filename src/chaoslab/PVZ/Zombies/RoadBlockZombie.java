package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;

public class RoadBlockZombie extends Zombie{
	protected boolean mHasItem = true;
	protected int     mItemHealth = 50;
	
	public RoadBlockZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mItemHealth = 50;
		mHasItem = true;
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 10;
	}
	public void dropItem(){
		mHasItem = false;
	}
	@Override
	public void attacked( int harmPoint ){
		if(mHasItem){
			mItemHealth -= harmPoint;
		}else{
			mHealthPoint -= harmPoint;
		}
		//mStatus = GameConstants.ZOMBIE_ATTACKED;
		if(0 >= mItemHealth){
			dropItem();
		}
	}
}