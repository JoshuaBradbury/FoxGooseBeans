package preliminaries;

import java.util.Observable;

public class Model extends Observable {

	private int xCoord, yCoord, speed, elevation;
	public static final int RUNWAY_LENGTH = 100;
	public static final int RUNWAY_WIDTH = 10;
	private int leftLength = RUNWAY_LENGTH;
	private boolean takeOff = false;
	private int seconds;
	
	public Model(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public int getElevation() {
		return elevation;
	}

	private int elevationCounter = 0;

	public void update() {
		yCoord += speed;
		leftLength -= speed;

		if (speed >= 10) {
			elevationCounter++;
			if (elevationCounter >= 5)
				elevation++;
		}
		takeOff();
		setChanged();
		notifyObservers();
	}
	
	public void takeOff() {
		if (getLeftLength() >= 0) {
			if (getxCoord() == 5 && getElevation() >= 5)
				takeOff = true;
			else if (getElevation() >= 5 && getxCoord() != 5) {
				if (getxCoord() == 5)
					takeOff = true;
			}
		} else if (getLeftLength() < 0 && !takeOff) {
			takeOff = true;
		}
	}

	public boolean getTakeOff() {
		return takeOff;
	}
	
	public int getLeftLength() {
		return leftLength;
	}

	public void run() {
		while (true) {
			try {
				if (!takeOff) {
					seconds++;
					update();
				}

				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void reset() {
		takeOff = false;
		speed = 0;
		elevation = 0;
		xCoord = 5;
		yCoord = 0;
		seconds = 0;
	}
	
	public int getSeconds() {
		return seconds;
	}
	
	public String toString() {
		return "X:" + xCoord + "  Y:" + yCoord + "  Speed:" + speed + "  Elevation:" + elevation;
	}
}
