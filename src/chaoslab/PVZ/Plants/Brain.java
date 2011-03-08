package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.R;
import chaoslab.PVZ.Zombies.Zombie;

public class Brain extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4343019466100268654L;
	private static Bitmap[] mWaveBitmaps;
	public Brain(String name,  Particle[] particles, int cost) {
		super(name, null, cost);
		mHealthPoint = 100;
		mWidth 		 = 60;
		mHeight		 = 80;
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		
	}
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.brain),
		};
	}
	
	public void update(){
		super.update();
		updateWaveBitmap(mWaveBitmaps);
	}
}
