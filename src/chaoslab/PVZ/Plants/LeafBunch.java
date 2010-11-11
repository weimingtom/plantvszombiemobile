package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.Zombies.BungeeZombie;
import chaoslab.PVZ.Zombies.Zombie;

public class LeafBunch extends Plant {
	/**
	 * the leafbunch can protect 3 * 3 cells
	 */
	private static final int PROTECT_RANGE = 1;
	
	public LeafBunch(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		switch(mState){
		case PLANT_STATE_WAVE:
			for (int i = 0; i < zombies.size(); ++i){
				if (zombies.get(i) instanceof BungeeZombie){
					BungeeZombie zombie = (BungeeZombie)zombies.get(i);
					if (zombie.getTarget() != null && zombie.getStatus() == GameConstants.ZOMBIE_FROZON){
						Position targetPosition = zombie.getTarget().getPosition();
						int targetRow = PlantCells.getRow(targetPosition);
						int targetCol = PlantCells.getCol(targetPosition);
						int row = PlantCells.getRow(mPosition);
						int col = PlantCells.getCol(mPosition);
						/*
						 * check whether zombie's target is in the protecting range
						 * 3 * 3 cells that leafBunch is in the center
						 */
						if (row + PROTECT_RANGE < targetRow
						|| row - PROTECT_RANGE > targetRow
						|| col + PROTECT_RANGE < targetCol 
						|| col - PROTECT_RANGE > targetCol)
							continue;
						zombie.setStatus(GameConstants.ZOMBIE_BACKWARD);
						mState = PLANT_STATE_ATTACK;
					}
				}
			}
			break;
		default:
			break;
		}

	}
	
	@Override
	public void update(){
		super.update();
		switch (mState){
		case PLANT_STATE_WAVE:
			if (mWaveBitmaps != null){
				updateWaveBitmap();
			}
			break;
		case PLANT_STATE_ATTACK:
			updateAttackBitmap();
			if (mAttackFrmCount == 0)
				mState = PLANT_STATE_WAVE;
			break;
		}
	}
}
