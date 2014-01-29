public class Herd {
	private HotPlate[] hotPlates;
	private boolean isOn;

	public Herd(int numberHotplates, int maxLevelsOfHotplates) {
		this.isOn = false;
		this.hotPlates = new HotPlate[numberHotplates];
		for (int i = 0; i < numberHotplates; i++) {
			hotPlates[i] = new HotPlate(maxLevelsOfHotplates);
		}
		 //TODO turn all heat off
	}

	private void turnOffAllHotPlates() {
		int count = hotPlates.length;
		for (int i = 0; i < count; i++) {
			hotPlates[i].turnOff();
		}
	}

	public void setHeatLevel(int hotPlate, int heatLevel) {
		hotPlates[hotPlate].setHeatLevel(heatLevel);
	}

	public int getHeatLevel(int hotPlate) {
		return hotPlates[hotPlate].getHeatLevel();
	}

	public int getHotPlates() {
		return this.hotPlates.length;
	}

	@Override
	public String toString() {
		int countOfHeader = hotPlates.length;
		String outGoingString = "";
		if (countOfHeader == 0) {
			outGoingString = "No Header implemented";
		} else {
			for (int i = 0; i < countOfHeader; i++) {
				outGoingString += "Header-" + i + " " + hotPlates[i].toString() + "\n";
			}
		}
		return outGoingString;
	}
}