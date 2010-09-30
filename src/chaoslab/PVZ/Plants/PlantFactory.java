package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class PlantFactory {
	public static Plant createSunFlower(Resources res){
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.zombie_head)),
				new Particle(new Position(32, 40), 
						BitmapFactory.decodeResource(res, R.drawable.zombie_body)),	
			};
		return new SunFlower("SUNFLOWER", particles, 50);
	}
	public static Plant createBrain(Resources res){
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.brain)),	
			};
		return new Brain(particles, 0);
	}
	
}
