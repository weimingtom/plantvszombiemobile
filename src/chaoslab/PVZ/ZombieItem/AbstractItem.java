package chaoslab.PVZ.ZombieItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import chaoslab.PVZ.GameObject;
import android.graphics.Bitmap;
import chaoslab.PVZ.Position;

public class AbstractItem extends GameObject{
	protected boolean bmagnetized = false;
	protected boolean canDef = false;
	protected Bitmap  mBitmap[];
	protected int     mCurBitmap = 0;
	protected boolean bDrop = false;
	protected boolean bFading = false;
	protected int     nFadeCnt = 0;
	
	public AbstractItem(Bitmap[] bitmap){
		super("",null,0);
		mBitmap = bitmap;
	}
	public void doAction(){
		
	}
	public boolean canBeMagnetized(){
		return bmagnetized;
	}
	public boolean canDefence(){
		return canDef;
	}
	public void droped(Position pos){
		mPosition.x = pos.x;
		mPosition.y = pos.y;
		bDrop = true;
		bFading = true;
		nFadeCnt = 0;
		ItemFactory.addItem(this);
	}
	public void moveTo(Position pos){
		mPosition.x = pos.x;
		mPosition.y = pos.y;
	}
	public int protect(int delta){
		if(canDef)
		{
		  mHealthPoint -= delta;
		  if(mHealthPoint >= 0){
			  return 0;
		  }else{
			  return -mHealthPoint;
		  }
		}else{
			return delta;
		}
	}
	@Override
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		if(mBitmap ==null){
			return;
		}
		if(mCurBitmap >= mBitmap.length){
			mCurBitmap = 0;
		}
		canvas.drawBitmap(mBitmap[mCurBitmap], null, 
				new Rect((int)(mPosition.x * scaleX),
						(int)(mPosition.y * scaleY),
						(int)((mPosition.x + mBitmap[0].getWidth()) * scaleX),
						(int)((mPosition.y + mBitmap[0].getHeight()) * scaleY)), paint);	
	}
	@Override
	public void update(){
		//TODO: maybe change picture according to health point here
		if(nFadeCnt >= 10){
			onDie();
		}
		if(bFading){
			nFadeCnt ++;
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		AbstractItem obj = (AbstractItem)super.clone();
		obj.init();
		return obj;
	}
	public void init(){
		mCurBitmap = 0;
		bDrop = false;
		bFading = false;
		nFadeCnt = 0;
		mHealthPoint = 50;
	}
}

