package chaoslab.PVZ.ProjectileObjects;

import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Plants.Torchwood;
import chaoslab.PVZ.Zombies.Zombie;

public class ProjectilePea extends ProjectileObject {
	protected Bitmap[] mBitmaps;
	/** indicates the type of pea.*/
	protected int	   mType;
	public static final int PEA_TYPE_NORMAL = 0;
	public static final int PEA_TYPE_SNOW	= 1;
	public static final int PEA_TYPE_FIRE   = 2;
	
	protected static final int mSlowDurationFrame = 180;
	public ProjectilePea(String name, Bitmap[] bitmaps, int type) {
		super(name, null);
		// TODO Auto-generated constructor stub
		mWidth 	= 28;
		mHeight = 28;
		mBitmaps = bitmaps;
		mType	= type;
		if (type < mBitmaps.length)
			mCurBitmap = mBitmaps[type];
		else
			mCurBitmap = null;
	}

	public void setType(int type){
		mType = type;
		if (type < mBitmaps.length)
			mCurBitmap = mBitmaps[type];
	}
	
	@Override
	public void onHit(GameObject target) {
		if (!mIsAlive){
			return;
		}
		if (target.isAlive() && target.getStand() == GameConstants.STAND_ZOMBIE){
			switch (mType){
			case PEA_TYPE_NORMAL:
				target.addHealthPoint(-1 * mPower);
				break;
			case PEA_TYPE_SNOW:
				try{
					((Zombie)target).onSlowed(mSlowDurationFrame);
					target.addHealthPoint(-1 * mPower);
				}
				catch (Exception e){
				/**TODO Deal with Cast Exception*/
				}
				break;
			case PEA_TYPE_FIRE:
				target.addHealthPoint(-1 * 2 * mPower);
				break;
			}
			onDie();
		}
		if (target instanceof Torchwood ){
			switch(mType){
			case PEA_TYPE_NORMAL:
				setType(PEA_TYPE_FIRE);
				break;
			case PEA_TYPE_SNOW:
				setType(PEA_TYPE_NORMAL);
				break;
			default:
				setType(PEA_TYPE_FIRE);
			}
		}
	}

}
