package chaoslab.PVZ;
/**
 * Used to describe object's position
 * Note that it is not based on the screen coordinate system,
 * but on the background bitmap.When objects drawn to the screen, 
 * they are scaled, so are their positions.
 * @author Liu.zhenxing
 *
 */
public class Position implements Cloneable{
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
	@Override
	public Object clone() throws CloneNotSupportedException{
		Position position = (Position)super.clone();
		position.x  	  = this.x;
		position.y 		  = this.y;
		return position;
	}
}
