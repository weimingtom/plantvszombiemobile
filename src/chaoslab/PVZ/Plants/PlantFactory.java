package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PlantFactory {
	public static Plant createSunFlower(Resources res){
		/*Particle particles[] = {
				new Particle(new Position(0, 0), 
						BitmapFactory.decodeResource(res, R.drawable.sunflower_head)),
			};*/
		Bitmap bitmaps[] = {
				BitmapFactory.decodeResource(res, R.drawable.sunflower00),
				BitmapFactory.decodeResource(res, R.drawable.sunflower01),
				BitmapFactory.decodeResource(res, R.drawable.sunflower02),
				BitmapFactory.decodeResource(res, R.drawable.sunflower03),
				BitmapFactory.decodeResource(res, R.drawable.sunflower04),
				BitmapFactory.decodeResource(res, R.drawable.sunflower05),
				BitmapFactory.decodeResource(res, R.drawable.sunflower06),
				BitmapFactory.decodeResource(res, R.drawable.sunflower07),
				BitmapFactory.decodeResource(res, R.drawable.sunflower08),
		};
		SunFlower sunFlower = new SunFlower("SUNFLOWER", null, 50);
		sunFlower.setWaveBitmaps(bitmaps);
		return sunFlower;
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
	
	public static Plant createChomper(Resources res){
		Bitmap bitmaps[] = {
			BitmapFactory.decodeResource(res, R.drawable.chomper_topjaw),
		};
		Chomper chomper = new Chomper("CHOMPER", null, 150);
		chomper.setWaveBitmaps(bitmaps);
		return chomper;
	}
	
	public static Plant createLeafBunch(Resources res){
		Bitmap bitmaps[] = {
			BitmapFactory.decodeResource(res, R.drawable.leafbunch1),	
		};
		LeafBunch leafBunch = new LeafBunch("LeafBunch", null, 100);
		leafBunch.setWaveBitmaps(bitmaps);
		return leafBunch;
	}
	
	public static Plant createBrain(Resources res){
		return new Brain(BitmapFactory.decodeResource(res, R.drawable.brain), 0);
	}
	
	public static Plant createTorchwood(Resources res){
		Bitmap bitmaps[] = {
			BitmapFactory.decodeResource(res, R.drawable.torchwood_body),	
		};
		Torchwood torchwood = new Torchwood("Torchwood", null, 150);
		torchwood.setWaveBitmaps(bitmaps);
		return torchwood;
	}
	
	public static Plant createPotatoMine(Resources res){
		Bitmap bitmaps[] = {
				BitmapFactory.decodeResource(res, R.drawable.potatomine_body),	
			};
		PotatoMine potatoMine = new PotatoMine("PotatoMine", null, 25);
		potatoMine.setWaveBitmaps(bitmaps);
		return potatoMine;
	}
	public static Plant createMagnetShroom(Resources res){
		Bitmap bitmaps[] = {
			BitmapFactory.decodeResource(res, R.drawable.magnetshroom_head1),	
		};
		MagnetShroom magnet = new MagnetShroom("Magnetshroom", null, 100);
		magnet.setWaveBitmaps(bitmaps);
		return magnet;
	}
}
