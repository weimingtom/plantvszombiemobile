package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Zombies.Zombie;

public class PotatoMine extends Plant{

	public static final int POTATO_MINE_POWER = 250;
	
	public PotatoMine(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		mHeight = 40;
		mWidth	= 40;
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		boolean isBombed = false;
		for (int i = 0; i < zombies.size(); ++i){
			if (GameObject.isCollise(this, zombies.get(i)) && !zombies.get(i).isInvincible()){
				if (zombies.get(i).getHealthPoint() <= POTATO_MINE_POWER)
					zombies.get(i).setIsCharred(true);
				zombies.get(i).onHarmed(POTATO_MINE_POWER);
				isBombed = true;
			}
		}
		if (isBombed)
			onDie();
	}
	
	@Override
	public void onDie(){
		super.onDie();
	}
	
	@Override
	public void update(){
		super.update();
		updateWaveBitmap();
	}

}
