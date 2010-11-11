package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;

import java.lang.Math;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;

public class PoleJumpZombie extends Zombie{
	protected boolean mHasPole = true; //has it gotten a jumping pole or not
	protected int     MIN_JUMP;
	protected int     MAX_JUMP;
	protected int     mJumpCnt = 0;
	protected final double PI = 3.14159265;
	protected final float mJumpRadius = 50.0f;
	protected Position mTempPosition;        
	
	public PoleJumpZombie(String name, Particle particles[],Bitmap bitmap[],int cost,AbstractItem item){
		super(name,particles,bitmap,cost);
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		MIN_JUMP = 3;
		MAX_JUMP = 4;
		mMaxBitmap = 10;
		mItem = item;
		mTempPosition = new Position(0,0);
	}
	@Override
	public void eat(Plant target){
		if(mStatus == GameConstants.ZOMBIE_JUMP || mStatus == GameConstants.ZOMBIE_ATTACK){
			return;
		}
		if(target.isDefCreature() && mHasPole){
			Jump();
		}else{
			super.eat(target);
		}
	}
	@Override
	public void moving(){
		float moveFactor = mIsSlowed ? 0.5f : 1.0f;
		if(mHasPole){
			mPosition.x += 2 * mMoveDirection.x * mMoveSpeed * moveFactor;
		}else{
			mPosition.x += mMoveDirection.x * mMoveSpeed * moveFactor;
		}
	}
	//jump along sin arc track
	public void doJumping(){
		mPosition.x = mTempPosition.x + mMoveDirection.x *mJumpRadius*(1.0f - (float)Math.cos(45.0*mJumpCnt*PI/180.0));
		mPosition.y = mTempPosition.y - (float)(mJumpRadius*Math.sin(45.0*mJumpCnt*PI/180.0));
		if(mJumpCnt == 4){
			mStatus = GameConstants.ZOMBIE_MOVE;
		}
	}
	@Override
	public void update(){
		super.update();
		if(GameConstants.ZOMBIE_JUMP == mStatus){
			mJumpCnt ++;
			doJumping();
		}
	}
	@Override
	public void updateStatus(){
		super.updateStatus();
		switch(mStatus){
		case GameConstants.ZOMBIE_JUMP:
			mTempPosition.x = mPosition.x;
			mTempPosition.y = mPosition.y;
			mEventFrame = MIN_JUMP - 1;
			mIsInvincible = true;
			mHasPole = false;
			break;
		default:
			break;
		}
		switch(mPreStatus){
		case GameConstants.ZOMBIE_JUMP:
			mIsInvincible = false;
			dropItem();
			break;
			default:
				break;
		}
	}
	public void Jump(){
		mStatus = GameConstants.ZOMBIE_JUMP;
	}
	@Override
	public void onDie(){
		super.onDie();
		dropItem();
	}
}
