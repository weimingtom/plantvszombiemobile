package chaoslab.PVZ;

/**
 * SeedCard: Shows in SeedBar,description of its object.Can be dragged to 
 * generate new object at certain cost
 * @author Liu.zhenxing
 *
 */
public class SeedCard {
	private GameObject mObject;
	private int mImageId;
	public SeedCard(int imageId, GameObject gameObject){
		mImageId = imageId;
		mObject = gameObject; 
	}
	public int getCost(){
		return mObject.getCost();
	}
	public int getImageId(){
		return mImageId;
	}

}
