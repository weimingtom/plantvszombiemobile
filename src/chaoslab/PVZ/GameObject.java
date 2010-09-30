package chaoslab.PVZ;
/**
 * Base class of Plants and Zombies
 */
import chaoslab.PVZ.Particle;
import android.graphics.Canvas;

abstract public class GameObject {
	protected String 	mName;
	protected int		mCost;
	protected int 		mHealthPoint;
	protected int		mElapsedFrame;
	protected boolean	mIsAlive;
	protected Position	mPosition;
	protected Particle  mParticles[];
	public static final int MAX_POSITION_X = 800;
	public static final int MAX_POSITION_Y = 600;
	protected int		mWidth 	= 0;
	protected int		mHeight = 0;
	
	public GameObject(String name, Particle particles[], int cost)
	{
		setName(name);
		mParticles = new Particle[particles.length];
		for (int i = 0; i < particles.length; ++i){
			mParticles[i]	= particles[i];
		}
		mCost			= cost;
		mElapsedFrame	= 0;
		mIsAlive		= true;
		mPosition		= new Position(0, 0);
	}
	public void SetPosition(int posX, int posY){
		mPosition.x = posX;
		mPosition.y = posY;
	}
	
	public Position getPosition(){
		return mPosition;
	}
	
	public int getCost(){
		return mCost;
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	
	public String getName() {
		return mName;
	}
	
	public int getHealthPoint()
	{
		return mHealthPoint;
	}
	
	/**
	 * Do something when die,like play sound ,set status,etc
	 */
	public void onDie(){
		mIsAlive = false;
	}
	
	public void doDraw(Canvas canvas, float scaleX, float scaleY){
		for (int i = 0; i < mParticles.length; ++i){
			mParticles[i].doDraw(canvas, this, scaleX, scaleY);
		}
	}
	
	public void update(){
		++mElapsedFrame;
	}
	
	public boolean isAlive(){
		return mIsAlive;
	}
	
	public int getWidth(){
		return mWidth;
	}
	
	public int getHeight(){
		return mHeight;
	}
	
	public boolean isInScreen(int canvasWidth, int canvasHeight){
		if (mPosition.x + mWidth < 0 || mPosition.x > MAX_POSITION_X ||
			mPosition.y + mHeight < 0 || mPosition.y > MAX_POSITION_Y)
			return false;
		else
			return true;
	}

}
