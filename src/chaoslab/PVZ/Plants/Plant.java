package chaoslab.PVZ.Plants;

import java.util.ArrayList;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Harmable;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * 
 * @author Liu.zhenxing
 * define Plant class
 */
abstract public class Plant extends GameObject implements Harmable{
	public static final int PLANT_STATE_WAVE 			= 0;
	public static final int PLANT_STATE_ATTACK 			= 1;
	public static final int PLANT_STATE_SPECIAL_ACTION	= 2;

	protected int	mState	= PLANT_STATE_WAVE;
	/** total attack frame*/
	protected int 	mAttackFrame			= 0;
	protected int	curWaveImgNum 			= 0;
	protected int 	curAttackImgNum			= 0;
	protected int	mAttackFrmCount	  = 0;
	
	protected static Bitmap[] 	mWaveBitmaps;
	//protected static Bitmap[]	mAttackBitmaps;

	protected static final int WAVE_INTERVAL = 2;
	public Plant(String name, Particle particles[], int cost) {
		super(name, particles, cost);
		mStand = GameConstants.STAND_PLANT;
		/*
		 * set default properties
		 */
		mWidth = 60;
		mHeight = 80;
		mHealthPoint = 100;
	}
	
	public abstract void attack(ArrayList<Zombie> zombies);
	
	public void onHarmed(int harmPoint){
		mHealthPoint -= harmPoint;
		if (mHealthPoint <= 0){
			mHealthPoint = 0;
			this.onDie();
		}
	}
	
	public void updateWaveBitmap(Bitmap bitmaps[]){
		if (bitmaps != null){
			if (mElapsedFrame % WAVE_INTERVAL == 0){
				curWaveImgNum ++;
				if (curWaveImgNum >= bitmaps.length){
					curWaveImgNum = 0;
				}
			}
			mCurBitmap = bitmaps[curWaveImgNum];
		}
	}
	
	public void updateAttackBitmap(Bitmap bitmaps[]){
		if (bitmaps != null){
			mAttackFrmCount++;
			/*
			 * update img per frame as default, or here
			 * should check update interval.Consider it 
			 * in future.
			*/
			curAttackImgNum++;
			if (mAttackFrmCount > mAttackFrame){
				mAttackFrmCount = 0;
				curAttackImgNum = 0;
			}
			mCurBitmap = bitmaps[curAttackImgNum];
		}
	}
	/*
	public void setWaveBitmaps(Bitmap[] bitmaps){
		mWaveBitmaps = bitmaps;
	}
	
	public void setAttackBitmaps(Bitmap[] bitmaps){
		mAttackBitmaps = bitmaps;
		if (bitmaps != null)
			mAttackFrame   = bitmaps.length;
		else
			mAttackFrame = 0;
	}
	*/
	public boolean isDefCreature(){
		return true;
	}
	public boolean isProtected(){
		return false;
	}
	
	public Bitmap getBitmap(){
		return mCurBitmap;
		/*if (mWaveBitmaps != null)
		{
			return mWaveBitmaps[0];
		}else{
			return null;
		}*/
	}
	
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if (mCurBitmap == null)
			return;
		/*
		 * scale bitmap to proper width and height
		 */
		int width 	= PlantCells.CELL_WIDTH > mCurBitmap.getWidth() ?  mCurBitmap.getWidth() :PlantCells.CELL_WIDTH;
		int height 	= PlantCells.CELL_HEIGHT > mCurBitmap.getHeight() ? mCurBitmap.getHeight():PlantCells.CELL_HEIGHT;
		int cellPosY = (int)(PlantCells.getRow(mPosition) * PlantCells.CELL_HEIGHT + PlantCells.ORIGIN.y);
		canvas.drawBitmap(mCurBitmap, null, 
				new Rect((int)(mPosition.x * scaleX), (int)((cellPosY + PlantCells.CELL_HEIGHT - height) * scaleY),
						(int)((mPosition.x + width) * scaleX),
						(int)((cellPosY + PlantCells.CELL_HEIGHT) * scaleY)), paint);
		
	}

}

