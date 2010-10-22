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
						BitmapFactory.decodeResource(res, R.drawable.sunflower_head)),
			};
		return new SunFlower("SUNFLOWER", particles, 50);
	}
	
	public static Plant createPea(Resources res){
		/*
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.peashooter_head)),
				new Particle(new Position(65, 0), 
						BitmapFactory.decodeResource(res, R.drawable.peashooter_mouth))
			};*/
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.sunflower_head)),
			};
		return new PeaShooter("PEASHOOTER", particles, 50);
	}
	
	public static Plant createBrain(Resources res){
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.brain)),	
			};
		return new Brain(particles, 0);
	}
	
}
