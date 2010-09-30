package chaoslab.PVZ.Zombies;

import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;
import android.content.res.Resources;
import android.graphics.BitmapFactory;

public class ZombieFactory {
	public static Zombie createNormalZombie(Resources res){
		
		Particle particles[] = {
			new Particle(new Position(0, 0), 
					BitmapFactory.decodeResource(res, R.drawable.zombie_head)),
			new Particle(new Position(32, 32), 
					BitmapFactory.decodeResource(res, R.drawable.zombie_body)),	
		};
		return new Zombie("normal Zombie", particles, 100);
	}
}
