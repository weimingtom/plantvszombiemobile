package chaoslab.PVZ;
/**
 * Each game object is composed by several particles when drawn
 * This class encapsulates doDraw() operation 
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Particle {
	private Position mPosition;	//relative position to its owner
	private Bitmap	mBitmap;
	public Particle(Position position, Bitmap bitmap){
		this.mPosition = position;
		this.mBitmap   = bitmap;
		
	}
	public void doDraw(Canvas canvas, GameObject owner, float scaleX, float scaleY){
		canvas.drawBitmap(Bitmap.createScaledBitmap(mBitmap, (int)(mBitmap.getWidth() * scaleX), (int)(mBitmap.getHeight() * scaleY), true),
				 (owner.getPosition().x + mPosition.x) * scaleX,
				(owner.getPosition().y  + mPosition.y) * scaleY, null);
	}
}
