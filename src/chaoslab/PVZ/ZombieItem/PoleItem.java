package chaoslab.PVZ.ZombieItem;

import android.graphics.Bitmap;

public class PoleItem extends AbstractItem{
	public PoleItem(Bitmap[] bitmap){
		super(bitmap);
		canDef = false;
		bmagnetized = false;
	}
}
