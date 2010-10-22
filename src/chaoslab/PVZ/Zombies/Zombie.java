package chaoslab.PVZ.Zombies;

import android.util.Log;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Eatable;

public class Zombie extends GameObject {

	protected int 		mEatFrame		= 30;
	protected int 		mEatInterval 	= 30; //eat once per 30 frames as default
	protected float 	mMoveSpeed		= 5.0f;   //per frame
	protected boolean 	mIsSlowed 		= false;
	protected int		mAttackPower	= 25;
	protected int		mMoveDirection	= -1;  //1:right, -1:left , 0:static

	//protected Bitmap	;
	/**
	 * Constructor: name, particles, cost
	 */
	public Zombie(String name, Particle particles[], int cost) {
		super(name, particles, cost);
		mStand  = GameConstants.STAND_ZOMBIE;
		mWidth 	= 80;
		mHeight = 100;
		mHealthPoint = 50;	
 	}
	
	public void move(){
		float moveFactor = mIsSlowed ? 1.0f : 0.5f;
		mPosition.x += mMoveDirection * mMoveSpeed * moveFactor;
	}
	
	public void eat(Eatable target){
		float eatFactor = mIsSlowed ? 1.0f : 2.0f;
		if (mEatFrame >= mEatInterval * eatFactor){
			target.ate(mAttackPower);
			mEatFrame = 0;
		}
		mEatFrame ++;
	}
	
	@Override
	public void update(){
		super.update();
		
	}
	
}
