/**
 * Chomper
 * One of the most powerful plants!
 * @author Liu.zhenxing
 */
package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import chaoslab.PVZ.Zombies.Zombie;

public class Chomper extends Plant{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2772482567235627357L;
	private static Bitmap[] mWaveBitmaps;
	private static Bitmap[] mAttackBitmaps;
	
	private static final int CHOMPER_STATE_DIGEST 		= PLANT_STATE_SPECIAL_ACTION;
	private static final int CHOMPER_DIGEST_FRAME 		= 100;
	private static final int ATTACK_FRAME 				= 10;
	
	private static final float ATTACK_RANGE		=  PlantCells.CELL_WIDTH;
	private int     mDigestFrmCount   			= 0;
	private transient Bitmap	mFoodBitmap;
	private Zombie mTarget;
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
						&& zombiePosition.x >= mPosition.x + mWidth * 0.5f
						&& zombiePosition.x <= mPosition.x + mWidth + ATTACK_RANGE
						){
					mFoodBitmap = Bitmap.createBitmap(zombies.get(i).getBitmap());
					mState = PLANT_STATE_ATTACK;
					mTarget = zombies.get(i);
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
			updateWaveBitmap(mWaveBitmaps);
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
			updateAttackBitmap(mAttackBitmaps);
			if (mAttackFrmCnt == 0){
				mState = CHOMPER_STATE_DIGEST;
				if (mTarget == null || !mTarget.isAlive()){
					mFoodBitmap = null;
				}else{
					mTarget.onDie();
				}
			}
				
			break;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if (mState == CHOMPER_STATE_DIGEST &&  mFoodBitmap != null){
			Matrix matrix = new Matrix();
			matrix.postScale(scaleX, scaleY);
			matrix.postRotate(90);
			matrix.postTranslate((mPosition.x + mWidth) * scaleX, mPosition.y * scaleY);
			canvas.drawBitmap(mFoodBitmap, matrix, paint);
		}
		super.doDraw(canvas, scaleX, scaleY, paint);
		
	}
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.chomper_topjaw),
		};
		mAttackBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_00),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_01),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_02),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_03),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_04),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_05),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_06),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_07),
				BitmapFactory.decodeResource(res, R.drawable.chomper_eat_08),
		};
	}

}
