package chaoslab.PVZ.Zombies;

import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

public class ZombieFactory {
	public static Zombie createNormalZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[12];
		bitmap[0] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_00);
		bitmap[1] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_01);
		bitmap[2] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_02);
		bitmap[3] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_03);
		bitmap[4] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_04);
		bitmap[5] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_05);
		bitmap[6] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_06);
		bitmap[7] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_07);
		bitmap[8] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_08);
		bitmap[9] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_09);
		bitmap[10] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_10);
		bitmap[11] = BitmapFactory.decodeResource(res, R.drawable.zb_normal_11);
		return new Zombie("normal Zombie", null,bitmap, 100);
	}
	public static Zombie createSoccerZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_football_helmet2);
		}
		return new SoccerZombie("soccer Zombie", null,bitmap, 100);
	}
	public static Zombie createBungeeZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_bungi_head);
		}
		return new BungeeZombie("bungee Zombie", null,bitmap, 100);
	}
	public static Zombie createMiniZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombieimphead);
		}
		return new MiniZombie("mini Zombie", null,bitmap, 100);
	}
	public static Zombie createPoleVaultZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombiepolevaulterhead);
		}
		return new PoleJumpZombie("pole Zombie", null,bitmap, 100);
	}
	public static Zombie createRoadBlockZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_cone1);
		}
		return new RoadBlockZombie("cone Zombie", null,bitmap, 100);
	}
}
