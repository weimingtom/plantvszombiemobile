package chaoslab.PVZ;

import android.app.Activity;
import android.os.Bundle;

public class PlantVsZombie extends Activity {
    /** A handle to the View in which the game is running. */
    private PlantVsZombieView mView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mView = (PlantVsZombieView) findViewById(R.id.PVZView);
    }
    @Override
    public void onPause(){
    	super.onPause();
    	mView.getThread().pause();
    }
}