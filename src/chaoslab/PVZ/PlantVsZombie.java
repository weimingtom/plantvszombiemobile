package chaoslab.PVZ;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.AdapterView.OnItemClickListener;

public class PlantVsZombie extends Activity {
    /** A handle to the View in which the game is running. */
    private PlantVsZombieView mView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("PlantVsZombie", "created");
        setContentView(R.layout.main);
        mView = (PlantVsZombieView) findViewById(R.id.PVZView);
      /*  //Set SeedBar Gallery
        Gallery mSeedBarGallery = (Gallery)findViewById(R.id.SeedCardGallery);
        mSeedBarGallery.setAdapter(new SeedBarGalleryAdapter(this));
        //Set ClickListener
        mSeedBarGallery.setOnItemClickListener(new OnItemClickListener(){
			@Override
			 public void onItemClick(AdapterView<?> parent, View v, int position, long id) {  
				//SeedBarGalleryAdapter adapter = (SeedBarGalleryAdapter)parent.getAdapter();
				//adapter.getItem(position);
            }
        });*/
    }
    @Override
    public void onPause(){
    	super.onPause();
    	mView.getThread().pause();
    }
}