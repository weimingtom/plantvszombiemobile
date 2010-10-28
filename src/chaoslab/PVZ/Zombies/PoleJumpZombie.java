package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;

public class PoleJumpZombie extends Zombie{
	protected boolean mHasPole = true; //has it gotten a jumping pole or not
	public PoleJumpZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 10;
	}
	@Override
	public void eat(Plant target){
		if(target.isDefCreature() && mHasPole){
			doJumping();
		}else{
			super.eat(target);
		}
	}
	public void doJumping(){
		mPosition.x += 30;
		mHasPole = false;
		mStatus = GameConstants.ZOMBIE_JUMP;
	}
	@Override
	public void update(){
		super.update();
		if(GameConstants.ZOMBIE_JUMP == mStatus){
			
		}
	}
}
