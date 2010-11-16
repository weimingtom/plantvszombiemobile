package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.R;
import chaoslab.PVZ.Zombies.Zombie;

public class SunFlower extends Plant{
	public static final int SINGLE_SUNSHINE_NUM = 25;
	private static Bitmap[] 	mWaveBitmaps;
	public SunFlower(String name, Particle particles[], int cost) {
		super(name, particles, cost);
	}
	
	@Override
	public void onHarmed(int harmPoint){
		super.onHarmed(harmPoint);
		if (mGameEventListener != null){
			mGameEventListener.onSunshineAdded(SINGLE_SUNSHINE_NUM);
		}
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// do nothing
		
	}
	
	public void update(){
		super.update();
		updateWaveBitmap(mWaveBitmaps);
	}

	public static void initBitmaps(Resources res) {
		mWaveBitmaps = new Bitmap[] {
				BitmapFactory.decodeResource(res, R.drawable.sunflower00),
				BitmapFactory.decodeResource(res, R.drawable.sunflower01),
				BitmapFactory.decodeResource(res, R.drawable.sunflower02),
				BitmapFactory.decodeResource(res, R.drawable.sunflower03),
				BitmapFactory.decodeResource(res, R.drawable.sunflower04),
				BitmapFactory.decodeResource(res, R.drawable.sunflower05),
				BitmapFactory.decodeResource(res, R.drawable.sunflower06),
				BitmapFactory.decodeResource(res, R.drawable.sunflower07),
				BitmapFactory.decodeResource(res, R.drawable.sunflower08),
			};
	}
}
