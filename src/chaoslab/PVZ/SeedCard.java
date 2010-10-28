package chaoslab.PVZ;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * SeedCard: Shows in SeedBar,description of its object.Can be dragged to 
 * generate new object at certain cost
 * @author Liu.zhenxing
 *
 */
public class SeedCard {
	private GameObject mObject;
	private Particle   mParticle;
	protected Resources mRes;
	
	public SeedCard(Particle particle, GameObject gameObject,Resources res){
		mParticle 	= particle;
		mObject		= gameObject; 
		mRes = res;
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
		Bitmap seedpacket1 	= BitmapFactory.decodeResource(mRes, R.drawable.seedpacket_larger);
		Bitmap bitmap = Bitmap.createScaledBitmap(seedpacket1, (int)(seedpacket1.getWidth() * 0.5),
				(int)(seedpacket1.getHeight() * 0.5), true);
		canvas.drawBitmap(bitmap, null, 
				new Rect((int)(mParticle.getPosition().x * scaleX), (int)(mParticle.getPosition().y * scaleY),
						(int)((mParticle.getPosition().x + bitmap.getWidth()) * scaleX),
						(int)((mParticle.getPosition().y + bitmap.getHeight()) * scaleY)), paint);
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
