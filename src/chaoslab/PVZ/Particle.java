package chaoslab.PVZ;
/**
 * Each game object is composed by several particles when drawn
 * This class encapsulates doDraw() operation 
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Particle implements Cloneable{
	private Position mPosition;	//relative position to its owner
	private Bitmap	mBitmap;
	public Particle(Position position, Bitmap bitmap){
		this.mPosition = position;
		this.mBitmap   = bitmap;
		
	}
	
	/**
	 * Set the center of Particle
	 * @param x:center x
	 * @param y:center y
	 */
	public void setCenter(float x, float y){
		mPosition.x = x - 0.5f * mBitmap.getWidth();
		mPosition.y = y - 0.5f * mBitmap.getHeight();
	}
	
	public void setPosition(float x, float y){
		mPosition.x = x;
		mPosition.y = y;
	}
	
	public void setPosition(Position position){
		mPosition = position;
	}
	
	public Position getPosition(){
		return mPosition;
	}
	
	public Bitmap getBitmap(){
		return mBitmap;
	}
	
	public void doDraw(Canvas canvas, GameObject owner, float scaleX, float scaleY, Paint paint){
		/** absolute x,y stands for the real coordinate */
		float absoluteX, absoluteY;
		if (owner != null){
			absoluteX = owner.getPosition().x + mPosition.x;
			absoluteY = owner.getPosition().y + mPosition.y;
		}else{
			absoluteX = mPosition.x;
			absoluteY = mPosition.y;
		}
		
		canvas.drawBitmap(mBitmap, null, 
				new Rect((int)(absoluteX * scaleX), (int)(absoluteY * scaleY),
						(int)((absoluteX + mBitmap.getWidth()) * scaleX),
						(int)((absoluteY + mBitmap.getHeight()) * scaleY)), paint);
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Particle particle = (Particle)super.clone();
		particle.mBitmap = Bitmap.createBitmap(this.mBitmap);
		return particle;
	}
}
