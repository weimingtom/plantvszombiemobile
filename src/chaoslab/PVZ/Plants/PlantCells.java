package chaoslab.PVZ.Plants;

import chaoslab.PVZ.Position;

public class PlantCells {
	public static final Position ORIGIN = new Position(0, 80);
	public static final int MAX_COL_NUM 	= 6;
	public static final int MAX_ROW_NUM		= 5;
	public static final int CELL_WIDTH 		= 80;
	public static final int CELL_HEIGHT 	= 95;
	private Plant mPlants[][];
	
	public PlantCells(){
		mPlants = new Plant[MAX_ROW_NUM][MAX_COL_NUM];
	}
	
	public void setPlant(int row, int col, Plant plant){
		plant.setPosition((int)ORIGIN.x + col * CELL_WIDTH, (int)ORIGIN.y + row * CELL_HEIGHT + CELL_HEIGHT - plant.getWidth());
		mPlants[row][col] = plant;
	}
	
	/*public Plant[] getRow(int row){
		if (row >=0 && row < MAX_ROW_NUM)
			return mPlants[row];
		else 
			return null;
	}*/
	public static int getRow(final Position position){
		return (int)((position.y - ORIGIN.y ) / CELL_HEIGHT);
	}
	
	public static int getCol(final Position position){
		return (int)((position.x - ORIGIN.x) / CELL_WIDTH);
	}
	
	public Plant getPlant(int row, int col){
		
		if (row >= 0 && row < MAX_ROW_NUM && col >= 0 && col < MAX_COL_NUM){
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
