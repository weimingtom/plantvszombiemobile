package chaoslab.PVZ.Zombies;

import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import chaoslab.PVZ.ZombieItem.*;

public class ZombieFactory {
	public static Bitmap[] createCommonBitmap(Resources res){
		Bitmap bitmap[] = {
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_00),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_01),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_02),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_03),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_04),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_05),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_06),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_07),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_08),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_09),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_10),
				BitmapFactory.decodeResource(res, R.drawable.zb_normal_11),
		};
		return bitmap;
	}
	public static Bitmap[] createCharredBitmap(Resources res){
		Bitmap charredBitmap[] = {
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred1),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred2),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred3),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred4),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred5),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred6),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred7),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred8),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred9),
				BitmapFactory.decodeResource(res, R.drawable.zombie_charred10),
				
		};
		return charredBitmap;
	}
	public static Zombie createNormalZombie(Resources res){
		Bitmap bitmap[] = createCommonBitmap(res);
		Bitmap charredBitmap[] = createCharredBitmap(res);
		Zombie zombie =  new Zombie("normal Zombie", null,bitmap, 100);
		zombie.setCharredBitmap(charredBitmap);
		return zombie;
	}
	public static Zombie createSoccerZombie(Resources res){
		Bitmap bitmap[] = createCommonBitmap(res);
		Bitmap charredBitmap[] = createCharredBitmap(res);
		DeffensiveItem item = ItemFactory.createSoccerHelmetItem(res);
		SoccerZombie zombie = new SoccerZombie("soccer Zombie", null,bitmap, 150,item);
		zombie.setCharredBitmap(charredBitmap);
		return zombie;
	}
	public static Zombie createBungeeZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_bungi_head);
		}
		return new BungeeZombie("bungee Zombie", null,bitmap, 150);
	}
	public static Zombie createMiniZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombieimphead);
		}
		return new MiniZombie("mini Zombie", null,bitmap, 50);
	}
	public static Zombie createPoleVaultZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombiepolevaulterhead);
		}
		PoleItem item = ItemFactory.createPoleItem(res);
		return new PoleJumpZombie("pole Zombie", null,bitmap, 125,item);
	}
	public static Zombie createRoadBlockZombie(Resources res){
		Bitmap bitmap[] = createCommonBitmap(res);
		Bitmap charredBitmap[] = createCharredBitmap(res);
		DeffensiveItem item = ItemFactory.createConeHelmetItem(res);
		RoadBlockZombie zombie =  new RoadBlockZombie("cone Zombie", null,bitmap, 125,item);
		zombie.setCharredBitmap(charredBitmap);
		return zombie;
	}
}
