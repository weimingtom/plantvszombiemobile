package chaoslab.PVZ.Plants;

import chaoslab.PVZ.ProjectileObjects.ProjectileFactory;
import chaoslab.PVZ.ProjectileObjects.ProjectilePea;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.Zombies.Zombie;

public class PeaShooter extends Plant {
	protected int mAttackFrame	  = 0;
	protected int mAttackInterval = 20;
	protected int mFaceTo		  = 1; /** 1 indicates right while -1 indicates left*/
	
	public static final int PEA_SHOOTER_TYPE_NORMAL = 0;
	public static final int PEA_SHOOTER_TYPE_SNOW	= 1;
	protected int mType = PEA_SHOOTER_TYPE_NORMAL;
	
	public PeaShooter(String name, Particle[] particles, int cost, int type) {
		super(name, particles, cost);
		mType 	= type;
		mWidth 	= 80;
		mHeight = 80;
		mHealthPoint = 100;
		mAttackFrame = mAttackInterval;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(ArrayList<Zombie> zombies){
		
		boolean isAttacked = false;
		for (int i = 0; i < zombies.size() && !isAttacked; ++i){
			Position zombiePosition = zombies.get(i).getPosition();
			if (mAttackFrame >= mAttackInterval && 
					PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)
					&& (zombiePosition.x - mPosition.x) * mFaceTo > 0){
				isAttacked = true;
				mAttackFrame = 0;
				
				if (mView != null){
					ProjectilePea projectilePea = (ProjectilePea)ProjectileFactory.createProjectilePea(mView.getResources());
					if (mType == PEA_SHOOTER_TYPE_SNOW){
						projectilePea.setType(ProjectilePea.PEA_TYPE_SNOW);
					}
					projectilePea.setPosition(mPosition.x + mWidth, mPosition.y);
					if (zombiePosition.x > mPosition.x){
						projectilePea.setMoveAngle(0);
					}else{
						projectilePea.setMoveAngle(Math.PI);
					}
					mView.addProjectileObject(projectilePea);
					
				}
			}
			
		}
		
		mAttackFrame++;
		
	}

}
