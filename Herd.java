/**
 *
 */
public class Herd {
	private Hotplate[] hotplates;
	private boolean powerButton;

	public Herd(int numberHotplates) {
		powerButton = false;
		hotplates = new Hotplate[numberHotplates];
		int hotPlatHeaterLevels = numberHotplates;
		for (int i = 0; i < numberHotplates; i++) {
			hotplates[i] = new Hotplate(hotPlatHeaterLevels);
		}
	}

	public Herd(int numberHotplates, int maxLevelsOfHotplates) {
		powerButton = false;
		hotplates = new Hotplate[numberHotplates];
		int hotPlatHeaterLevels = maxLevelsOfHotplates;
		for (int i = 0; i < numberHotplates; i++) {
			hotplates[i] = new Hotplate(hotPlatHeaterLevels);
		}
	}

	public boolean isOn() {
		return powerButton;
	}

	public void turnOn() {
		powerButton = true;
	}

	public void turnOff() {
		powerButton = false;
		//TODO turn all heate off
	}

	private void turnOffAllHotPlates() {
		int count = hotplates.length;
		for (int i = 0; i < count; i++) {
			hotplates[i].turnOff();
		}
	}

	public void increaseHotplate(int numberOfHotplate) {
		//TODO increase Plate
	}

	public void decreaseHotplate(int numberOfHotplate) {
		//TODO increase Plate
	}

	@Override
	public String toString() {
		int countOfHeader = hotplates.length;
		String outGoingString = "";
		if (countOfHeader == 0) {
			outGoingString = "No Header implemented";
		} else {
			for (int i = 0; i < countOfHeader; i++) {
				outGoingString += "Header-" + i + " " + hotplates[i].toString() + "\n";
			}
		}
		return outGoingString;
	}
}