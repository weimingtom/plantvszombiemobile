/*
 * PlantVsZombieView
 * Copyright (C) 2010 The Chaos Lab's First Project
 *
 */
package chaoslab.PVZ;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import chaoslab.PVZ.Plants.Plant;
import chaoslab.PVZ.Plants.PlantCells;
import chaoslab.PVZ.Plants.PlantFactory;
import chaoslab.PVZ.Zombies.Zombie;
import chaoslab.PVZ.Zombies.ZombieFactory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
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
		/*
         * Member (state) fields
         */
		private int mState;
        /** The drawable to use as the background of the animation canvas */
        private Bitmap mBackgroundImage;
        private Bitmap mBowlingStripeImage;
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
        /** Message handler used by thread to interact with TextView */
        private Handler mHandler;
   
		/** current stage */
        /*private int mStage = 1;*/
		//private Context mContext;
		/** Indicate whether the surface has been created & is ready to draw */
        private boolean mRun = false;
        private PlantCells mPlants;
		private ArrayList<Zombie> mZombies;
		private float mScaleX = 1.0f;
		private float mScaleY = 1.0f;
        
        public PlantVsZombieThread(SurfaceHolder surfaceHolder, Context context,
                Handler handler) {
        	 // get handles to some important objects
            mSurfaceHolder = surfaceHolder;
            mHandler = handler;
            mContext = context;
            mState	 = STATE_RUNNING;
            //Set Images
            Resources res	 	= context.getResources();
            mBackgroundImage 	= Bitmap.createBitmap(
            		BitmapFactory.decodeResource(res, R.drawable.background), 
            		200, 0, 800, 600);
            mBowlingStripeImage = BitmapFactory.decodeResource(res, R.drawable.bowlingstripe);
            mPlants = new PlantCells();
            mZombies = new ArrayList<Zombie>();
          //  BitmapFactory.decodeResource(res, R.drawable.seedbar);
            Zombie zombie = ZombieFactory.createNormalZombie(res);
            zombie.SetPosition(500, (int)(PlantCells.ORIGIN.y + 2 * PlantCells.CELL_HEIGHT - zombie.getHeight()));
            mZombies.add(zombie);
            InitPlants(res);
        }
        
        /**
         * Initialize plants according to mStage
         */
        public void InitPlants(Resources res){
        	//Initialize brains
        	/*
        	try {
    			ObjectOutputStream out = new ObjectOutputStream (
    					new FileOutputStream("stg1.map"));
    			for (int i = 0; 
    				i < PlantCells.MAX_ROW_NUM * (PlantCells.MAX_COL_NUM - 1); ++i){
    				out.writeObject(PlantFactory.createBrain(res));
    			}
    		} catch (FileNotFoundException e) {
    			
    			Log.d("TEST", "FILENOTFOUND");
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
        	//read from file stg1.map
        	try {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(".\\stg1.map"));
				for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i)
				{
					mPlants.setPlant(i, 0, PlantFactory.createBrain(res));
	        		for (int j = 1; j < PlantCells.MAX_COL_NUM; ++j)
	        			mPlants.setPlant(i, j, (Plant)in.readObject());
				}
			} catch (StreamCorruptedException e) {
				
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			*/
			
        	for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i)
        		for (int j = 0; j < PlantCells.MAX_COL_NUM; ++j)
        			mPlants.setPlant(i, j, PlantFactory.createBrain(res));
        }
        
        @Override
        public void run(){
        	while (mRun){
        		Canvas c = null;
        		try {
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                        if (mState == STATE_RUNNING)
                        	update();
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
         * Draws the plants, zombies, and background to the provided
         * Canvas.
         */
        public void doDraw(Canvas canvas){
        	// Draw the background image. Operations on the Canvas accumulate
            // so this is like clearing the screen.
        	
            canvas.drawBitmap(mBackgroundImage, 0, 0, null);
            canvas.drawBitmap(mBowlingStripeImage, 250, 32, null);
            for (int i = 0; i < mZombies.size(); ++i){
            	mZombies.get(i).doDraw(canvas, mScaleX, mScaleY);
            }
           
            for (int i = 0; i < mPlants.MAX_ROW_NUM; ++i)
            {
            	for (int j = 0; j < mPlants.MAX_COL_NUM; ++j){
            		Plant plant = mPlants.getPlant(i, j);
            		if (plant != null && plant.isAlive())
            			plant.doDraw(canvas, mScaleX, mScaleY);
            	}
            }
           
           
        }
        /**
         * Draw status bar.Shows current sunshines,available zombie list.
         */
        public void drawStatusBar(){
        	//canvas.drawBitmap()
        }
        /**
         * Check collision(assume that both a and b are rectangular area)
         * @param GameObject a
         * @param GameObject b
         * @return
         */
        public boolean isCollise(GameObject a, GameObject b){
        	if (a.getPosition().x > b.getPosition().x + b.getWidth())
        		return false;
        	if (a.getPosition().x + a.getWidth() < b.getPosition().x)
        		return false;
        	if (a.getPosition().y > b.getPosition().y + b.getHeight())
        		return false;
        	if (a.getPosition().y + a.getHeight() < b.getPosition().y)
        		return false;
        	return true;
        }
        
        /**
         * Update all valid Objects,including zombies and plants
         */
        public void update(){
        	//update plants
        	for (int i = 0; i < PlantCells.MAX_ROW_NUM; ++i){
        		for (int j = 0; j < PlantCells.MAX_COL_NUM; ++j){
        			Plant plant = mPlants.getPlant(i, j);
        			if (plant != null && !plant.isAlive())
        				plant = null;
        			else
        				plant.update();
        		}
        	}
        	//update zombies 
        	for (int i = 0; i < mZombies.size(); ++i){
        		Zombie zombie = mZombies.get(i);
        		//check zombie's position to select proper action.
	        	if (zombie != null && zombie.isAlive() && zombie.isInScreen(mCanvasWidth, mCanvasHeight)){
		        	Plant targetPlant = mPlants.getPlant(zombie.getPosition());
		        	if (targetPlant != null && targetPlant.isAlive() 
		        			&& isCollise(targetPlant, zombie))
		        		zombie.eat(targetPlant);
		        	else
		        		zombie.move();
		        	zombie.update();
	        	}else{
	        		zombie = null;
	        	}
	        	
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
               
                // don't forget to resize the background image
                mBackgroundImage = Bitmap.createScaledBitmap(
                        mBackgroundImage, width, height, true);
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
	}
	
	/** Handle to the application context, used to e.g. fetch Drawables. */
	private Context mContext;
	private PlantVsZombieThread thread;
    /** Current sunshine number*/
	private int mSunshines = 150;
	public PlantVsZombieView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new PlantVsZombieThread(holder, context, new Handler() {
            @Override
            public void handleMessage(Message m) {
                
            }
        });

        setFocusable(true); // make sure we get key events
	}

    /**
     * Standard window-focus override. Notice focus lost so we can pause on
     * focus lost. e.g. user switches to take a call.
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!hasWindowFocus) thread.pause();
    }
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, 
			int width, int height) {
		// TODO Auto-generated method stub
		thread.setSurfaceSize(width, height);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread.setRunning(true);
        thread.start();
	}

	@Override
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
    	if (mSunshines > 9999)
    		mSunshines = 9999;
    	else 
    		if (mSunshines < 0)
    			mSunshines = 0;
	}

}
