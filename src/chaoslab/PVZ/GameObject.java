package chaoslab.PVZ;
/**
 * Base class of Plants and Zombies
 */
import chaoslab.PVZ.Particle;
import android.graphics.Canvas;
import android.graphics.Paint;

abstract public class GameObject implements Cloneable{

	public static final int MAX_POSITION_X = 800;
	public static final int MAX_POSITION_Y = 600;
	
	protected String 	mName;
	protected int		mCost;
	protected int 		mHealthPoint;
	protected int		mElapsedFrame;
	protected boolean	mIsAlive;
	protected Position	mPosition;
	/** TODO: change this to ArrayList to support animation*/
	protected Particle  mParticles[];		
	/** Actual size of this object*/
	protected int		mWidth 	= 0;
	protected int		mHeight = 0;
	
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
	}
	
	public void setPosition(float posX, float posY){
		mPosition.x = posX;
		mPosition.y = posY;
	}
	
	public Position getPosition(){
		return mPosition;
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
	
	public int getHealthPoint(){
		return mHealthPoint;
	}
	
	public int getWidth(){
		return mWidth;
	}
	
	public int getHeight(){
		return mHeight;
	}
	
	/**
	 * Do something when die,like play sound ,set status,etc
	 */
	public void onDie(){
		mIsAlive = false;
	}
	
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		for (int i = 0; i < mParticles.length; ++i){
			mParticles[i].doDraw(canvas, this, scaleX, scaleY, paint);
		}
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
		for (int i = 0 ; i < mParticles.length; ++i){
			object.mParticles[i] = (Particle)this.mParticles[i].clone();
		}
		object.mPosition = (Position)this.mPosition.clone();
		return object;
	}

}
