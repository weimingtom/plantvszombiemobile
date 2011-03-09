package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.Plants.PlantCells;
import android.graphics.Matrix;
import java.lang.Math;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.ZombieItem.DeffensiveItem;

public class UndermineZombie extends Zombie{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7685331770678234577L;
	protected int mRisingCnt = 0;
	protected Position mTempPos;
	protected int MIN_RISING = 1;
	protected int MAX_RISING = 4;
	public UndermineZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mStatus = GameConstants.ZOMBIE_UNDERGROUND;
		mPreStatus = GameConstants.ZOMBIE_UNDERGROUND;
		MIN_ATTACKED = 4;
		MAX_ATTACKED = 4;
		MIN_EAT = 1;
		MAX_EAT = 1;
		MIN_MOVE = 1;
		MAX_MOVE = 1;
		mMaxBitmap = bitmap.length;
		mTempPos = new Position(0,0);
		mItem = new DeffensiveItem(null,50,true);
	}
	public void comeOut(){
		mStatus = GameConstants.ZOMBIE_COME_OUT;
		mMoveSpeed = 5;
		mTempPos.x = mPosition.x;
		mTempPos.y = mPosition.y;
		mEventFrame = MIN_RISING;
	}
	public void rising(){
		mPosition.y = mTempPos.y - (float)(5.0f*Math.sin(mRisingCnt*45*Math.PI/180.0f));
		mRisingCnt ++;
	}
	@Override
	public void update(){
		super.update();
		//do action
		switch(mStatus){
		case GameConstants.ZOMBIE_UNDERGROUND:
			mIsInvincible = true;
			mEventFrame = 5;
			mMoveSpeed = 15;
			moving();
			if (mPosition.x <= PlantCells.CELL_WIDTH){
				comeOut();
			}
			break;
		case GameConstants.ZOMBIE_COME_OUT:
			if(mRisingCnt > 4){
				mRisingCnt = 0;
				mStatus = GameConstants.ZOMBIE_MOVE;
				if (mPosition.x <= PlantCells.CELL_WIDTH){
					setMoveDirection(1, 0);
				}
				mTempPos.x = 0;
				mTempPos.y = 0;
				mEventFrame = MIN_MOVE;
				mIsInvincible = false;
			}else{
				rising();
				mEventFrame++;
				if(mEventFrame > MAX_RISING){
					mEventFrame = MIN_RISING;
				}
			}
		default:
			break;
		}
	}
	@Override
	public void eat(Plant target){
		if(mStatus == GameConstants.ZOMBIE_MOVE ){
			mStatus = GameConstants.ZOMBIE_ATTACK;
			mTarget = target;
			mEatFrmCnt = 0;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		float tempScaleY = scaleY/2;
		if (mStatus != GameConstants.ZOMBIE_CHARRED){
			if(mEventFrame < 0 || mEventFrame >= mMaxBitmap){
				Matrix matrix = new Matrix();
				matrix.postScale(scaleX,tempScaleY);
				//matrix.postRotate(180, mBitmap[0].getWidth()/2, mBitmap[0].getHeight()/2);
				matrix.postTranslate(mPosition.x*scaleX, mPosition.y*scaleY);
				matrix.postTranslate(-PlantCells.CELL_WIDTH*scaleX, 0);
				canvas.drawBitmap(mBitmap[0], matrix,paint);
			}else if(mEventFrame < mMaxBitmap ){
				Matrix matrix = new Matrix();
				matrix.postScale(scaleX,tempScaleY);
				//matrix.postRotate(180, mBitmap[mEventFrame].getWidth()/2, mBitmap[mEventFrame].getHeight()/2);
				matrix.postTranslate(mPosition.x*scaleX, mPosition.y*scaleY);
				matrix.postTranslate(-PlantCells.CELL_WIDTH*scaleX, 0);
				if(mEventFrame == 5){
					matrix.postTranslate(0, PlantCells.CELL_HEIGHT*scaleY);
				}
				canvas.drawBitmap(mBitmap[mEventFrame], matrix,paint);
			}
		}else{
			super.doDraw(canvas, scaleX, scaleY, paint);
		}
		if(mItem != null){
			mItem.doDraw(canvas,scaleX,scaleY,paint);
		}
	}
	@Override
	public void init()throws CloneNotSupportedException{
		super.init();
		mStatus = GameConstants.ZOMBIE_UNDERGROUND;
		mTempPos = new Position(0,0);
		mRisingCnt = 0;
		mIsInvincible = true;
		mItem = new DeffensiveItem(null,50,true);
	}
	@Override
	public void dropItem(){
		super.dropItem();
		if(mStatus == GameConstants.ZOMBIE_UNDERGROUND){
			comeOut();
		}
	}
}