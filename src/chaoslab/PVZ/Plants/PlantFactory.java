package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlantFactory {
	public static Plant createSunFlower(Resources res){
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.sunflower_head)),
			};
		return new SunFlower("SUNFLOWER", particles, 50);
	}
	
	public static Plant createPeaShooter(Resources res){
		/*
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.peashooter_head)),
				new Particle(new Position(65, 0), 
						BitmapFactory.decodeResource(res, R.drawable.peashooter_mouth))
			};*/
		Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.peashooter_01)),
			};
		return new PeaShooter("PEASHOOTER", particles, 50, PeaShooter.PEA_SHOOTER_TYPE_NORMAL);
	}
	
	public static Plant createWavingPeaShooter(Resources res){
		Bitmap bitmaps[] = {
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
		WavingPeaShooter shooter = new WavingPeaShooter("WavingPeaShooter", null, 50, PeaShooter.PEA_SHOOTER_TYPE_NORMAL);
		shooter.setWaveBitmaps(bitmaps);
		return shooter;
	}
	
	public static Plant createSnowPeaShooter(Resources res){
		Bitmap bitmaps[] = {
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
		WavingPeaShooter shooter = new WavingPeaShooter("WavingPeaShooter", null, 50, PeaShooter.PEA_SHOOTER_TYPE_SNOW);
		shooter.setWaveBitmaps(bitmaps);
		return shooter;
	}
	
	
	public static Plant createBrain(Resources res){
		return new Brain(BitmapFactory.decodeResource(res, R.drawable.brain), 0);
	}
	
}
