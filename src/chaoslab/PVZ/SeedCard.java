package chaoslab.PVZ;

import android.graphics.Bitmap;
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
	private Bitmap	   mBkgBitmap;
	private Bitmap	   mBitmap;
	private Position   mPosition;
	public static final int SEED_CARD_WIDTH  = 50;
	public static final int SEED_CARD_HEIGHT = 70;
	
	public SeedCard(Position position, GameObject gameObject,Bitmap bkgBitmap, Bitmap bitmap){
		mPosition 	= new Position(position);
		mObject		= gameObject;
		mBkgBitmap 	= bkgBitmap;
		mBitmap		= bitmap; 
	}
	
	public void setPosition(float x, float y){
		mPosition.x = x;
		mPosition.y = y;
	}
	
	public void setPosition(Position position){
		mPosition.x = position.x;
		mPosition.y = position.y;
	}
	
	public Position getPosition(){
		return mPosition;
	}
	
	public Bitmap getBitmap(){
		return mBitmap;
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
		canvas.drawBitmap(mBkgBitmap, null, 
				new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
						(int)((mPosition.x + SEED_CARD_WIDTH) * scaleX),
						(int)((mPosition.y + SEED_CARD_HEIGHT) * scaleY)), paint);
		double startX = mPosition.x  + SEED_CARD_WIDTH * 0.5 - mBitmap.getWidth() * 0.5;
		double startY = mPosition.y + SEED_CARD_HEIGHT * 0.5 - mBitmap.getHeight() * 0.5;
		canvas.drawBitmap(mBitmap, null, 
				new Rect((int)(startX * scaleX), 
						(int)(startY * scaleY),
						(int)((startX + mBitmap.getWidth()) * scaleX),
						(int)((startY + mBitmap.getHeight()) * scaleY)), paint);
		
	}
	
	public boolean isSelected(float x, float y){
		if (mPosition.x < x && mPosition.x + SEED_CARD_WIDTH > x
			&& mPosition.y  < y && mPosition.y + SEED_CARD_HEIGHT > y)
			return true;
		else
			return false;
	}
}
