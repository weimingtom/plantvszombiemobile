package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;

public class UndermineZombie extends Zombie{
	public UndermineZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
		mStatus = GameConstants.ZOMBIE_UNDERGROUND;
		mPreStatus = GameConstants.ZOMBIE_UNDERGROUND;
	}
	public void comeOut(){
		mStatus = GameConstants.ZOMBIE_MOVE;
	}
}