package chaoslab.PVZ.ProjectileObjects;
/**
 * an abstract of all projectile objects like peanut projectile etc.
 */
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;

abstract public class ProjectileObject extends GameObject {

	protected int mPower = 0;
	/** direction angel*/
	protected double mMoveAngle 	= 0;
	protected double mMoveSpeed 	= 0;
	protected double mMoveSpeedX	= 0;
	protected double mMoveSpeedY	= 0;
	public ProjectileObject(String name, Particle[] particles) {
		super(name, particles, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void setPower(int power){
		mPower = power;
	}
	
	public void setMoveAngle(double angle){
		mMoveAngle 	= angle;
		mMoveSpeedX = mMoveSpeed * Math.cos(angle);
		mMoveSpeedY = mMoveSpeed * Math.sin(angle);
	}
	
	public void setMoveSpeed(double speed){
		mMoveSpeed = speed;
		setMoveAngle(mMoveAngle);
	}
	
	public void move(){
		mPosition.x += mMoveSpeedX;
		mPosition.y += mMoveSpeedY;
	}
	
	@Override 
	public void update(){
		super.update();
		move();
	}
	
	public abstract void onHit(GameObject target);

}
