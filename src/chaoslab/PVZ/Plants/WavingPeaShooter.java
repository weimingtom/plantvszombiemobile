package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class WavingPeaShooter extends PeaShooter {

	protected int mState 					= PLANT_STATE_WAVE;
	protected int curWaveImgNum 			= 0;
	protected int curAttackImgNum			= 0;
	protected int curSpecialActionImgNum 	= 0;
	
	protected Bitmap[] 	mWaveBitmaps;
	protected Bitmap[]	mAttackBitmaps;
	protected Bitmap[]	mSpecialActionBitmaps;
	protected Bitmap 	mCurrentBitmap;
	
	public WavingPeaShooter(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		
	}
	
	public void setWaveBitmaps(Bitmap[] bitmaps){
		mWaveBitmaps = bitmaps;
	}
	
	public void setAttackBitmaps(Bitmap[] bitmaps){
		mAttackBitmaps = bitmaps;
	}
	
	public void setSpecialAcationBitmaps(Bitmap[] bitmaps){
		mSpecialActionBitmaps = bitmaps;
	}
	
	@Override
	public void update(){
		super.update();
		if (mElapsedFrame % WAVE_INTERVAL == 0){
			curWaveImgNum ++;
			if (curWaveImgNum >= mWaveBitmaps.length){
				curWaveImgNum = 0;
			}
		}
		if (mState == Plant.PLANT_STATE_WAVE){
			mCurrentBitmap = mWaveBitmaps[curWaveImgNum];
		}else {
			if (mState == Plant.PLANT_STATE_ATTACK){
				mCurrentBitmap = mAttackBitmaps[curAttackImgNum];
			}else{
				mCurrentBitmap = mWaveBitmaps[curWaveImgNum];
			}
		}
	}
	
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if (mCurrentBitmap == null)
			return;
		canvas.drawBitmap(mCurrentBitmap, null, 
				new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
						(int)((mPosition.x + mCurrentBitmap.getWidth()) * scaleX),
						(int)((mPosition.y + mCurrentBitmap.getHeight()) * scaleY)), paint);
	}
}
