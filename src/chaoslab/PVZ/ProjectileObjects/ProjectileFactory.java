package chaoslab.PVZ.ProjectileObjects;

import chaoslab.PVZ.GameObject;
import android.content.res.Resources;

public class ProjectileFactory {
	public static final int POWER_PEA 		= 5;
	public static final double SPEED_PEA 	= 30.0;
	private static ProjectileFactory mInstance = new ProjectileFactory();
	
	public static ProjectileFactory getInstance(){
		return mInstance;
	}
	
	public void init(Resources res){
		ProjectilePea.initBitmaps(res);
	}
	public GameObject createProjectilePea(){
		/*Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.projectilepea)),
			};
		*/
		ProjectilePea projectilePea = new ProjectilePea("PROJECTILE_PEA", ProjectilePea.PEA_TYPE_NORMAL);
		projectilePea.setPower(POWER_PEA);
		projectilePea.setMoveSpeed(SPEED_PEA);
		return projectilePea;
	}
}
