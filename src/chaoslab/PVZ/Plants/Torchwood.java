package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.R;
import chaoslab.PVZ.Zombies.Zombie;

public class Torchwood extends Plant {
	private static Bitmap[] 	mWaveBitmaps;
	public Torchwood(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		
	}
	
	public void update(){
		super.update();
		updateWaveBitmap(mWaveBitmaps);
	}
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.torchwood_body),
		};
	}
}
