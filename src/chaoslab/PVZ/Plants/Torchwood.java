package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;

public class Torchwood extends Plant {

	public Torchwood(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		
	}
	
	public void update(){
		super.update();
		updateWaveBitmap();
	}
}
