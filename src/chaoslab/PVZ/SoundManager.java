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
	 * @param c : the context, which is needed in loading embedded sound resource.
	 * 
	 */
	public void Initialize(Context c)
	{
		context = c;
		soundPool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
	}
	
	/**
	 * Preload sound resource. Sound resource must be loaded before being played.
	 * 
	 * @param resid : resource id
	 * 
	 */
	public void loadSound(int resid)
	{
		if(!soundIDResourceIDMap.containsKey(resid))
		{
			int soundID = soundPool.load(context, resid, 1);
			soundIDResourceIDMap.put(resid, soundID);
		}
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
	 * @param resid :sound resource id
	 * @param loop :whether loop play. 
     *				-1 means loop forever, 
	 *				0 means don't loop, 
	 *				other values indicate the number of repeats, 
	 *				e.g. a value of 1 plays the audio twice.
	 * @param volume : 0.0 ~ 1.0
	 * 
	 * @return a key is returned. The key can be used to stop play the corresponding stream.
	 */
	public int play(int resid, int loop, float volume)
	{
		//if the resource is not preloaded, exception will thrown here.
		int soundID = soundIDResourceIDMap.get(resid); 
		return soundPool.play(soundID, volume, volume, 5, loop,1.0f);
	}
	
	/**
	 * Stop play a specific stream.
	 * 
	 * @param key : indicates the stream that should be stopped. 
	 *             Key is returned by play(int resid, boolean loop)
	 * 
	 */
	public void stop(int streamID)
	{
		soundPool.stop(streamID);
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
