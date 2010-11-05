/**
 * Chomper
 * One of the most powerful plants!
 * @author Liu.zhenxing
 */
package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.Zombies.Zombie;

public class Chomper extends Plant{
/*
	private static final int CHOMPER_STATE_NORMAL 		= 0;
	private static final int CHOMPER_STATE_ATTACK 		= 1;*/
	private static final int CHOMPER_STATE_DIGEST 		= PLANT_STATE_SPECIAL_ACTION;
	private static final int CHOMPER_DIGEST_FRAME 		= 100;
	private static final int ATTACK_FRAME 				= 10;
	
	private static final float ATTACK_RANGE	=  PlantCells.CELL_WIDTH * 0.5f;
	private int     mDigestFrmCount   = 0;
	private Bitmap	mFoodBitmap;
	public Chomper(String name, Particle[] particles, int cost) {
		super(name, null, cost);
		mAttackFrame = ATTACK_FRAME;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		switch (mState){
		case PLANT_STATE_WAVE:
			for (int i = 0; i < zombies.size(); ++i){
				Position zombiePosition = zombies.get(i).getPosition();
				if (!zombies.get(i).isInvincible() 
						&& PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)
						&& zombiePosition.x >= mPosition.x + mWidth 
						&& zombiePosition.x <= mPosition.x + mWidth + ATTACK_RANGE
						//&& PlantCells.getCol(zombiePosition)	== PlantCells.getCol(mPosition)	
						){
					mFoodBitmap = Bitmap.createBitmap(zombies.get(i).getBitmap());
					mState = PLANT_STATE_ATTACK;
					zombies.get(i).onDie();
					break;
				}
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void update(){
		super.update();
		switch (mState){
		case PLANT_STATE_WAVE:
			updateWaveBitmap();
			break;
		case CHOMPER_STATE_DIGEST:
			mDigestFrmCount++;
			if (mDigestFrmCount >= CHOMPER_DIGEST_FRAME){
				mDigestFrmCount = 0;
				mState = PLANT_STATE_WAVE;
				mFoodBitmap = null;
			}
			break;
		case PLANT_STATE_ATTACK:
			updateAttackBitmap();
			if (mAttackFrmCount == 0)
				mState = CHOMPER_STATE_DIGEST;
			break;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if (mState == CHOMPER_STATE_DIGEST && mFoodBitmap != null){
			Matrix matrix = new Matrix();
			matrix.postScale(scaleX, scaleY);
			matrix.postRotate(90);
			matrix.postTranslate((mPosition.x + mWidth) * scaleX, mPosition.y * scaleY);
			canvas.drawBitmap(mFoodBitmap, matrix, paint);
		}
		super.doDraw(canvas, scaleX, scaleY, paint);
		
	}

}
