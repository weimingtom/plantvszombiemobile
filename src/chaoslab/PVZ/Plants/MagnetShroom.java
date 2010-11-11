package chaoslab.PVZ.Plants;

import java.util.ArrayList;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.ZombieItem.AbstractItem;
import chaoslab.PVZ.Zombies.Zombie;

public class MagnetShroom extends Plant {

	private int ATTACK_RANGE = 3;
	public MagnetShroom(String name, Particle[] particles, int cost) {
		super(name, particles, cost);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack(ArrayList<Zombie> zombies) {
		// TODO Auto-generated method stub
		for (int i = 0; i < zombies.size(); ++i){
			Position targetPosition = zombies.get(i).getPosition();
			int targetRow = PlantCells.getRow(targetPosition);
			int targetCol = PlantCells.getCol(targetPosition);
			int row = PlantCells.getRow(mPosition);
			int col = PlantCells.getCol(mPosition);
			/*
			 * check whether zombie  is in the attack range
			 * 7 * 7 cells that magnet is in the center
			 */
			if (row + ATTACK_RANGE  < targetRow
			|| row - ATTACK_RANGE > targetRow
			|| col + ATTACK_RANGE < targetCol 
			|| col - ATTACK_RANGE > targetCol)
				continue;
			AbstractItem item = zombies.get(i).getItem();
			if (item != null && item.canBeMagnetized()){
				zombies.get(i).setItem(null);
				item.setIsMagnetting(true);
				item.setDestPosition(mPosition);
				
			}
		}
	}
	@Override
	public void update(){
		super.update();
		updateWaveBitmap();
	}

}
