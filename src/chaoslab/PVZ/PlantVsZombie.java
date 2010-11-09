package chaoslab.PVZ;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class PlantVsZombie extends Activity {
    /** A handle to the View in which the game is running. */
    private PlantVsZombieView mView;
    private Intent mIntent = new Intent("chaoslab.PVZ.MUSIC");
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoundManager.getInstance().Initialize(getApplicationContext());
        SoundManager.getInstance().loadSound(R.raw.explosion);
        SoundManager.getInstance().loadSound(R.raw.chomp);
        setContentView(R.layout.main);
        mView = (PlantVsZombieView) findViewById(R.id.PVZView);
        startService(mIntent);
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
    	stopService(mIntent);
    	super.onDestroy();
    }
    
    
}