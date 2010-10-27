package chaoslab.PVZ.Zombies;

import android.graphics.Bitmap;
import chaoslab.PVZ.Plants.Plant;
import android.graphics.BitmapFactory;
import chaoslab.PVZ.GameConstants;
import chaoslab.PVZ.Particle;
import chaoslab.PVZ.Position;
import chaoslab.PVZ.R;

public class PoleJumpZombie extends Zombie{
	protected boolean mHasPole = true; //has it gotten a jumping pole or not
	public PoleJumpZombie(String name, Particle particles[],Bitmap bitmap[],int cost){
		super(name,particles,bitmap,cost);
	}
	@Override
	public void eat(Plant target){
		if(target.isDefCreature() && mHasPole){
			doJumping();
		}else{
			super.eat(target);
		}
	}
	public void doJumping(){
		mPosition.x += 30;
		mHasPole = false;
		mStatus = GameConstants.ZOMBIE_JUMP;
	}
	@Override
	public void update(){
		super.update();
		if(GameConstants.ZOMBIE_JUMP == mStatus){
			
		}
	}
}
