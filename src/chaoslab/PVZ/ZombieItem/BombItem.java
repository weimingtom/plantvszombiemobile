package chaoslab.PVZ.ZombieItem;

import android.graphics.Bitmap;
import chaoslab.PVZ.Plants.Plant;

public class BombItem extends AbstractItem{
	protected Plant mTarget;
	public BombItem(Bitmap[] bitmap){
		super(bitmap);
		mHealthPoint = 50;
		canDef = false;
		bmagnetized = true;
		mTarget = null;
	}
	@Override
	public void doAction(){
		if(mTarget != null){
			mTarget.onDie();
			mHealthPoint = 0;
			//do something bomb stuffs
		}
	}
	public void setTarget(Plant target){
		mTarget = target;
	}
	@Override
	public void init(){
		super.init();
		mTarget = null;
		mHealthPoint = 50;
	}
}
