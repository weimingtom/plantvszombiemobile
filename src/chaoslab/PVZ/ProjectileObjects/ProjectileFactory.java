package chaoslab.PVZ.ProjectileObjects;

import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ProjectileFactory {
	public static final int POWER_PEA 		= 5;
	public static final double SPEED_PEA 	= 30.0;
	public static GameObject createProjectilePea(Resources res){
		/*Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.projectilepea)),
			};
		*/
		Bitmap bitmaps[] = {
				BitmapFactory.decodeResource(res, R.drawable.projectilepea),
				BitmapFactory.decodeResource(res, R.drawable.projectilesnowpea),
				BitmapFactory.decodeResource(res, R.drawable.projectilepea)
		};
		ProjectilePea projectilePea = new ProjectilePea("PROJECTILE_PEA", bitmaps, ProjectilePea.PEA_TYPE_NORMAL);
		projectilePea.setPower(POWER_PEA);
		projectilePea.setMoveSpeed(SPEED_PEA);
		return projectilePea;
	}
}
