package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Plants.Plant;

public class BungeeZombie extends Zombie{
	public BungeeZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mHealthPoint = 100;
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
			mPosition.y += 30;
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
			mPosition.y -= 10;
		}else{
			onDie();
		}
	}
	public void freeze(){
		if(mEatFrmCnt == 20){
			mStatus = GameConstants.ZOMBIE_TRANSIT;
			mTarget.onDie();
		}
		mEatFrmCnt ++;
	}
	public void backward(){
		if(mPosition.y >= 0){
			mPosition.y -= 10;
		}else{
			onDie();
		}
	}
}