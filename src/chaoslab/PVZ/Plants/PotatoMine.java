package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.R;

import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.SoundManager;
import chaoslab.PVZ.Zombies.Zombie;

public class PotatoMine extends Plant{

	public static final int POTATO_MINE_POWER = 500;
	//private static final int ATTACK_RANGE = PlantCells.CELL_WIDTH;
	
	public PotatoMine(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		mHeight = 40;
		mWidth	= 40;
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		boolean isBombed = false;
		ArrayList<Zombie> zombiesInRange = new ArrayList<Zombie>();
		for (int i = 0; i < zombies.size(); ++i){
			Position zombiePosition = zombies.get(i).getPosition();
			if (!zombies.get(i).isInvincible() 
					&& PlantCells.getRow(zombiePosition) == PlantCells.getRow(mPosition)
					&& PlantCells.getCol(zombiePosition) == PlantCells.getCol(mPosition)
				//	&& zombiePosition.x <= mPosition.x + ATTACK_RANGE 
				//	&& zombiePosition.x >= mPosition.x
					 ){
				zombiesInRange.add(zombies.get(i));
				if (GameObject.isCollise(this, zombies.get(i))){
					isBombed = true;
				}
			}
		}
		
		if (isBombed){
			for (int i = 0; i < zombiesInRange.size(); ++i){
				Zombie zombie = zombiesInRange.get(i);
				if (zombie.getHealthPoint() <= POTATO_MINE_POWER)
					zombiesInRange.get(i).setIsCharred(true);
				zombiesInRange.get(i).onHarmed(POTATO_MINE_POWER);
			}
			onDie();
		}
	}
	
	@Override
	public void onDie(){
		super.onDie();
		SoundManager.getInstance().play(R.raw.explosion, 0, 1.0f);
	}
	
	@Override
	public void update(){
		super.update();
		updateWaveBitmap();
	}

}
