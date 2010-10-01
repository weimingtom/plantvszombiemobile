package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.PlantVsZombieView;

public class SunFlower extends Plant{
	private PlantVsZombieView mView;
	public SunFlower(String name, Particle particles[], int cost) {
		super(name, particles, cost);
	}
	
	public void setView(PlantVsZombieView view){
		mView = view;
	}
	
	@Override
	public void ate(int harmPoint){
		super.ate(harmPoint);
		if (mView != null){
			mView.addSunshines(100);
		}
	}
}
