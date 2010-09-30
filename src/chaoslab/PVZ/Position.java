package chaoslab.PVZ;

public class Position {
	public float x;
	public float y;
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Position(final Position position){
		this.x = position.x;
		this.y = position.y;
	}
	
	public void add(final Position position){
		x += position.x;
		y += position.y;
	}
	
	public String toString(){
		return "(" + this.x + " ," + this.y + " )";
	}
}
