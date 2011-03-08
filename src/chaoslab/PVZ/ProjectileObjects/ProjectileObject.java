package chaoslab.PVZ.ProjectileObjects;
/**
 * an abstract of all projectile objects like peanut projectile etc.
 */
import chaoslab.PVZ.GameObject;
import chaoslab.PVZ.Particle;

abstract public class ProjectileObject extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4613522014822427077L;
	protected int mPower = 0;
	public ProjectileObject(String name, Particle[] particles) {
		super(name, particles, 0);
		// TODO Auto-generated constructor stub
	}
	
	public void setPower(int power){
		mPower = power;
	}
	
	@Override 
	public void update(){
		super.update();
		move();
	}
	
	public abstract void onHit(GameObject target);

}
