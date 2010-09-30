package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Particle;

public class Brain extends Plant {

	public Brain(Particle[] particles, int cost) {
		super("BRAIN", particles, cost);
		mHealthPoint = 100;
		mWidth 		 = 32;
		mHeight		 = 32;
	}
	
}
