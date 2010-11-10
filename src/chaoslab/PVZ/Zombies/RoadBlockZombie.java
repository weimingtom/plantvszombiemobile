package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.Particle;

public class RoadBlockZombie extends Zombie{
	
	public RoadBlockZombie(String name, Particle particles[],Bitmap bitmap[],int cost,AbstractItem item){
		super(name,particles,bitmap,cost);
		mHealthPoint = 50;
		mItem = item;
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 12;
	}
	/*
	 * only defensive item can be dropped
	 */
	public void dropItem(){
		if(mItem != null){
			mItem.droped(mPosition);
		}
		mItem = null;
	}
	@Override
	public void onHarmed( int harmPoint ){
		if(mItem != null){
			//if has item,ask for help
			mHealthPoint -= mItem.protect(harmPoint);
			if(mItem.getHealthPoint() <= 0){
				//item may die after this disaster
				dropItem();
			}
		}else{
			//else, suffer it yourself
			mHealthPoint -= harmPoint;
		}
		//check if he can survive
		if (mHealthPoint <= 0){
			mHealthPoint = 0;
			onDie();
		}
	}
	
	@Override
	public int getHealthPoint(){
		if(mItem != null){
			return mHealthPoint + mItem.getHealthPoint();
		}else{
			return mHealthPoint;
		}
	}
}