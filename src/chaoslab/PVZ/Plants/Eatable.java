package chaoslab.PVZ.Plants;

public interface Eatable {
	public void ate(int harmPoint);
	/*
	 * is this object a defensive creature or not
	 * eg: pumpkin,potato
	 * pole vault jumping zombies will jump over these creatures
	 */
	public boolean isDefCreature();
	public boolean isProtected(); //protected by vegetable umbrella
}
