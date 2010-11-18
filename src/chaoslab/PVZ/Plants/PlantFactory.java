package chaoslab.PVZ.Plants;
import android.content.res.Resources;

public class PlantFactory {
	private static PlantFactory mInstance = new PlantFactory();
	private PlantFactory(){
	}
	
	public static PlantFactory getInstance(){
		return mInstance;
	}
	/**
	 * called to init bitmaps before creating plants.
	 * @param res
	 */
	public void init(Resources res){
		Brain.initBitmaps(res);
		Chomper.initBitmaps(res);
		LeafBunch.initBitmaps(res);
		MagnetShroom.initBitmaps(res);
		SunFlower.initBitmaps(res);
		//PeaShooter.initBitmaps(res);
		PotatoMine.initBitmaps(res);
		Torchwood.initBitmaps(res);
		WavingPeaShooter.initBitmaps(res);
	}
	
	public Plant createSunFlower(){
		return new SunFlower("SUNFLOWER", null, 50);
	}
	
	public Plant createWavingPeaShooter(){
		return new WavingPeaShooter("WavingPeaShooter", 
				null, 50, PeaShooter.PEA_SHOOTER_TYPE_NORMAL);
		
	}
	
	public Plant createSnowPeaShooter(){
		return new WavingPeaShooter("WavingPeaShooter", 
				null, 50, PeaShooter.PEA_SHOOTER_TYPE_SNOW);
	}
	
	public Plant createChomper(){
		return new Chomper("CHOMPER", null, 150);
	}
	
	public Plant createLeafBunch(){
		return new LeafBunch("LeafBunch", null, 100);
	}
	
	public Plant createBrain(){
		return new Brain("Brain", null,  0);
	}
	
	public  Plant createTorchwood(){
		
		Torchwood torchwood = new Torchwood("Torchwood", null, 150);
		return torchwood;
	}
	
	public Plant createPotatoMine(){
		
		PotatoMine potatoMine = new PotatoMine("PotatoMine", null, 25);
		
		return potatoMine;
	}
	public Plant createMagnetShroom(){
		MagnetShroom magnet = new MagnetShroom("Magnetshroom", null, 100);
		return magnet;
	}
}
