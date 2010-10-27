package chaoslab.PVZ.Zombies;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

public class ZombieFactory {
	public static Zombie createNormalZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_head);
		}
		return new Zombie("normal Zombie", null,bitmap, 100);
	}
	public static Zombie createSoccerZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_football_helmet2);
		}
		return new Zombie("soccer Zombie", null,bitmap, 100);
	}
	public static Zombie createBungeeZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_bungi_head);
		}
		return new Zombie("soccer Zombie", null,bitmap, 100);
	}
	public static Zombie createMiniZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombieimphead);
		}
		return new Zombie("soccer Zombie", null,bitmap, 100);
	}
	public static Zombie createPoleVaultZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombiepolevaulterhead);
		}
		return new Zombie("soccer Zombie", null,bitmap, 100);
	}
	public static Zombie createRoadBlockZombie(Resources res){
		Bitmap bitmap[] = new Bitmap[10];
		for(int i=0;i<10;++i){
			bitmap[i] = BitmapFactory.decodeResource(res, R.drawable.zombie_cone1);
		}
		return new Zombie("soccer Zombie", null,bitmap, 100);
	}
}
