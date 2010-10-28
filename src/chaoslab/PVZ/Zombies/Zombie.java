package chaoslab.PVZ.Zombies;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.Log;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Plant;

public class Zombie extends GameObject{

	protected final int MIN_ATTACKED = 8;
	protected final int MAX_ATTACKED = 9;
	protected final int MIN_EAT = 4;
	protected final int MAX_EAT = 7;
	protected final int MIN_MOVE = 0;
	protected final int MAX_MOVE = 3;
	//0-10: attacked 10-20:eat 20-30:move
	//differ from move frame
	protected int 		mEventFrame		= 0; 
	protected int 		mEatInterval 	= 30; //eat once per 30 frames as default
	protected float 	mMoveSpeed		= 5.0f;   //per frame
	protected boolean 	mIsSlowed 		= false;
	protected int		mAttackPower	= 25;
	protected int		mMoveDirection	= -1;  //1:right, -1:left , 0:static
	//keep the frame number before the break-out event
	protected int       mPreviousFrame = 0;
	protected int       mStatus = GameConstants.ZOMBIE_MOVE;
	protected Plant     mTarget = null;
	protected int       mPreStatus = GameConstants.ZOMBIE_MOVE;
	protected Bitmap	mBitmap[];  
	protected int       mMaxBitmap = 10;
	protected int 		mEatFrmCnt = 0;
	 
	//protected Bitmap	;
	/**
	 * Constructor: name, particles, cost
	 */
	public Zombie(String name, Particle particles[], Bitmap bitmap[],int cost) {
		super(name, particles, cost);
		mStand  = GameConstants.STAND_ZOMBIE;
		mWidth 	= 80;
		mHeight = 100;
		mHealthPoint = 50;	
		mBitmap = bitmap;
		mMaxBitmap = 10;
		mStatus = GameConstants.ZOMBIE_MOVE;
		mEventFrame = MIN_MOVE;;
 	}
	public boolean isDefCreature(){
		return false;
	}
	public boolean directAttatck(){
		return false;
	}
	public void move(){
		float moveFactor = mIsSlowed ? 1.0f : 0.5f;
		mPosition.x += mMoveDirection * mMoveSpeed * moveFactor;
		mStatus = GameConstants.ZOMBIE_MOVE;
	}
	public void seek(Plant target){
		mStatus = GameConstants.ZOMBIE_SEEK;
		mTarget = target;
	}
	public void eat(Plant target){
		mTarget = target;
		mEventFrame = MIN_EAT;
		mStatus = GameConstants.ZOMBIE_ATTACK;
		mEatFrmCnt = 0;
	}
	public void eating(){
		if(mTarget.isAlive()){
			mTarget.ate(mAttackPower);
		}else{
			mTarget.onDie();
			mStatus = GameConstants.ZOMBIE_MOVE;
			mEventFrame = MIN_MOVE;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		canvas.drawBitmap(mBitmap[mEventFrame], null, 
				new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
						(int)((mPosition.x + mBitmap[mEventFrame].getWidth()) * scaleX),
						(int)((mPosition.y + mBitmap[mEventFrame].getHeight()) * scaleY)), paint);
	}
	@Override
	public void update(){
		//update status
		if(mPreStatus != mStatus){
			updateStatus();
			mPreStatus = mStatus;
		}
		//update bitmap frame
		++mEventFrame; 
		if(MAX_ATTACKED == mEventFrame){
			//mEventFrame = mPreviousFrame;
			//mStatus = GameConstants.ZOMBIE_MOVE;
		}else if(MAX_EAT == mEventFrame){
			mEventFrame = MIN_EAT;
		}else if(MAX_MOVE == mEventFrame){
			mEventFrame = MIN_MOVE;
		}else{
		}
		//update other
		if(mStatus == GameConstants.ZOMBIE_ATTACK){
			if(mEatFrmCnt%mEatInterval == 0){
				eating();
			}
			mEatFrmCnt ++;
		}
		super.update();
		
	}
	public void updateStatus(){
		switch(mStatus){
		case GameConstants.ZOMBIE_MOVE:
			mEventFrame = MIN_MOVE;
			break;
		case GameConstants.ZOMBIE_ATTACK:
			mEventFrame = MIN_EAT;
			break;
		case GameConstants.ZOMBIE_ATTACKED:
			//mEventFrame = MIN_ATTACKED;
			break;
		default:
			break;
		}
	}
	public void attacked( int harmPoint )
	{
		//how to process the slowed status?
		//more switch or a picture mask?
		//mStatus = GameConstants.ZOMBIE_ATTACKED;
		mHealthPoint -= harmPoint;
		//mEventFrame = MIN_ATTACKED;
		if(0 >= mHealthPoint){
			mHealthPoint =0;
			this.onDie();
		}
	}
	public void addHealthPoint(int delta){
		if (mIsAlive){
			attacked(-1*delta);
		}
	}
}
