package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;

public class SunFlower extends Plant{
	
	public SunFlower(String name, Particle particles[], int cost) {
		super(name, particles, cost);
	}
	
	@Override
	public void onHarmed(int harmPoint){
		super.onHarmed(harmPoint);
		if (mView != null){
			mView.addSunshines(50);
		}
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// do nothing
		
	}
	
	public void update(){
		super.update();
		updateWaveBitmap();
	}
}
