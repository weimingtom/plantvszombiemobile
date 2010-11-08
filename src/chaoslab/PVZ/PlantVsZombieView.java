/*
 * PlantVsZombieView
 * Copyright (C) 2010 The Chaos Lab's First Project
 *
 */
package chaoslab.PVZ;

import java.util.ArrayList;
import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.Plants.PlantCells;
import chaoslab.PVZ.Plants.PlantFactory;
import chaoslab.PVZ.ProjectileObjects.ProjectileObject;
import chaoslab.PVZ.Zombies.Zombie;
import chaoslab.PVZ.Zombies.ZombieFactory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * View of Plant VS Zombie.Help zombies to EAT all plants!!
 */
public class PlantVsZombieView extends SurfaceView implements SurfaceHolder.Callback{
	
	class PlantVsZombieThread extends Thread{
		 /*
         * State-tracking constants
         */
		public static final int STATE_RUNNING = 1;
		public static final int STATE_PAUSED  = 2;
		public static final int STATE_WIN	  = 3;
		public static final int STATE_LOSE	  = 4;
		
    	/** equals to the last column of the plant cell*/
     	public static final int STRIP_COLUMN = 6;
		/*
         * Member (state) fields
         */
		private int mState;
		private Handler mHandler;
        /** The drawable to use as the background of the animation canvas */
        private Bitmap mBackgroundImage;
        private Bitmap mBowlingStripeImage;
        private Bitmap mSeedBarImage;
        /**
         * Current height of the surface/canvas.
         * 
         * @see #setSurfaceSize
         */
        private int mCanvasHeight = 1;
        /**
         * Current width of the surface/canvas.
         * 
         * @see #setSurfaceSize
         */
        private int mCanvasWidth = 1;
        /** Handle to the surface manager object we interact with */
        private SurfaceHolder mSurfaceHolder;
        /** Handle to the icon of the selected seed's icon in seed bank*/
    	private GameObject mSelectedSeedObject 	= null;
    	/**	Handle to the selected seed*/
    	private SeedCard mSelectedSeedCard	= null;
    	private ArrayList<SeedCard> mSeedCards;
		/** current stage */
        /*private int mStage = 1;*/
		//private Context mContext;
		/** Indicate whether the surface has been created & is ready to draw */
        private boolean mRun = false;
        private PlantCells mPlants;
		private ArrayList<Zombie> mZombies;
		private ArrayList<ProjectileObject> mProjectileObjects;
		private float mScaleX = 1.0f;
		private float mScaleY = 1.0f;
        private int	  mCurBrainNum = 0;
        public PlantVsZombieThread(SurfaceHolder surfaceHolder, Context context,
                Handler handler) {
        	 // get handles to some important objects
            mSurfaceHolder = surfaceHolder;
            mContext = context;
            mHandler = handler;
            mState	 = STATE_RUNNING;
            //Set Images
            Resources res	 	= context.getResources();
            mBackgroundImage 	= Bitmap.createBitmap(
            		BitmapFactory.decodeResource(res, R.drawable.background), 
            		200, 0, 800, 600);
            mBowlingStripeImage = BitmapFactory.decodeResource(res, R.drawable.bowlingstripe);
            mSeedBarImage		= BitmapFactory.decodeResource(res, R.drawable.seedbar);
            init();
          /*  Zombie zombie = ZombieFactory.createNormalZombie(res);
            zombie.setPosition(500, (int)(PlantCells.ORIGIN.y + 2 * PlantCells.CELL_HEIGHT - zombie.getHeight()));
            mZombies.add(zombie);
            */
           
        }
        /***
         * initilize game state, when needs to restart, call this to restore 
         * the initial state.
         */
        public void init(){
        	 Resources res		= mContext.getResources();
        	 mPlants 			= new PlantCells();
             mZombies 			= new ArrayList<Zombie>();
             mProjectileObjects = new ArrayList<ProjectileObject>();
             
             InitPlants(res);
             InitSeedCards(res);
             mState				= STATE_RUNNING;
             mSunshines			= 1500;
        }
        
