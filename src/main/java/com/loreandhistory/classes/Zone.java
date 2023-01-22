package com.loreandhistory.classes;

final public class Zone {
	final private int startX;
	final private int startY;
	final private int endX;
	final private int endY;

	public Zone(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	public boolean isInZone(int x, int y) {
		return (x >= this.startX && y >= this.startY && x <= this.endX && y <= this.endY);
	}
}
