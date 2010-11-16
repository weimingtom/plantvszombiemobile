package chaoslab.PVZ;
/**
 * GameEventListener
 * 
 * @author liu.zhenxing
 *
 */
public interface GameEventListener {
	public void onSunshineAdded(int delta);
	public void onProjectileObjectCreated(GameObject object);
}
