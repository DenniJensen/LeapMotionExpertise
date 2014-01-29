/**
 * Simple hot plate used in a cooker. Contains a power button and a 
 * head level. 
 * 
 * @author Dennis Haegler
 */
public class HotPlate {
	private boolean powerState;
	private int heatLevel;
	private int maxHeadLevel;

	/**
	 * Creates an new Hot plate by an given max heat level.
	 * 
	 * @param maxHeatLevel the maximum level for the hot plate.
	 */
	public HotPlate(int maxHeatLevel) {
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
	 * @param heatLevel
	 */
	public void setHeatLevel(int heatLevel) {
		if(heatLevel > maxHeadLevel) {
			this.heatLevel = maxHeadLevel;
		} else if (heatLevel < 0) {
			this.heatLevel = 0;
		} else {
			this.heatLevel = heatLevel;
		}
	}

	/**
	 *
	 * @return
	 */
	public int getHeatLevel() {
		return heatLevel;
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