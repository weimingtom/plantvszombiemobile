package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Plants.Plant;

public class BungeeZombie extends Zombie{
	protected Bitmap capturePlant = null;
	public BungeeZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mHealthPoint = 200;
		MIN_ATTACKED = 8;
		MAX_ATTACKED = 9;
		MIN_EAT = 6;
		MAX_EAT = 7;
		MIN_MOVE = 0;
		MAX_MOVE = 9;
		mMaxBitmap = 10;
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
		if(mStatus == GameConstants.ZOMBIE_SEEK){
			seeking();
		}else if(mStatus == GameConstants.ZOMBIE_TRANSIT){
			transport();
		}else if(mStatus == GameConstants.ZOMBIE_FROZON){
			freeze();
		}else{
			
		}
	}
	public void seeking(){
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
		if(mTarget == null){
			mTarget.onDie();
			mStatus = GameConstants.ZOMBIE_BACKWARD;
		}else if(mPosition.y >= 0){
			mPosition.y -= 50;
		}else{
			onDie();
		}
	}
	public void freeze(){
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
		if(mPosition.y >= 0){
			mPosition.y -= 50;
		}else{
			onDie();
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		super.doDraw(canvas, scaleX, scaleY, paint);
		if(capturePlant != null){
			canvas.drawBitmap(capturePlant, null, 
					new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
							(int)((mPosition.x + capturePlant.getWidth()) * scaleX),
							(int)((mPosition.y + 30 + capturePlant.getHeight()) * scaleY)), paint);
		}
		
	}
}