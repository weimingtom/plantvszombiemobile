package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.graphics.Bitmap;
import chaoslab.PVZ.Zombies.Zombie;

public class Brain extends Plant {

	public Brain(Bitmap bitmap, int cost) {
		super("BRAIN", null, cost);
		mCurBitmap	 = bitmap;
		mHealthPoint = 100;
		mWidth 		 = 60;
		mHeight		 = 80;
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		
	}
	
}
