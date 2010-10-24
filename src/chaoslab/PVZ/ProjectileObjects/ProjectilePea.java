package chaoslab.PVZ.ProjectileObjects;

import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;

public class ProjectilePea extends ProjectileObject {
	public ProjectilePea(String name, Particle[] particles) {
		super(name, particles);
		// TODO Auto-generated constructor stub
		mWidth 	= 28;
		mHeight = 28;
	}

	@Override
	public void onHit(GameObject target) {
		if (target.getStand() == GameConstants.STAND_ZOMBIE){
			target.addHealthPoint(-1 * mPower);
			onDie();
		}
	}

}
