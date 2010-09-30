package chaoslab.PVZ.Plants;

import android.util.Log;
import chaoslab.PVZ.Position;

public class PlantCells {
	public	final Position ORIGIN = new Position(0, 80);
	public  final int MAX_COL_NUM 	= 6;
	public  final int MAX_ROW_NUM	= 5;
	public  final int CELL_WIDTH 	= 80;
	public	final int CELL_HEIGHT 	= 100;
	private Plant mPlants[][];
	
	public PlantCells(){
		mPlants = new Plant[MAX_ROW_NUM][MAX_COL_NUM];
	}
	
	public void setPlant(int row, int col, Plant plant){
		plant.SetPosition((int)ORIGIN.x + col * CELL_WIDTH, (int)ORIGIN.y + row * CELL_HEIGHT + CELL_HEIGHT - plant.getHeight());
		mPlants[row][col] = plant;
	}
	
	public Plant[] getRow(int row){
		if (row >=0 && row < MAX_ROW_NUM)
			return mPlants[row];
		else 
			return null;
	}
	
	public Plant getPlant(int row, int col){
		
		if (row >= 0 && row < MAX_ROW_NUM && col >= 0 && col < MAX_COL_NUM)
		{
			return mPlants[row][col];
		}
		else
			return null;
	}
	
	public Plant getPlant(Position position){
		return getPlant((int)(position.y - ORIGIN.y) / CELL_HEIGHT, (int)(position.x - ORIGIN.x) / CELL_WIDTH); 
	}
	
	/**
	 * check each plant to remove dead one, 
	 */
	public void update(){
		for (int i = 0; i < MAX_ROW_NUM; ++i){
			for (int j = 0; j < MAX_COL_NUM; ++j){
				if (mPlants[i][j] != null && !mPlants[i][j].isAlive()){
					mPlants[i][j] = null;
				}
			}
		}
	}
	
	
}
