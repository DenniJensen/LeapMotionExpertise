/**
 * Simple hot plate used in a cooker. Contains a power button and a 
 * head level. 
 * 
 * @author Dennis Haegler
 */
public class Hotplate {
	private boolean powerState;
	private int heatLevel;
	private int maxHeadLevel;

	/**
	 * Creates an new Hot plate by an given max heat level.
	 * 
	 * @param maxHeatLevel the maximum level for the hot plate.
	 */
	public Hotplate(int maxHeatLevel) {
		this.powerState = false;
		this.heatLevel = 0;
		this.maxHeadLevel = maxHeatLevel;
	}

	/**
	 *
	 * @return
	 */
	public boolean isOn() {
		return powerState;
	}

	/**
	 * Turns off the hot plate.
	 */
	public void turnOff() {
		this.powerState = false;
		this.heatLevel = 0;
	}

	/**
	 * Turns on the hot plate.
	 */
	public void turnOn() {
		this.powerState = true;
	}

	/**
	 *
	 * @return
	 */
	public int getHeatLevel() {
		return heatLevel;
	}
	
	/**
	 * Increases the heat level by one. You cannot be higher as the max heat level
	 * from the cooker. Changes will be ignored if the new heat level is higher as
	 * the max heat level.
	 */
	public void increaseHeatLevel() {
		int newLevel = heatLevel + 1;
		if (newLevel <= maxHeadLevel) {
			heatLevel = newLevel;
		}
	}
	
	/**
	 * Decreases the heat level by one. You cannot be lower as 0.
	 * Changes will be ignored if the new heat level is lower as
	 * the 0.
	 */
	public void decreaseHeatLevel() {
		int newLevel = heatLevel - 1;
		if (newLevel >= 0) {
			heatLevel = newLevel;
		}
	}

	/**
	 * Returns an information string from the hotplate.
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return "Heating: " + powerState + " --- Heat Level: " + heatLevel;
	}
}