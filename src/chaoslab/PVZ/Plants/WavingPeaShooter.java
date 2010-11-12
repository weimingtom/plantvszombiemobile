package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WavingPeaShooter extends PeaShooter {
	private static Bitmap[] 	mWaveBitmaps;
	private static Bitmap[] 	mAttackBitmaps;
	public WavingPeaShooter(String name, Particle[] particles, int cost, int type) {
		super(name, particles, cost, type);
		
	}
	/*public void setSpecialAcationBitmaps(Bitmap[] bitmaps){
		mSpecialActionBitmaps = bitmaps;
	}*/
	
	@Override
	public void update(){
		super.update();
		updateWaveBitmap(mWaveBitmaps);
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
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.peashooter_01),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_02),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_03),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_04),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_05),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_06),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_07),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_08),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_09),
		};
	}
}
