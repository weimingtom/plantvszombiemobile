package chaoslab.PVZ;

import chaoslab.PVZ.PlantVsZombieView.PlantVsZombieThread;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class PlantVsZombie extends Activity {
    /** A handle to the View in which the game is running. */
    private PlantVsZombieView mView;
    private PlantVsZombieThread mThread;
    private Intent mIntent = new Intent("chaoslab.PVZ.MUSIC");
    private ServiceConnection mConnc;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.main);
        mView = (PlantVsZombieView) findViewById(R.id.PVZView);
        mThread = mView.getThread();
        
        if (savedInstanceState == null){
	        SoundManager.getInstance().Initialize(getApplicationContext());
	        SoundManager.getInstance().loadSound(R.raw.explosion);
	        SoundManager.getInstance().loadSound(R.raw.chomp);  
	        startService(mIntent);
	       // if (!bindService(mIntent, mConnc, Service.BIND_AUTO_CREATE)){
	        	//Log.d("FAILED!", "FAILED!");
	       // }
        }else{
        	mThread.restoreState(savedInstanceState);
        }
    }
    @Override
    public void onPause(){
    	super.onPause();
    	mView.getThread().pause();
    }
    /**
     * Notification that something is about to happen, to give the Activity a
     * chance to save state.
     * 
     * @param outState a Bundle into which this Activity should save its state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // just have the View's thread save its state into our Bundle
    	Log.d("WARNING:", "on SAVE");
        super.onSaveInstanceState(outState);
        mView.getThread().saveState(outState);
        
    }
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
       
    }
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	SoundManager.getInstance().Uninitialize();
    	mView.getThread().setRunning(false);
    	stopService(mIntent);
    	
    }
    
    
}