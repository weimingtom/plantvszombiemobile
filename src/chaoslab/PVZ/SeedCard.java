package chaoslab.PVZ;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * SeedCard: Shows in SeedBar,description of its object.Can be dragged to 
 * generate new object at certain cost
 * @author Liu.zhenxing
 *
 */
public class SeedCard {
	private GameObject mObject;
	private Particle   mParticle;
	
	public SeedCard(Particle particle, GameObject gameObject){
		mParticle 	= particle;
		mObject		= gameObject; 
	}
	
	public void setPosition(float x, float y){
		mParticle.setPosition(x, y);
	}
	
	public void setPosition(Position position){
		mParticle.setPosition(position);
	}
	
	public Position getPosition(){
		return mParticle.getPosition();
	}
	
	public Bitmap getBitmap(){
		return mParticle.getBitmap();
	}
	
	public int getCost(){
		if (mObject != null)
			return mObject.getCost();
		else
			return 0;
	}
	
	public GameObject getObject(){
		return mObject;
	}
	
	
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		mParticle.doDraw(canvas, null, scaleX, scaleY, paint);
	}
	
	public boolean isSelected(float x, float y){
		if (getPosition().x < x && getPosition().x + getBitmap().getWidth() > x
			&& getPosition().y  < y && getPosition().y + getBitmap().getHeight() > y)
			return true;
		else
			return false;
	}
}
