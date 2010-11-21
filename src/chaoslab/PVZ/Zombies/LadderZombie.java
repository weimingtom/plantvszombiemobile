package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.Zombies.Zombie;;

public class LadderZombie extends Zombie {
	
	public LadderZombie(String name, Particle particles[],Bitmap bitmap[],int cost,AbstractItem item){
		super(name,particles,bitmap,cost);
		mHealthPoint = 80;
		mItem = item;
	}
	@Override
	public void eat(Plant target){
		if(target != null&&mItem != null){
			target.setLadder(mItem);
			mItem.setPosition(target.getPosition().x, target.getPosition().y);
			mItem = null;
			mStatus = GameConstants.ZOMBIE_CLIMB;
		}else{
			if(mStatus != GameConstants.ZOMBIE_ATTACK && mStatus != GameConstants.ZOMBIE_CLIMB){
				mStatus = GameConstants.ZOMBIE_ATTACK;
				mTarget = target;
				mEatFrmCnt = 0;
			}
		}
	}
}
