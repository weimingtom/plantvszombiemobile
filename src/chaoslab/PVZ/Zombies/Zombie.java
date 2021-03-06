package chaoslab.PVZ.Zombies;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Harmable;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import chaoslab.PVZ.SoundManager;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.Plants.PlantCells;
import chaoslab.PVZ.ZombieItem.AbstractItem;

public class Zombie extends GameObject implements Harmable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6290192405500653434L;
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
	protected int 		mEatInterval 	= 3; //eat once per 10 frames as default
	protected boolean 	mIsSlowed 		= false;
	protected int		mAttackPower	= 8;
	//keep the frame number before the break-out event
	protected int       mPreviousFrame = 0;
	protected int       mStatus = GameConstants.ZOMBIE_MOVE;
	protected Plant     mTarget = null;
	protected int       mPreStatus = GameConstants.ZOMBIE_MOVE;
	protected transient Bitmap	mBitmap[];  
	protected int       mMaxBitmap = 12;
	protected int 		mEatFrmCnt = 0;
	protected int       mSlowFrmCnt = 0;
	/** indicate whether zombie can be hit*/
	protected boolean	mIsInvincible = false;
	protected boolean	mIsCharred	=   false;
	private int mCharredBitmapIndex = 0;
	private transient Bitmap[] mCharredBitmap;
	 
	protected Position mTempPosition;
	protected  AbstractItem mItem;
	protected int mClimbCnt;
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
		mPreStatus = GameConstants.ZOMBIE_MOVE;
		mEventFrame = MIN_MOVE;
		mMoveSpeed = 5;
		mClimbCnt = 0;
		setMoveDirection(-1, 0);
		mTempPosition = new Position(0,0);
		mClimbCnt = 0;
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
		mStatus = GameConstants.ZOMBIE_PRE_SEEK;
		mTarget = target;
	}
	public void eat(Plant target){
		if(target.hasLadder()){
			mStatus = GameConstants.ZOMBIE_CLIMB;
		}else{
			if(mStatus != GameConstants.ZOMBIE_ATTACK){
				mStatus = GameConstants.ZOMBIE_ATTACK;
				mTarget = target;
				mEatFrmCnt = 0;
			}
		}
	}
	/*
	 * do this action when status stay in ZOMBIE_ATTACK
	 */
	public void eating(){
		if(mTarget.isAlive()){
			mTarget.onHarmed(mAttackPower);
			SoundManager.getInstance().play(R.raw.chomp, 0, 1.0f);
		}else{
			mTarget.onDie();
			mStatus = GameConstants.ZOMBIE_MOVE;
			mEventFrame = MIN_MOVE;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if(mBitmap==null)return;
		if (mStatus != GameConstants.ZOMBIE_CHARRED){
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
		}else{
			super.doDraw(canvas, scaleX, scaleY, paint);
		}
		if(mItem != null){
			mItem.doDraw(canvas,scaleX,scaleY,paint);
		}
	}
	/*
	 * do this action when status stay in ZOMBIE_MOVE
	 */
	public void moving(){
		float moveFactor = mIsSlowed ? 0.5f : 1.0f;
		mPosition.x += mMoveDirection.x * mMoveSpeed * moveFactor;
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
			moving();
			++mEventFrame; 
			if(MAX_MOVE < mEventFrame){
				mEventFrame = MIN_MOVE;
			}
			break;
		case GameConstants.ZOMBIE_ATTACK:
			int attackFactor = mIsSlowed ? 2 : 1;
			if(mEatFrmCnt % ( mEatInterval * attackFactor)== 0 || !mTarget.isAlive()){
				eating();
				++mEventFrame; 
			}
			mEatFrmCnt ++;
			if(MAX_EAT < mEventFrame){
				mEventFrame = MIN_EAT;
			}
			break;
		case GameConstants.ZOMBIE_ATTACKED:
			if(MAX_ATTACKED <= mEventFrame){
				//mEventFrame = mPreviousFrame;
				//mStatus = GameConstants.ZOMBIE_MOVE - 1;
			}
			break;
		case GameConstants.ZOMBIE_CHARRED:
			if (mCharredBitmap != null && mCharredBitmapIndex < mCharredBitmap.length){
				mCurBitmap = mCharredBitmap[mCharredBitmapIndex];
				mCharredBitmapIndex ++;
			}else{
				mCharredBitmapIndex = 0;
				mIsAlive = false;
			}
			break;
		case GameConstants.ZOMBIE_CLIMB:
			mClimbCnt ++;
			doClimb();
			break;
		default:
			++mEventFrame; 
			break;
		}
		if(mItem != null){
			mItem.moveTo(mPosition);
			mItem.update();
		}
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
			mEventFrame = MIN_MOVE;
			break;
		case GameConstants.ZOMBIE_ATTACK:
			mEventFrame = MIN_EAT;
			break;
		case GameConstants.ZOMBIE_ATTACKED:
			//mEventFrame = MIN_ATTACKED - 1;
			break;
		case GameConstants.ZOMBIE_CLIMB:
			mTempPosition.x = mPosition.x;
			mTempPosition.y = mPosition.y;
			mIsInvincible = true;
			break;
		default:
			break;
		}
		switch(mPreStatus){
		case GameConstants.ZOMBIE_CLIMB:
			mTempPosition.x = 0;
			mTempPosition.y = 0;
			mIsInvincible = false;
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
	
	public int getStatus(){
		return mStatus;
	}
	
	public void setIsCharred(boolean isCharred){
		mIsCharred = isCharred;
	}
	public void setCharredBitmap(Bitmap[] bitmaps){
		mCharredBitmap = bitmaps;
	}
	@Override
	public void onDie(){
		//super.onDie();
		if (mIsCharred){
			mStatus = GameConstants.ZOMBIE_CHARRED;
			mIsInvincible = true;
			mCharredBitmapIndex = 0;
		}else{
			super.onDie();
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Zombie obj = (Zombie)super.clone();
		obj.init();
		return obj;
	}
	public void init()throws CloneNotSupportedException{
		mIsSlowed = false;
		mTarget = null;
		mStatus = GameConstants.ZOMBIE_MOVE;
		mIsInvincible = false;
		mIsCharred	=   false;
		mCharredBitmapIndex = 0;
		mMoveDirection = new Position(-1,0);
		mPosition = new Position(0,0);
		mPreviousFrame = 0;
		mMoveSpeed = 5.0f;
		mPreStatus = GameConstants.ZOMBIE_MOVE;
		if(mItem != null){
			mItem = (AbstractItem)mItem.clone();
		}
		mTempPosition = new Position(0,0);
		mClimbCnt = 0;
	}
	@Override
	public void setCenterPosition(float posX, float posY){
		super.setCenterPosition(posX, posY);
		if(mItem != null){
			mItem.moveTo(mPosition);
		}
	}

	public void dropItem(){
		if(mItem != null){
			mItem.droped(mPosition);
		}
		mItem = null;
	}
	
	public AbstractItem getItem(){
		return mItem;
	}
	
	public void setItem(AbstractItem item){
		mItem = item;
	}
	public void doClimb(){
		mPosition.x = mTempPosition.x + mMoveDirection.x *PlantCells.CELL_HEIGHT/2*(
				1.0f - (float)Math.cos(22.5f*mClimbCnt*Math.PI/180.0));
		mPosition.y = mTempPosition.y - (float)(PlantCells.CELL_HEIGHT/2*Math.sin(22.5f*mClimbCnt*Math.PI/180.0));
		if(mClimbCnt == 8){
			mStatus = GameConstants.ZOMBIE_MOVE;
		}
	}
	
}
