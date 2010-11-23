package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import chaoslab.PVZ.ProjectileObjects.ProjectileFactory;
import chaoslab.PVZ.ProjectileObjects.ProjectilePea;
import chaoslab.PVZ.Zombies.Zombie;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PeaShooter extends Plant{
	private static Bitmap[] 	mWaveBitmaps;
	private static Bitmap[] 	mAttackBitmaps;
	/** indicates a minimum attack interval between two attacks*/
	protected int mAttackCoolDown = 20;
	/** A counterdown of attack cool down.*/
	protected int mAttackCoolDownCnt;
	protected int mFaceTo		  = 1; /** 1 indicates right while -1 indicates left*/
	
	public static final int PEA_SHOOTER_TYPE_NORMAL = 0;
	public static final int PEA_SHOOTER_TYPE_SNOW	= 1;
	protected int mType = PEA_SHOOTER_TYPE_NORMAL;
	private ProjectilePea  mPea;
	public PeaShooter(String name, Particle[] particles, int cost, int type) {
		super(name, particles, cost);
		mType 	= type;
		mAttackFrame = mAttackBitmaps.length * WAVE_INTERVAL;
		mAttackCoolDownCnt = 0;
	}
	
	@Override
	public void attack(ArrayList<Zombie> zombies){
		if (mAttackCoolDownCnt != 0){
			mAttackCoolDownCnt--;
		}else{
			boolean isAttacked = false;
			for (int i = 0; i < zombies.size() && !isAttacked; ++i){
				Position zombiePosition = zombies.get(i).getPosition();
				if (!zombies.get(i).isInvincible() 
						&& PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)
						&& (zombiePosition.x - mPosition.x) * mFaceTo > 0){
					isAttacked = true;
					mAttackCoolDownCnt = mAttackCoolDown;
					mState = PLANT_STATE_ATTACK;
					mPea = (ProjectilePea)ProjectileFactory.getInstance().createProjectilePea();
					if (mType == PEA_SHOOTER_TYPE_SNOW){
						mPea.setType(ProjectilePea.PEA_TYPE_SNOW);
					}
					mPea.setPosition(mPosition.x + mWidth, mPosition.y);
					if (zombiePosition.x > mPosition.x){
						mPea.setMoveAngle(0);
					}else{
						mPea.setMoveAngle(Math.PI);
					}
						
				}
			}
		}
	}
	@Override
	public void update(){
		super.update();
		switch (mState){
		case PLANT_STATE_ATTACK:
			updateAttackBitmap(mAttackBitmaps);
			if (mGameEventListener != null){
			if (mAttackFrmCnt == 12)
				mGameEventListener.onProjectileObjectCreated(mPea);
			}
			if (mAttackFrmCnt == 0)
				mState = PLANT_STATE_WAVE;
			break;
		default:
			updateWaveBitmap(mWaveBitmaps);
			break;
		}
		/*updateWaveBitmap(mWaveBitmaps);
		if (mState == Plant.PLANT_STATE_WAVE){
			mCurBitmap = mWaveBitmaps[curWaveImgNum];
		}else {
			if (mState == Plant.PLANT_STATE_ATTACK){
				updateAttackBitmap(mAttackBitmaps);
				mCurBitmap = mAttackBitmaps[curAttackImgNum];
			}else{
				mCurBitmap = mWaveBitmaps[curWaveImgNum];
			}
		}*/
		
	}
	
	public static void initBitmaps(Resources res){
		mWaveBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.peashooter_01),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_02),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_03),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_04),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_05),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_06),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_07),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_08),
				BitmapFactory.decodeResource(res, R.drawable.peashooter_09),
		};
		mAttackBitmaps = new Bitmap[]{
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_00),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_01),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_02),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_03),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_04),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_05),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_06),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_07),
				BitmapFactory.decodeResource(res, R.drawable.pea_atk_08),
		};
	}
}
