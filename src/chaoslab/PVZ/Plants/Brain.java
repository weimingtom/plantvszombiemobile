package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;

public class Brain extends Plant {

	public Brain(Particle[] particles, int cost) {
		super("BRAIN", particles, cost);
		mHealthPoint = 100;
		mWidth 		 = 32;
		mHeight		 = 32;
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		
	}
	
}
