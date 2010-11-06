package chaoslab.PVZ;

import java.util.*;
import android.media.*;
import android.content.Context;

/*
 * This class provide sound utility to other modules.
 * 
 * */
public class SoundManager {
	
	private static SoundManager instance = new SoundManager();
	private SoundPool soundPool = null;
	private Hashtable<Integer,Integer> soundIDResourceIDMap = new Hashtable<Integer,Integer>(20);
	private Context context = null; 
	
	private SoundManager()
	{
		
	}
	
	/**
	 * Get instance of SoundManager. There is only one SoundManager instance in the game.
	 */
	static public SoundManager getInstance()
	{
		return instance;
	}
	
	/**
	 * Initialize the SoundManager.This method must be called before any sound is played.
	 * 
	 * @param c: the context, which is needed in loading embedded sound resource.
	 * 
	 */
	public void Initialize(Context c)
	{
		context = c;
		soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
	}
	
	/**
	 * Release all resources associated with SoundManager.
	 * 
	 */
	public void Uninitialize()
	{
		Enumeration<Integer> keys = soundIDResourceIDMap.keys();
		while(keys.hasMoreElements())
		{
			soundPool.unload(soundIDResourceIDMap.get(keys.nextElement()));
		}
		soundIDResourceIDMap.clear();
		soundPool.release();
	}
	
	
	/**
	 * Play the indicated sound.
	 * 
	 * @param resid:sound resource id
	 * @param loop:whether loop play. 
     *            -1 means loop forever, 
	 * 			   0 means don't loop, 
	 *             other values indicate the number of repeats, 
	 *             e.g. a value of 1 plays the audio twice.
	 * 
	 * @return a key is returned. The key can be used to stop play the corresponding stream.
	 */
	public int play(int resID, int loop)
	{
		int soundID = 0;
		if(soundIDResourceIDMap.containsKey(resID))
		{
			soundID = soundIDResourceIDMap.get(resID); 
		}else{
			//if load causes performance problem here, 
			//this code can be moved to Initialize() section, 
			//that's to pre-load all needed resources.
			soundID = soundPool.load(context, resID, 1);
			soundIDResourceIDMap.put(resID, soundID);
		}
		
		return soundPool.play(soundID, 1.0f, 1.0f, 5, loop,1.0f);
	}
	
	/**
	 * Stop play a specific stream.
	 * 
	 * @param key: indicates the stream that should be stopped. 
	 *             Key is returned by play(int resid, boolean loop)
	 * 
	 */
	public void stop(int streamID)
	{
		soundPool.stop(streamID);
	}
	
	
	/**
	 * Stop play all streams.
	 */
	public void stopAll()
	{
		//It's a pity that SoundPool doesn't have a stopAll method.
		//I'll find a better way to deal with this problem.
		
		//One method is to keep a stream id list, then stop them one by one.
		//But this method has the disadvantage that the list will keep
		//growing, and you have no way to know which stream has been stopped
		//playing(stopped by user, or just completed playing).
		
		//The best solution is that SoundPool provides an interface to 
		//query all active streams. But no such method is found.
	}
	
	/**
	 * Pause all currently active streams.
	 */
	public void pauseAll()
	{
		 soundPool.autoPause();
	}
	
	/**
	 * Resume all streams paused by last pauseAll().
	 */
	public void resumeAll()
	{
		soundPool.autoResume();
	}
	
}
