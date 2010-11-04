package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import android.graphics.Bitmap;

public class WavingPeaShooter extends PeaShooter {
	public WavingPeaShooter(String name, Particle[] particles, int cost, int type) {
		super(name, particles, cost, type);
		
	}
	
	public void setWaveBitmaps(Bitmap[] bitmaps){
		mWaveBitmaps = bitmaps;
	}
	
	public void setAttackBitmaps(Bitmap[] bitmaps){
		mAttackBitmaps = bitmaps;
	}
	
	/*public void setSpecialAcationBitmaps(Bitmap[] bitmaps){
		mSpecialActionBitmaps = bitmaps;
	}*/
	
	@Override
	public void update(){
		super.update();
		updateWaveBitmap();
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
}
