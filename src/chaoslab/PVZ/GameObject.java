package chaoslab.PVZ;
/**
 * Base class of Plants and Zombies
 */
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.GameConstants;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

abstract public class GameObject implements Cloneable{

	public static final int MAX_POSITION_X = 800;
	public static final int MAX_POSITION_Y = 600;
	
	protected String 	mName;
	protected int		mCost;
	protected int 		mHealthPoint = 50;
	protected int		mElapsedFrame;
	protected boolean	mIsAlive;
	protected Position	mPosition;
	/** TODO: change this to ArrayList to support animation*/
	protected Particle  mParticles[];
	protected Bitmap    mCurBitmap;
	/** Actual size of this object*/
	protected int		mWidth 	= 0;
	protected int		mHeight = 0;
	protected PlantVsZombieView mView;
	/** Indicate this object stands by which side(Plant or Zombie or neither?)*/
	protected	  int		mStand	= GameConstants.STAND_NONE;
	
	public GameObject(String name, Particle particles[], int cost){
		setName(name);
		if (particles != null){
			mParticles = new Particle[particles.length];
			for (int i = 0; i < particles.length; ++i){
				mParticles[i]	= particles[i];
			}
		}
		mCost			= cost;
		mElapsedFrame	= 0;
		mIsAlive		= true;
		mPosition		= new Position(0, 0);
		mView			= null;
		
	}
	
	public void setPosition(float posX, float posY){
		mPosition.x = posX;
		mPosition.y = posY;
	}
	
	public void setCenterPosition(float posX, float posY){
		mPosition.x = posX - mWidth * 0.5f;
		mPosition.y = posY - mHeight * 0.5f;
	}
	
	public Position getPosition(){
		return mPosition;
	}
	
	public Position getCenterPosition(){
		return new Position(mPosition.x + mWidth * 0.5f, mPosition.y + mHeight * 0.5f);
	}
	
	public int getCost(){
		return mCost;
	}
	
	public void setName(String name){
		this.mName = name;
	}
	
	public String getName(){
		return mName;
	}
	
	public void addHealthPoint(int delta){
		if (mIsAlive){
			mHealthPoint += delta;
			if (mHealthPoint < 0){
				onDie();
			}
		}
	}
	
	public int getHealthPoint(){
		return mHealthPoint;
	}
	
	public int getWidth(){
		return mWidth;
	}
	
	public int getHeight(){
		return mHeight;
	}
	
	public int getStand(){
		return mStand;
	}
	/**
	 * Do something when die,like play sound ,set status,etc
	 */
	public void onDie(){
		mIsAlive = false;
	}
	
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		/*
		for (int i = 0; i < mParticles.length; ++i){
			mParticles[i].doDraw(canvas, this, scaleX, scaleY, paint);
		}*/
		if (mCurBitmap == null)
			return;
		canvas.drawBitmap(mCurBitmap, null, 
				new Rect((int)(mPosition.x * scaleX), (int)(mPosition.y * scaleY),
						(int)((mPosition.x + mCurBitmap.getWidth()) * scaleX),
						(int)((mPosition.y + mCurBitmap.getHeight()) * scaleY)), paint);
	
	}
	
	public void update(){
		++mElapsedFrame;
	}
	
	public boolean isAlive(){
		return mIsAlive;
	}

	public boolean isInScreen(int canvasWidth, int canvasHeight){
		if (mPosition.x + mWidth < 0 || mPosition.x > MAX_POSITION_X ||
			mPosition.y + mHeight < 0 || mPosition.y > MAX_POSITION_Y)
			return false;
		else
			return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		GameObject object = (GameObject)super.clone();
		/*for (int i = 0 ; i < mParticles.length; ++i){
			object.mParticles[i] = (Particle)this.mParticles[i].clone();
		}*/
		object.mPosition = (Position)this.mPosition.clone();
		return object;
	}

	public void setView(PlantVsZombieView view) {
		mView = view;
	}
	
	public Bitmap getBitmap(){
		return mCurBitmap;
	}
	
	 /**
     * Check collision(assume that both a and b are rectangular area)
     * @param GameObject a
     * @param GameObject b
     * @return
     */
	public static boolean isCollise(GameObject a, GameObject b){
      	if (a.getPosition().x >= b.getPosition().x + b.getWidth())
      		return false;
      	if (a.getPosition().x + a.getWidth() <= b.getPosition().x)
      		return false;
      	if (a.getPosition().y >= b.getPosition().y + b.getHeight())
      		return false;
      	if (a.getPosition().y + a.getHeight() <= b.getPosition().y)
      		return false;
      	return true;
      }

}
