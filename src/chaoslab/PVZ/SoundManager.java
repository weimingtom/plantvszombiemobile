package chaoslab.PVZ;

import java.util.*;
import android.media.*;
import android.content.Context;

/*
 * This class provide sound utility to other modules.
 * 
 * Problem : Embedded sound can only be played through  create(Context  context, int resid)  api.
 * It implies that you have to create a new media player each time you play sound. MediaPlayer
 * can't be reused to play embedded resources. 
 * 
 * */
public class SoundManager {
	
	
	private ArrayList<MediaPlayer> mediaPlayerList = new ArrayList<MediaPlayer>();
	private static SoundManager instance = new SoundManager();
	
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
	 * Set the context, which is needed in creating MediaPlayer to play embedded sound resource.
	 * 
	 * @param context : the context
	 */
	public void setContext(Context context)
	{
		
	}
	
	/**
	 * Play the indicated sound.
	 * 
	 * @param resid:sound resource id
	 * @param loop:whether loop play
	 * 
	 * @return a key is returned. The key can be used to stop play the corresponding sound.
	 */
	public String play(int resid, boolean loop)
	{
		return null;
	}
	
	/**
	 * Stop play a specific sound.
	 * 
	 * @param key: indicates the sound that should be stopped. Key is returned by play(int resid, boolean loop)
	 * 
	 */
	public void stopPlay(String key)
	{
		
	}
	
	/**
	 * Query whether the indicated sound is playing.
	 * 
	 * @param key : the sound to query. Key is returned by play(int resid, boolean loop)
	 */
	public boolean isPlaying(String key)
	{
		return false;
	}	
	
	/**
	 * Stop play all sounds.
	 */
	public void stopAll()
	{
		
	}
	
	/**
	 * Pause all currently playing sounds.
	 */
	public void pauseAll()
	{
		
	}
	
	/**
	 * Resume all paused sounds.
	 */
	public void resumeAll()
	{
		
	}
	
}
