package chaoslab.PVZ;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class PlantVsZombie extends Activity {
    /** A handle to the View in which the game is running. */
    private PlantVsZombieView mView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoundManager.getInstance().Initialize(getApplicationContext());
        SoundManager.getInstance().loadSound(R.raw.explosion);
        setContentView(R.layout.main);
        mView = (PlantVsZombieView) findViewById(R.id.PVZView);
       
    }
    @Override
    public void onPause(){
    	super.onPause();
    	mView.getThread().pause();
    }
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
       
    }
    @Override
    public void onDestroy(){
    	SoundManager.getInstance().Uninitialize();
    	mView.getThread().setRunning(false);
    	super.onDestroy();
    }
    
    
}