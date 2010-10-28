package chaoslab.PVZ.ProjectileObjects;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class ProjectileFactory {
	public static final int POWER_PEA 		= 5;
	public static final double SPEED_PEA 	= 10.0;
	public static ProjectileObject createProjectilePea(Resources res){
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.projectilepea)),
			};
		
		ProjectilePea projectilePea = new ProjectilePea("PROJECTILE_PEA", particles);
		projectilePea.setPower(POWER_PEA);
		projectilePea.setMoveSpeed(SPEED_PEA);
		return projectilePea;
	}
}
