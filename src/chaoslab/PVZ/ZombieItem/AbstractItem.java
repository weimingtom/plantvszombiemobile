package chaoslab.PVZ.ZombieItem;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import chaoslab.PVZ.GameObject;
import android.graphics.Bitmap;
import chaoslab.PVZ.Position;

public class AbstractItem extends GameObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7886035731874533962L;
	protected boolean bmagnetized = false;
	protected boolean canDef = false;
	protected transient Bitmap  mBitmap[];
	protected int     mCurIndex = 0;
	protected boolean bDrop = false;
	protected boolean bFading = false;
	protected int     nFadeCnt = 0;
	protected boolean mIsMagnetting = false;
	protected Position mDestPosition;
	private int mMoveFrm;
	private int mMoveFrmCnt;
	
	public AbstractItem(Bitmap[] bitmap){
		super("",null, 0);
		mBitmap = bitmap;
		setMoveSpeed(40);
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
		if(mCurIndex >= mBitmap.length){
			mCurIndex = 0;
		}
		canvas.drawBitmap(mBitmap[mCurIndex], null, 
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
		if (mIsMagnetting){
			if (mMoveFrmCnt < mMoveFrm){
				move();
			}
			else{
				mPosition = mDestPosition;
				mIsMagnetting = false;
				bFading = true;
				nFadeCnt = 0;
			}
			mMoveFrmCnt ++;
		}
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		AbstractItem obj = (AbstractItem)super.clone();
		obj.init();
		return obj;
	}
	public void init(){
		mCurIndex = 0;
		bDrop = false;
		bFading = false;
		nFadeCnt = 0;
		mHealthPoint = 50;
		mPosition = new Position(0,0);
	}
	
	public void setDestPosition(Position destPosition){
		mDestPosition = destPosition;
		mMoveFrm	  = (int)(Position.getDistance(mPosition, destPosition) / mMoveSpeed);
		setMoveDirection(Position.getDirectionVector(mPosition, destPosition));
	}
	
	public void setIsMagnetting(boolean flag){
		mIsMagnetting = flag;
		if (flag){
			mMoveFrmCnt = 0;
			bFading = false;
			ItemFactory.addItem(this);
		}
	}
}

