package chaoslab.PVZ;

/*
 * this class is the substantial element of some 
 * special effect
 */

import android.graphics.Bitmap;

public class Parti_obj {
	//position
	public double x = 0.0f;
	public double y = 0.0f;
	//speed
	public double speedX = 0.0f;
	public double speedY = 0.0f;
	//acceleration
	public double accX = 0.0f;
	public double accY = 0.0f;
	//display
	public boolean color_bitmap = true; //true:color false:bitmap
	public int color = 0;
	public Bitmap bitmap = null;
	public int alpha = 0;
	//life period
	public int life = 0;
	
	public Parti_obj(){
		
	}
	
}
