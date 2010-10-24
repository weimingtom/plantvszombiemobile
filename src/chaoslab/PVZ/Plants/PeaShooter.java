package chaoslab.PVZ.Plants;

import chaoslab.PVZ.ProjectileObjects.ProjectileFactory;
import chaoslab.PVZ.ProjectileObjects.ProjectilePea;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.Zombies.Zombie;

public class PeaShooter extends Plant {
	protected int mAttackFrame 	 = 60;
	protected int mAttackInterval = 60;
	public PeaShooter(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		mWidth 	= 80;
		mHeight = 80;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack(ArrayList<Zombie> zombies){
		
		boolean isAttacked = false;
		for (int i = 0; i < zombies.size() && !isAttacked; ++i){
			Position zombiePosition = zombies.get(i).getPosition();
			if (mAttackFrame >= mAttackInterval && 
					PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)){
				isAttacked = true;
				mAttackFrame = 0;
				
				if (mView != null){
					ProjectilePea projectilePea = (ProjectilePea)ProjectileFactory.createProjectilePea(mView.getResources());
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