        /**
         * Initialize plants according to current stage.
         */
        public void InitPlants(Resources res){
        	for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i)
        		for (int j = 0; j < PlantCells.MAX_COL_NUM; ++j){
        			Plant plant = null;
        			switch (j){
        			case 0:
        				plant = PlantFactory.createBrain(res);
        				break;
        			case 1:
        				plant = PlantFactory.createSnowPeaShooter(res);
        				break;
        			case 2:
        				if ( i== 2){
        					plant = PlantFactory.createLeafBunch(res);
        				}else{
        					plant = PlantFactory.createPotatoMine(res);
        				}
        				break;
        			case 4:
        				if (i == 3){
        					plant = PlantFactory.createTorchwood(res);
        				}else{
        					//plant = PlantFactory.createPotatoMine(res);
        					plant = PlantFactory.createChomper(res);
        				}
        				break;
        			default:
        				//plant = PlantFactory.createPotatoMine(res);
        				plant = PlantFactory.createSunFlower(res);
        			}
        			
        			if (plant != null){
	        			plant.setView(PlantVsZombieView.this);
	        			mPlants.setPlant(i, j, plant);
        			}
        			
        		}
        	mCurBrainNum = 5;
        }
        /**
         * InitSeedCards
         * 
         * @param res
         */
        public void InitSeedCards(Resources res){
        	mSeedCards 			= new ArrayList<SeedCard>();
        	Bitmap bkgBitmap	= BitmapFactory.decodeResource(res, R.drawable.seedpacket_larger);
        	Bitmap seedPackets[] = {
        			BitmapFactory.decodeResource(res, R.drawable.zombie_head),
        			BitmapFactory.decodeResource(res, R.drawable.zombie_bungi_head),
        			BitmapFactory.decodeResource(res, R.drawable.zombie_football_helmet2),
        			BitmapFactory.decodeResource(res, R.drawable.zombiepolevaulterhead),
        			BitmapFactory.decodeResource(res, R.drawable.zombieimphead),
        			BitmapFactory.decodeResource(res, R.drawable.zombie_cone1),
        	};
        	Zombie seedZombies[] = {
        			ZombieFactory.createNormalZombie(res),
        			ZombieFactory.createBungeeZombie(res),
        			ZombieFactory.createSoccerZombie(res),
        			ZombieFactory.createPoleVaultZombie(res),
        			ZombieFactory.createMiniZombie(res),
        			ZombieFactory.createRoadBlockZombie(res),
        	};
        	Position startPosition = new Position(70, 10);
        	for (int i = 0; i < seedPackets.length; ++i){
        		Bitmap seedPacket =  seedPackets[i];
        		mSeedCards.add(new SeedCard(startPosition, seedZombies[i], bkgBitmap, 
        				Bitmap.createScaledBitmap(seedPacket, (int)(seedPacket.getWidth() * 0.5),
            					(int)(seedPacket.getHeight() * 0.5), true)));
        		startPosition.x += SeedCard.SEED_CARD_WIDTH;
        	}
        }
        @Override
        public void run(){
        	while (mRun ){
        		Canvas c = null;
        		try {
                    c = mSurfaceHolder.lockCanvas(null);
                   
                    synchronized (mSurfaceHolder) {
                        if (mState == STATE_RUNNING)
                        	update();
                        if (c != null)
                        	doDraw(c);
                       
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
        	}
        }
        /**
         * Draws the plants, zombies, etc. to the provided
         * Canvas.
         */
        public void doDraw(Canvas canvas){
        	// Draw the background image. Operations on the Canvas accumulate
            // so this is like clearing the screen.
            canvas.drawBitmap(mBackgroundImage, null, new Rect(0, 0, mCanvasWidth, mCanvasHeight), null);
            canvas.drawBitmap(mBowlingStripeImage, PlantCells.CELL_WIDTH * STRIP_COLUMN * mScaleX, 32, null);
            //Log.d("time", "AFTER BACKGROUND");
            //Draw SeedBar
            Matrix matrix = new Matrix();
        	matrix.setScale(mScaleX, mScaleY);
            canvas.drawBitmap(mSeedBarImage, matrix, null);
            canvas.drawText(Integer.toString(mSunshines), 20 * mScaleX, 80 * mScaleY, new Paint());
            
            for (int i = 0; i < mSeedCards.size(); ++i){
            	mSeedCards.get(i).doDraw(canvas, mScaleX, mScaleY, null);
            }
            //Log.d("time", "AFTER draw Seed Card");
           
            //Draw Plants and Zombies
            for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i){
            	for (int j = 0; j < PlantCells.MAX_COL_NUM; ++j){
            		GameObject plant = mPlants.getPlant(i, j);
            		if (plant != null && plant.isAlive())
            			plant.doDraw(canvas, mScaleX, mScaleY, null);
            	}
            }
           // Log.d("time", "AFTER draw ALL PLANT");
            for (int i = 0; i < mZombies.size(); ++i){
            	mZombies.get(i).doDraw(canvas, mScaleX, mScaleY, null);
            }
            for (int i = 0; i < mProjectileObjects.size(); ++i){
            	mProjectileObjects.get(i).doDraw(canvas, mScaleX, mScaleY, null);
            }
            
            /* NOTE THAT HERE MAY OCCUR A SYNCHRONIZE ERROR: mSelectedObject may be set
             * to null in onTouchEvent method just before doDraw(). So here I temperately 
             * put new Paint outside of the if section to avoid this error.
             * Maybe here should add a synchronize*/
        	Paint paint = new Paint();
        	paint.setAlpha(127);
            if (mSelectedSeedObject != null){
            	mSelectedSeedObject.doDraw(canvas, mScaleX, mScaleY, paint);
            }
            
        }

       
        
        public void addProjectileObject(ProjectileObject object){
        	mProjectileObjects.add(object);
        }
        
        public void addZombie(Zombie zombie){
        	mZombies.add(zombie);
        }
        
        /**
         * Update all valid Objects,including zombies and plants
         */
        public void update(){
        	int ateBrainNum = 0;
        	//update plants
        	for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i){
        		for (int j = 0; j < PlantCells.MAX_COL_NUM; ++j){
        			Plant plant = mPlants.getPlant(i, j);
        			if (plant != null && plant.isAlive()){
        				plant.update();
        				plant.attack(mZombies);
        			}
        			else{
        				plant = null;
        				if (j == 0){
        					ateBrainNum ++;
        				}
        			}
        		}
        	}
        	//update zombies 
        	for (int i = 0; i < mZombies.size(); ++i){
        		Zombie zombie = mZombies.get(i);
        		//check zombie's position to select proper action.
	        	if (zombie != null && zombie.isAlive() && zombie.isInScreen(mCanvasWidth, mCanvasHeight)){
	        		Plant targetPlant = mPlants.getPlant(zombie.getPosition());
		        	if (targetPlant != null && targetPlant.isAlive() 
		        			&& GameObject.isCollise(targetPlant, zombie))
		        		zombie.eat(targetPlant);
		        	//else
		        		//zombie.move();
		        	zombie.update();
	        	}else{
	        		mZombies.remove(zombie);
	        		zombie = null;
	        	}	        	
        	}
        	/* update projectile objects
             * NOTE THAT:Here we should check each zombie and plant to make sure 
        	 * that each hit message will be sent to
        	 * the projectileObject,though single check can save time. 
        	 */
        	for (int i = 0; i < mProjectileObjects.size(); ++i){
        		ProjectileObject po = mProjectileObjects.get(i);
        		if (po.isAlive()){
	        		po.update();
	        		Plant plant = mPlants.getPlant(po.getPosition());
	        		if (plant != null && plant.isAlive() && GameObject.isCollise(plant, po)){
	        			po.onHit(plant);
	        		}
	        		
	        		for (int j = 0; j < mZombies.size(); ++j){
	        			Zombie zombie = mZombies.get(j);
	        			if (zombie.isAlive() && GameObject.isCollise(zombie, po)){
	        				po.onHit(zombie);
	        			}
	        		}
	        		
	        		
        		}else{
        			mProjectileObjects.remove(po);
        			po = null;
        		}
        	}
        	
        	 /*
             * check win or loose, and send message
             */
        	if (mCurBrainNum == ateBrainNum){
        		 mState = STATE_WIN;
        		 Message msg = mHandler.obtainMessage();
                 Bundle b = new Bundle();
                 b.putString("STATE_WIN", "" + STATE_WIN);
                 msg.setData(b);
                 mHandler.sendMessage(msg);

        	} else{
        		if (mSunshines < 50 && mZombies.size() == 0){
        			mState = STATE_LOSE;
        			Message msg = mHandler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putString("STATE_LOSE", "" + STATE_LOSE);
                    msg.setData(b);
                    mHandler.sendMessage(msg);
        		}
            }
        }
        
