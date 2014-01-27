import processing.core.PApplet;

public class Herd extends PApplet {
	private Hotplate[] hotplates;
	private boolean isOn;

	public Herd(int numberHotplates, int maxLevelsOfHotplates) {
		isOn = false;
		hotplates = new Hotplate[numberHotplates];
		for (int i = 0; i < numberHotplates; i++) {
			hotplates[i] = new Hotplate(maxLevelsOfHotplates);
		}
		 //TODO turn all heate off
	}

	private void turnOffAllHotPlates() {
		int count = hotplates.length;
		for (int i = 0; i < count; i++) {
			hotplates[i].turnOff();
		}
	}

	public void setHeatLevel(int hotPlate, int heatLevel) {
		hotplates[hotPlate].setHeatLevel(heatLevel);
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