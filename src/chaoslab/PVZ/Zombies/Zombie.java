package chaoslab.PVZ.Zombies;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.Log;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Harmable;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Plant;

public class Zombie extends GameObject implements Harmable{
	/**@{
	 * keep the bitmap indices of each action
	 */
	protected int MIN_ATTACKED = 8;
	protected int MAX_ATTACKED = 9;
	protected int MIN_EAT = 6;
	protected int MAX_EAT = 7;
	protected int MIN_MOVE = 0;
	protected int MAX_MOVE = 11;
	/**@}*/
	
	protected int 		mEventFrame		= 0; 
	protected int 		mEatInterval 	= 10; //eat once per 10 frames as default
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
	protected int       mMaxBitmap = 12;
	protected int 		mEatFrmCnt = 0;
	protected int       mSlowFrmCnt = 0;
	/** indicate whether zombie can be hit*/
	protected boolean	mIsInvincible = false;
	 
	//protected Bitmap	;
	/**
	 * Constructor: name, particles, cost
	 */
	public Zombie(String name, Particle particles[], Bitmap bitmap[],int cost) {
		super(name, particles, cost);
		mStand  = GameConstants.STAND_ZOMBIE;
		mWidth 	= 80;
		mHeight = 100;
		mHealthPoint = 100;	
		mBitmap = bitmap;
		mMaxBitmap = 12;
		mStatus = GameConstants.ZOMBIE_MOVE;
		mEventFrame = MIN_MOVE - 1;
 	}
	/*
	 * meaningless
	 */
	public boolean isDefCreature(){
		return false;
	}
	/*
	 * can this zombie attack a specified target ? 
	 */
	public boolean directAttatck(){
		return false;
	}
	public void move(){
		mStatus = GameConstants.ZOMBIE_MOVE;
	}
	/*
	 * seek for the target
	 */
	public void seek(Plant target){
		mStatus = GameConstants.ZOMBIE_SEEK;
		mTarget = target;
	}
	public void eat(Plant target){
		if(mStatus != GameConstants.ZOMBIE_ATTACK){
			mStatus = GameConstants.ZOMBIE_ATTACK;
			mTarget = target;
			mEatFrmCnt = 0;
		}
	}
	/*
	 * do this action when status stay in ZOMBIE_ATTACK
	 */
	public void eating(){
		if(mTarget.isAlive()){
			mTarget.onHarmed(mAttackPower);
		}else{
			mTarget.onDie();
			mStatus = GameConstants.ZOMBIE_MOVE;
			mEventFrame = MIN_MOVE;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if(mEventFrame < 0 || mEventFrame >= mMaxBitmap){
			canvas.drawBitmap(mBitmap[0], null, 
					new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
							(int)((mPosition.x + mBitmap[0].getWidth()) * scaleX),
							(int)((mPosition.y + mBitmap[0].getHeight()) * scaleY)), paint);
		}else if(mEventFrame < mMaxBitmap ){
			canvas.drawBitmap(mBitmap[mEventFrame], null, 
				new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
						(int)((mPosition.x + mBitmap[mEventFrame].getWidth()) * scaleX),
						(int)((mPosition.y + mBitmap[mEventFrame].getHeight()) * scaleY)), paint);
		}
	}
	/*
	 * do this action when status stay in ZOMBIE_MOVE
	 */
	public void moving(){
		float moveFactor = mIsSlowed ? 1.0f : 0.5f;
		mPosition.x += mMoveDirection * mMoveSpeed * moveFactor;
	}
	@Override
	public void update(){
		super.update();
		//update status
		if(mPreStatus != mStatus){
			updateStatus();
			mPreStatus = mStatus;
		}
		if (mIsSlowed){
			mSlowFrmCnt --;
			if (mSlowFrmCnt <= 0){
				mIsSlowed 	= false;
				mSlowFrmCnt = 0;
			}
		}

		//do action
		switch(mStatus){
		case GameConstants.ZOMBIE_MOVE:
			if(MAX_MOVE == mEventFrame){
				mEventFrame = MIN_MOVE - 1;
			}
			moving();
			break;
		case GameConstants.ZOMBIE_ATTACK:
			if(MAX_EAT == mEventFrame){
				mEventFrame = MIN_EAT - 1;
			}
			int attackFactor = mIsSlowed ? 2 : 1;
			if(mEatFrmCnt % ( mEatInterval * attackFactor)== 0 || !mTarget.isAlive()){
				eating();
			}
			mEatFrmCnt ++;
			break;
		case GameConstants.ZOMBIE_ATTACKED:
			if(MAX_ATTACKED == mEventFrame){
				//mEventFrame = mPreviousFrame;
				//mStatus = GameConstants.ZOMBIE_MOVE - 1;
			}
			break;
		default:
			break;
		}
		++mEventFrame; 
		
		
	}
	/*
	 * do something when status change,detail things due to the previous 
	 * status and the current status
	 * eg: changing from status FROZON to MOVE is definitely different 
	 * from changing from status ATTACK to MOVE
	 * this need to be done in future 
	 */
	public void updateStatus(){
		switch(mStatus){
		case GameConstants.ZOMBIE_MOVE:
			mEventFrame = MIN_MOVE - 1;
			break;
		case GameConstants.ZOMBIE_ATTACK:
			mEventFrame = MIN_EAT - 1;
			break;
		case GameConstants.ZOMBIE_ATTACKED:
			//mEventFrame = MIN_ATTACKED - 1;
			break;
		default:
			break;
		}
	}
	/*
	 * a separate status is prepared for this action
	 * maybe we need do some process when zombie is attacked
	 * in the near future
	 */
	public void onHarmed( int harmPoint )
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
	/*
	 * (non-Javadoc)
	 * @see chaoslab.PVZ.GameObject#addHealthPoint(int)
	 * close to attacked 
	 */
	public void addHealthPoint(int delta){
		if (mIsAlive){
			onHarmed(-1*delta);
		}
	}
	
	public void onSlowed(int duration){
		mSlowFrmCnt = duration;
		mIsSlowed   = true;
		Log.d("ZOMBIE", "SLOWED");
	}
	
	public boolean isInvincible(){
		return mIsInvincible;
	}
	
	public void setInvincible(boolean isInvincible){
		mIsInvincible = isInvincible;
	}
	
	@Override
	public Bitmap getBitmap(){
		return mBitmap[0];
	}
}
