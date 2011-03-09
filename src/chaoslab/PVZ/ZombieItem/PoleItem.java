package chaoslab.PVZ.ZombieItem;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import chaoslab.PVZ.Position;

public class PoleItem extends AbstractItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7663313814603295801L;
	public PoleItem(Bitmap[] bitmap){
		super(bitmap);
		canDef = false;
		bmagnetized = false;
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if(mBitmap ==null){
			return;
		}
		if(mCurIndex >= mBitmap.length){
			mCurIndex = 0;
		}
		Matrix matrix = new Matrix();
		Position pos = new Position(mPosition);
		if(!bDrop){
			pos.y = mPosition.y - mBitmap[mCurIndex].getWidth();
			matrix.postRotate(90);	
		}else{		
		}
		matrix.postScale(scaleX,scaleY);
		matrix.postTranslate(pos.x* scaleX, pos.y * scaleY);
		canvas.drawBitmap(mBitmap[mCurIndex],matrix, paint);	
	}
}