        /**
         * Deal with touch event.
         * DO REMEMBER THAT, the event's coordinate is not the real size
         * IT MUST BE DIVIDED BY the scale factor
         * @param event
         */
        public void onTouchEvent(MotionEvent event){
        	switch (event.getAction()){
    		case MotionEvent.ACTION_MOVE:
    			if (mSelectedSeedObject != null)
    				mSelectedSeedObject.setCenterPosition((int)(event.getX() / mScaleX), (int)(event.getY() / mScaleY));
    			break;
    		case MotionEvent.ACTION_DOWN:
    			if (mSelectedSeedCard != null || mSelectedSeedObject != null){
    				mSelectedSeedCard 	= null;
    				mSelectedSeedObject = null;
    			}else{
    				//check whether select a new seed card
    				boolean isSelected = false;
    				for (int i = 0; i < mSeedCards.size() && !isSelected; ++i){
    					if (mSeedCards.get(i).isSelected(event.getX() / mScaleX, event.getY() / mScaleY)
    							&& mSeedCards.get(i).getCost() <= mSunshines){
    						mSelectedSeedCard 	= mSeedCards.get(i);
    						mSelectedSeedObject = mSelectedSeedCard.getObject();
							isSelected 			= true;
    					}
    				}
    			}
    			break;
    		case MotionEvent.ACTION_UP:
    			if (mSelectedSeedObject != null){
    				try {
    					Position position = mSelectedSeedObject.getCenterPosition();
    					int col = (int)((position.x - PlantCells.ORIGIN.x) / PlantCells.CELL_WIDTH);
    					int row = (int)((position.y - PlantCells.ORIGIN.y) / PlantCells.CELL_HEIGHT);
    					if (row >= PlantCells.MAX_ROW_NUM)
    						row = PlantCells.MAX_ROW_NUM - 1;
    					else
    						if (row < 0)
    							row = 0;
    					Zombie newZombie = (Zombie)mSelectedSeedObject;
						if (col >= STRIP_COLUMN && !newZombie.directAttatck()){
    						if (mSunshines >= mSelectedSeedCard.getCost())
    						{
    							mSunshines -=  mSelectedSeedCard.getCost();
		    					Zombie zombie = (Zombie)mSelectedSeedObject.clone();
		    					zombie.setPosition(PlantCells.ORIGIN.x + col * PlantCells.CELL_WIDTH, 
		    							PlantCells.ORIGIN.y + row * PlantCells.CELL_HEIGHT);
		    					mZombies.add(zombie);
    						}
    					}else if( newZombie.directAttatck() && col < STRIP_COLUMN && col > 0) { //for bungee zombies and ones like this
    						if (mSunshines >= mSelectedSeedCard.getCost()){
    							mSunshines -=  mSelectedSeedCard.getCost();
    							Zombie zombie = (Zombie)mSelectedSeedObject.clone();
    							zombie.setPosition(PlantCells.ORIGIN.x + col * PlantCells.CELL_WIDTH,-30);
    							zombie.seek(mPlants.getPlant(row,col));
    							mZombies.add(zombie);
    						}
    						
    					}
						mSelectedSeedObject = null;
						mSelectedSeedCard 	= null;
    					
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
    			break;
    		}
        }
        
        /* Callback invoked when the surface dimensions change. */
        public void setSurfaceSize(int width, int height) {
            // synchronized to make sure these all change atomically
            synchronized (mSurfaceHolder) {
                mCanvasWidth	= width;
                mCanvasHeight	= height;
                
                mScaleX = width * 1.0f / mBackgroundImage.getWidth();
                mScaleY	= height * 1.0f / mBackgroundImage.getHeight();
           
            }
        }

		public void pause() {
			synchronized(mSurfaceHolder){
				mState = STATE_PAUSED;
			}
		}

		public void setRunning(boolean isRunning) {
			mRun = isRunning;
			
		}

		public void playAnimation(AnimationDrawable animation) {
			// TODO Auto-generated method stub
		//	animation.draw(mCanvas);
			animation.start();
		}
	}
	public static final int MAX_SUN_SHINE = 9999;
	public static final int MIN_SUN_SHINE = 0;
	/** Handle to the application context, used to e.g. fetch Drawables. */
	private Context mContext;
	private PlantVsZombieThread thread;
    /** Current sunshine number*/
	private int mSunshines = 1500;
	
	
	public PlantVsZombieView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new PlantVsZombieThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
            	  if (m.getData().getString("STATE_LOSE") != null
            			  || m.getData().getString("STATE_WIN") != null){
            		String message = null;
        			if (m.getData().getString("STATE_LOSE") != null){
        				message = "You Failed!";
        			}else{
        				message = "Congratulations!You have eaten all Brains!";
        			}
            	  
	            	  new AlertDialog.Builder(PlantVsZombieView.this.mContext)
	                  .setMessage(message)
	                  .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
	                      public void onClick(DialogInterface dialog, int whichButton) {
	                          /* User clicked Yes so do some stuff */
	                    	  thread.init();
	                      }
	                  })
	                  .setNegativeButton("Leave", new DialogInterface.OnClickListener() {
	                      public void onClick(DialogInterface dialog, int whichButton) {
	                          /* User clicked No so do some stuff */
	                    	  System.exit(0);
	                      }
	                  }).show();
            	  }
            
            }
        });
       
        setFocusable(true); // make sure we get key events
	}
	
	@Override
	/**
	 * Drag one seed from the seed bank to 
	 * the pointed place to generate a new plant/zombie 
	 */
	public boolean onTouchEvent(MotionEvent event){
		thread.onTouchEvent(event);
		return true;
	}
    /**
     * Standard window-focus override. Notice focus lost so we can pause on
     * focus lost. e.g. user switches to take a call.
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) thread.pause();
    }
	public void surfaceChanged(SurfaceHolder holder, int format, 
			int width, int height) {
		thread.setSurfaceSize(width, height);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
        thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
		
	}
	
	public PlantVsZombieThread getThread(){
		return thread;
	}

	public void addSunshines(int delta) {
		mSunshines += delta;
    	if (mSunshines > MAX_SUN_SHINE)
    		mSunshines = MAX_SUN_SHINE;
    	else 
    		if (mSunshines < MIN_SUN_SHINE)
    			mSunshines = MIN_SUN_SHINE;
	}
	
	public void addProjectileObject(ProjectileObject object){
		thread.addProjectileObject(object);
	}
	
	public void addAnimation(int animationResourceId){
		mContext.getResources().getAnimation(animationResourceId);
	}

}
