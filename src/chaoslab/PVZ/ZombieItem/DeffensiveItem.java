package chaoslab.PVZ.ZombieItem;

import chaoslab.PVZ.Position;
import android.graphics.Bitmap;

public class DeffensiveItem extends AbstractItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1055640659596450347L;
	protected int mMaxHealth = 0;
	public DeffensiveItem(Bitmap[] bitmap,int hP,boolean bMag){
		super(bitmap);
		mHealthPoint = hP;
		mMaxHealth = hP;
		bmagnetized = bMag;	
		canDef = true;
	}
	@Override
	public void init(){
		super.init();
		mHealthPoint = mMaxHealth;
	}
	@Override
	public void moveTo(Position pos){
		mPosition.x = pos.x -10;
		mPosition.y = pos.y -30;
	}
}
