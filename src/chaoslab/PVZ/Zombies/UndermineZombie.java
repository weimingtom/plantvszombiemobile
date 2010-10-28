package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;

public class UndermineZombie extends Zombie{
	public UndermineZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mStatus = GameConstants.ZOMBIE_UNDERGROUND;
		mPreStatus = GameConstants.ZOMBIE_UNDERGROUND;
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 10;
	}
	public void comeOut(){
		mStatus = GameConstants.ZOMBIE_MOVE;
	}
}