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

	private static final int CHOMPER_STATE_NORMAL 		= 0;
	private static final int CHOMPER_STATE_ATTACK 		= 1;
	private static final int CHOMPER_STATE_DIGEST 		= 2;
	private static final int CHOMPER_DIGEST_FRAME 		= 100;
	
	private int		mState	= CHOMPER_STATE_NORMAL;
	private static final int ATTACK_FRAME = 10;
	private int     mDigestFrmCount   = 0;
	private int		mAttackFrmCount	  = 0;
	private Bitmap	mFoodBitmap;
	public Chomper(String name, Particle[] particles, int cost) {
		super(name, null, cost);
		mWidth = 64;
		mHeight = 80;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		switch (mState){
		case CHOMPER_STATE_NORMAL:
			for (int i = 0; i < zombies.size(); ++i){
				Position zombiePosition = zombies.get(i).getPosition();
				if (!zombies.get(i).isInvincible() 
						&& PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)
						&& PlantCells.getCol(zombiePosition)	== PlantCells.getCol(mPosition)	
						){
					mFoodBitmap = Bitmap.createBitmap(zombies.get(i).getBitmap());
					mState = CHOMPER_STATE_ATTACK;
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
		case CHOMPER_STATE_NORMAL:
			if (mWaveBitmaps != null){
				if (mElapsedFrame % WAVE_INTERVAL == 0){
					curWaveImgNum ++;
					if (curWaveImgNum >= mWaveBitmaps.length){
						curWaveImgNum = 0;
					}
				}
				mCurBitmap = mWaveBitmaps[curWaveImgNum];
			}
			break;
		case CHOMPER_STATE_DIGEST:
			mDigestFrmCount++;
			if (mDigestFrmCount >= CHOMPER_DIGEST_FRAME){
				mDigestFrmCount = 0;
				mState = CHOMPER_STATE_NORMAL;
				mFoodBitmap = null;
			}
			break;
		case CHOMPER_STATE_ATTACK:
			mAttackFrmCount++;
			if (mAttackFrmCount > ATTACK_FRAME){
				mAttackFrmCount = 0;
				mState = CHOMPER_STATE_DIGEST;
			}
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
