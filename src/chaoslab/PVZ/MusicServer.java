package chaoslab.PVZ;
/**
 * MusicServer
 * @author Liu.zhenxing
 * play background music as a service
 */
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicServer extends Service {
	public static final String PAUSE_MUSIC = "chaoslab.PVZ.intent.action.pausemusic";
	public static final String PLAY_MUSIC = "chaoslab.PVZ.intent.action.playmusic";
	private MediaPlayer mediaPlayer; 
	class MyReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(PAUSE_MUSIC)){
				pause();
			}else
			{
				if (intent.getAction().equals(PLAY_MUSIC)){
					play();
				}
			}
			
		}
		
	}
	@Override 
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public void onStart(Intent intent,int startId){
		super.onStart(intent, startId);
		if (mediaPlayer == null){ 
			mediaPlayer = MediaPlayer.create(this, R.raw.mainmusic);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}
		
	}
	
	public void pause(){
		if (mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
		}
	}
	
	public void play(){
		if (mediaPlayer != null && !mediaPlayer.isPlaying()){
			mediaPlayer.start();
		}
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		if (mediaPlayer != null){
			mediaPlayer.release();
		}
		
	}
	
	
}
