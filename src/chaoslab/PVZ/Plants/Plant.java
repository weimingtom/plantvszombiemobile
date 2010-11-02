package chaoslab.PVZ.Plants;

import java.util.ArrayList;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Harmable;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;
import android.graphics.Bitmap;
/**
 * 
 * @author Liu.zhenxing
 * define Plant class
 */
abstract public class Plant extends GameObject implements Harmable{
	public static final int PLANT_STATE_WAVE 			= 0;
	public static final int PLANT_STATE_ATTACK 			= 1;
	public static final int PLANT_STATE_SPECIAL_ACTION	= 2;

	protected int	curWaveImgNum 			= 0;
	protected int 	curAttackImgNum			= 0;
	protected Bitmap[] 	mWaveBitmaps;
	protected Bitmap[]	mAttackBitmaps;

	protected static final int WAVE_INTERVAL = 2;
	public Plant(String name, Particle particles[], int cost) {
		super(name, particles, cost);
		mStand = GameConstants.STAND_PLANT;
	}
	
	public abstract void attack(ArrayList<Zombie> zombies);
	public Bitmap getBitmap(){
		return null;
	}
	public void onHarmed(int harmPoint){
		mHealthPoint -= harmPoint;
		if (mHealthPoint <= 0){
			mHealthPoint = 0;
			this.onDie();
		}
	}
	
	public void setWaveBitmaps(Bitmap[] bitmaps){
		mWaveBitmaps = bitmaps;
	}
	
	public void setAttackBitmaps(Bitmap[] bitmaps){
		mAttackBitmaps = bitmaps;
	}
	
	public boolean isDefCreature(){
		return false;
	}
	public boolean isProtected(){
		return false;
	}
}

