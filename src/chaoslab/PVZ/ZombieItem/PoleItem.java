package chaoslab.PVZ.ZombieItem;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class PoleItem extends AbstractItem{
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
		matrix.postTranslate((mPosition.x * scaleX), (mPosition.y * scaleY));
		matrix.postRotate(90);
		matrix.postScale(scaleX,scaleY);
		canvas.drawBitmap(mBitmap[mCurIndex],matrix, paint);	
	}
}