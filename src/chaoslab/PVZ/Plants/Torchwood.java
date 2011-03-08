package chaoslab.PVZ.Plants;

import java.util.ArrayList;
//import chaoslab.PVZ.Fire;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.R;
import chaoslab.PVZ.Zombies.Zombie;

public class Torchwood extends Plant {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1608066282204284236L;
	private static Bitmap[] 	mWaveBitmaps;
	//protected Fire fire;
	public Torchwood(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		// TODO Auto-generated constructor stub
		//fire = new Fire(40,40,mPosition,0);
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		super.doDraw(canvas,scaleX,scaleY,paint);
	//	if(fire!=null){
		//	fire.doDraw(canvas,scaleX,scaleY,paint);
		//}
	}
	public void update(){
		super.update();
		updateWaveBitmap(mWaveBitmaps);
		//fire.update();
	}
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.torchwood_body),
		};
	}
}
