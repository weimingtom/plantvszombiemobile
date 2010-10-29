package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import android.graphics.Bitmap;

public class WavingPeaShooter extends PeaShooter {

	protected int mState 					= PLANT_STATE_WAVE;
	protected int curWaveImgNum 			= 0;
	protected int curAttackImgNum			= 0;
	protected int curSpecialActionImgNum 	= 0;
	
	protected Bitmap[] 	mWaveBitmaps;
	protected Bitmap[]	mAttackBitmaps;
	protected Bitmap[]	mSpecialActionBitmaps;
	
	public WavingPeaShooter(String name, Particle[] particles, int cost, int type) {
		super(name, particles, cost, type);
		
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
			mCurBitmap = mWaveBitmaps[curWaveImgNum];
		}else {
			if (mState == Plant.PLANT_STATE_ATTACK){
				mCurBitmap = mAttackBitmaps[curAttackImgNum];
			}else{
				mCurBitmap = mWaveBitmaps[curWaveImgNum];
			}
		}
	}
	public Bitmap getBitmap(){
		if(mWaveBitmaps != null)
		{
			return mWaveBitmaps[0];
		}else{
			return null;
		}
	}
}
