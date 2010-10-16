package chaoslab.PVZ.Zombies;

import android.util.Log;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Plants.Eatable;

public class Zombie extends GameObject {

	protected int 		mEatInterval 	= 10; //eat once per 30 frames as default
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
		mWidth 	= 80;
		mHeight = 100;
 	}
	
	public void move(){
		float moveFactor = mIsSlowed ? 1.0f : 0.5f;
		mPosition.x += mMoveDirection * mMoveSpeed * moveFactor;
	}
	
	public void eat(Eatable target){
		float eatFactor = mIsSlowed ? 1.0f : 2.0f;
		if (mElapsedFrame % (mEatInterval * eatFactor)== 0){
			target.ate(mAttackPower);
		}
	}
	
	@Override
	public void update(){
		super.update();
	}
	
}
