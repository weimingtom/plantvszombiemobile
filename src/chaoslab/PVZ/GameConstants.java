package chaoslab.PVZ;

public class GameConstants {
	public static final int STAND_NONE 		= 0;
	public static final int STAND_PLANT 	= 1;
	public static final int STAND_ZOMBIE	= 2;
	/*
	 * something need to declare!
	 * after carefully consideration, I suggest putting some constants
	 * here as status enumerations
	 */
	public static final int ZOMBIE_MOVE = 3;  		//for any zombie
	public static final int ZOMBIE_ATTACK = 4; 		//for any zombie
	public static final int ZOMBIE_ATTACKED = 5; 	//for
	public static final int ZOMBIE_JUMP = 6;		//for pole vault jumping zombie	
	public static final int ZOMBIE_UNDERGROUND = 7; //for undermine zombie
	public static final int ZOMBIE_FROZON = 8;		//for zombies frozen by corn or bungee zombies
	public static final int ZOMBIE_SEEK = 9;		//for bungee zombie	
	public static final int ZOMBIE_TRANSIT = 10;	//fun bungee zombie
	public static final int ZOMBIE_BACKWARD = 11;
	public static final int ZOMBIE_CHARRED	= 12;
	public static final int ZOMBIE_PRE_SEEK = 13;		//fprepare for seek
	/*
	 * there are some other statuses so that I need another 10 number 
	 * to keep as reserved field.
	 * please define yourself constants using the value equal/over the @NEXT 
	 */
	  //public static final int NEXT = 21;
}

