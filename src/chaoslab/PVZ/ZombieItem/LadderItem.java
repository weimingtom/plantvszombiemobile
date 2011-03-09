package chaoslab.PVZ.ZombieItem;

import chaoslab.PVZ.Plants.Plant;
import android.graphics.Bitmap;

public class LadderItem extends AbstractItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1142428893052272822L;
	protected Plant mTarget;
	public LadderItem(Bitmap[] bitmap){
		super(bitmap);
		canDef = false;
		bmagnetized = true;
		mTarget = null;
	}
	@Override
	public void doAction(){
		//do something to the target plant
	}
	public void setTarget(Plant target){
		mTarget = target;
	}
	@Override
	public void init(){
		super.init();
		mTarget = null;
	}
}
