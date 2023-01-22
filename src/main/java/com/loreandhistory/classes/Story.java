package com.loreandhistory.classes;

import com.loreandhistory.enums.DialogueStatus;

final public class Story {
	final private String name;
	final private DialogueLine[] dialogueLines;
	final private Zone[] zones;
	private int index = 0;
	private DialogueStatus dialogueStatus = DialogueStatus.STOPPED;

	public Story(String name, Zone[] zones, DialogueLine[] dialogueLines) {
		this.name = name;
		this.zones = zones;
		this.dialogueLines = dialogueLines;
	}

	public boolean nextLine() {
		this.index += 1;
		return (this.index < this.dialogueLines.length);
	}

	public boolean playLine() {
		if (this.dialogueStatus != DialogueStatus.PLAYING && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].play();
			this.dialogueStatus = DialogueStatus.PLAYING;

			return true;
		}

		return false;
	}

	public boolean pauseLine() {
		if (this.dialogueStatus != DialogueStatus.PAUSED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].pause();
			this.dialogueStatus = DialogueStatus.PAUSED;

			return true;
		}

		return false;
	}

	public boolean stopLine() {
		if (this.dialogueStatus != DialogueStatus.STOPPED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].stop();
			this.dialogueStatus = DialogueStatus.STOPPED;

			return true;
		}

		return false;
	}

	public boolean isInZone(int x, int y) {
		for (Zone zone : this.zones) {
			if (zone.isInZone(x, y)) {
				return true;
			}
		}

		return false;
	}

	public String getName() {
		return this.name;
	}
}
