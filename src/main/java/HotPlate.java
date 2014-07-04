/**
 *
 * @author Dennis Haegler - dennis.haegler@gmail.com
 */
public class HotPlate {
	private boolean powerState;
	private int heatLevel;
	private int maxHeadLevel;

	public HotPlate(int maxHeatLevel) {
		this.powerState = false;
		this.heatLevel = 0;
		this.maxHeadLevel = maxHeatLevel;
	}

	public boolean isOn() {
		return powerState;
	}

	public void turnOff() {
		this.powerState = false;
		this.heatLevel = 0;
	}

	public void turnOn() {
		this.powerState = true;
	}

	public void setHeatLevel(int heatLevel) {
		if(heatLevel > maxHeadLevel) {
			this.heatLevel = maxHeadLevel;
		} else if (heatLevel < 0) {
			this.heatLevel = 0;
		} else {
			this.heatLevel = heatLevel;
		}
	}

	public int getHeatLevel() {
		return heatLevel;
	}

	public String toString() {
		return "Heating: " + powerState + " --- Heat Level: " + heatLevel;
	}
}
