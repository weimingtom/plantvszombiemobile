package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Plants.Plant;
import android.graphics.Matrix;

public class BungeeZombie extends Zombie{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6759620223439773753L;
	protected Bitmap capturePlant = null;
	protected int   mPrepareCnt = 0;
	protected final int LINE = 1;
	protected final int TARGET = 2;
	
	public BungeeZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mHealthPoint = 200;
		mMaxBitmap = 3;
	}
	
	@Override
	public void move(){
		
	}
	@Override
	public void eat(Plant target){
		//mStatus = GameConstants.ZOMBIE_SEEK;
		//mTarget = target;
	}
	@Override
	public void update(){
		super.update();
		switch (mStatus){
		case GameConstants.ZOMBIE_PRE_SEEK:
			mPrepareCnt ++;
			if(mPrepareCnt == 10)
			{
				mStatus = GameConstants.ZOMBIE_SEEK;
			}
			break;
		case GameConstants.ZOMBIE_SEEK:
			seeking();
			break;
		case GameConstants.ZOMBIE_TRANSIT:
			transport();
			break;
		case GameConstants.ZOMBIE_FROZON:
			freeze();
			break;
		default:
			backward();
		}
	}
	public void seeking(){
		mIsInvincible = true;
		if(mTarget == null){
			mStatus = GameConstants.ZOMBIE_BACKWARD;
		}else if(mPosition.y < mTarget.getPosition().y - 20){
			mPosition.y += 50;
			if(mPosition.y > mTarget.getPosition().y - 20){
				mPosition.y = mTarget.getPosition().y - 20;
			}
		}else if(!mTarget.isProtected()){
			mStatus = GameConstants.ZOMBIE_FROZON;
			mEatFrmCnt = 0;
		}else{
			mStatus = GameConstants.ZOMBIE_BACKWARD;
		}
	}
	@Override
	public boolean directAttatck(){
		return true;
	}
	public void transport(){
		mIsInvincible = true;
		if(mTarget == null){
			mStatus = GameConstants.ZOMBIE_BACKWARD;
		}else if(mPosition.y >= 0){
			mPosition.y -= 50;
		}else{
			onDie();
		}
	}
	public void freeze(){
		mIsInvincible = false;
		if(!mTarget.isAlive()){
			mStatus = GameConstants.ZOMBIE_BACKWARD;
			mTarget.onDie();
		}
		if(mEatFrmCnt == 20){
			mStatus = GameConstants.ZOMBIE_TRANSIT;
			capturePlant = mTarget.getBitmap();
			mTarget.onDie();
		}
		mEatFrmCnt ++;
	}
	
	public void backward(){
		mIsInvincible = true;
		if(mPosition.y >= 0){
			mPosition.y -= 50;
		}else{
			onDie();
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if(mStatus == GameConstants.ZOMBIE_PRE_SEEK){
			canvas.drawBitmap(mBitmap[TARGET],null,
					new Rect((int)(mTarget.getPosition().x * scaleX), (int)(mTarget.getPosition().y * scaleY),
							(int)((mTarget.getPosition().x + mBitmap[TARGET].getWidth()) * scaleX),
							(int)((mTarget.getPosition().y + 30 + mBitmap[TARGET].getHeight()) * scaleY)), 
							paint);
		}else if(mStatus == GameConstants.ZOMBIE_MOVE){
			canvas.drawBitmap(mBitmap[0], null, 
					new Rect((int)(mPosition.x * scaleX), 
							(int)(mPosition.y * scaleY),
							(int)((mPosition.x + mBitmap[0].getWidth()) * scaleX),
							(int)((mPosition.y + mBitmap[0].getHeight()) * scaleY)), 
							paint);
		}else{
			int lineCnt = (int)(mPosition.y/mBitmap[LINE].getHeight()) + 1;
			for(int i = 1;i<=lineCnt;++i){
				Matrix matrix = new Matrix();
				matrix.postScale(scaleX,scaleY);
				matrix.postTranslate((mPosition.x + (mBitmap[0].getWidth() - mBitmap[LINE].getWidth())/2) * scaleX,
									(mPosition.y - i*mBitmap[LINE].getHeight())* scaleY);
				canvas.drawBitmap(mBitmap[LINE], matrix,paint);
			}
			canvas.drawBitmap(mBitmap[0], null, 
					new Rect((int)(mPosition.x * scaleX), 
							(int)(mPosition.y * scaleY),
							(int)((mPosition.x + mBitmap[0].getWidth()) * scaleX),
							(int)((mPosition.y + mBitmap[0].getHeight()) * scaleY)), 
							paint);
		}
		if(capturePlant != null){
			canvas.drawBitmap(capturePlant, null, 
					new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
							(int)((mPosition.x + capturePlant.getWidth()) * scaleX),
							(int)((mPosition.y + 30 + capturePlant.getHeight()) * scaleY)), paint);
		}
		
	}
	/**
	 * LeafBunch should call this method to check whether the target is in its protect
	 * range.
	 * @return
	 */
	public Plant getTarget(){
		return mTarget;
	}
	
	public void setStatus(int status){
		mStatus = status;
	}
}