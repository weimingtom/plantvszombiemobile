package chaoslab.PVZ;

import java.io.Serializable;

/**
 * Position
 * Describes object's position.
 * Note that it is not based on the screen coordinate system,
 * but on the background bitmap.When objects drawn to the screen, 
 * they are scaled, so are their positions.
 * @author Liu.zhenxing
 *
 */
public class Position implements Cloneable, Serializable{
	private static final long serialVersionUID = 2794815527556986996L;
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
	
	/**
	 * return direction vector from source position to destination postion
	 * @param src Position, source position
	 * @param dest Position, dest position
	 * @return
	 */
	public static Position getDirectionVector(Position src, Position dest){
		double length = getDistance(src, dest);  
		if (length < 0.0000001){
			return null;
		}else{
			return new Position((float)((dest.x - src.x) / length), 
					(float)((dest.y - src.y) / length));
		}
	}
	
	public static double getDistance(Position src, Position dest){
		return Math.sqrt((dest.x - src.x) * (dest.x - src.x) 
				+ (dest.y - src.y) * (dest.y - src.y));
	}
}
