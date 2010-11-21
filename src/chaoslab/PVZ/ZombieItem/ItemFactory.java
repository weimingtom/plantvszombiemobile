package chaoslab.PVZ.ZombieItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import chaoslab.PVZ.PlantVsZombieView;
import chaoslab.PVZ.R;
import android.content.res.Resources;

public class ItemFactory {
	protected static PlantVsZombieView mView;
	
	public static BombItem createBombItem(Resources res){
		Bitmap bitmap[] = new Bitmap[1];
		for(int i=0;i<bitmap.length;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_jackbox_box);
		}
		return new BombItem(bitmap);
	}
	public static LadderItem createLadderItem(Resources res){
		Bitmap bitmap[] = new Bitmap[2];
		bitmap[0] = BitmapFactory.decodeResource(res, R.drawable.zombie_ladder_1);
		bitmap[1] = BitmapFactory.decodeResource(res, R.drawable.zombie_ladder_5);
		return new LadderItem(bitmap);
	}
	public static DeffensiveItem createIronHelmetItem(Resources res){
		Bitmap bitmap[] = new Bitmap[3];
		bitmap[0] = BitmapFactory.decodeResource(res, R.drawable.zombie_bucket1);
		bitmap[1] = BitmapFactory.decodeResource(res, R.drawable.zombie_bucket2);
		bitmap[2] = BitmapFactory.decodeResource(res, R.drawable.zombie_bucket3);
		return new DeffensiveItem(bitmap,100,true);
	}
	public static DeffensiveItem createConeHelmetItem(Resources res){
		Bitmap bitmap[] = new Bitmap[1];
		bitmap[0] = BitmapFactory.decodeResource(res, R.drawable.zombie_cone1);
		return new DeffensiveItem(bitmap,50,false);
	}
	public static DeffensiveItem createSoccerHelmetItem(Resources res){
		Bitmap bitmap[] = new Bitmap[1];
		for(int i=0;i<1;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_football_helmet2);
		}
		return new DeffensiveItem(bitmap,100,true);
	}
	public static PoleItem createPoleItem(Resources res){
		Bitmap bitmap[] = new Bitmap[1];
		for(int i=0;i<1;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_polevaulter_pole);
		}
		return new PoleItem(bitmap);
	}
	public static void setView(PlantVsZombieView view){
		mView = view;
	}
	public static void addItem(AbstractItem item){
		mView.addItem(item);
	}
}
