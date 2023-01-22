package com.loreandhistory.classes;

import com.loreandhistory.enums.DialogueStatus;

final public class Story {
	final private String name;
	final private DialogueLine[] dialogueLines;
	final private Zone[] zones;

	private int index = 0;
	private int tickCounter = 0;
	private DialogueStatus dialogueStatus = DialogueStatus.STOPPED;

	public Story(String name, Zone[] zones, DialogueLine[] dialogueLines) {
		this.name = name;
		this.zones = zones;
		this.dialogueLines = dialogueLines;
	}

	public void tick() {
		if (this.dialogueStatus == DialogueStatus.PLAYING) {
			final DialogueLine dialogue = this.dialogueLines[this.index];

			if (dialogue != null && this.tickCounter > dialogue.getDuration()) {
				this.sayNextLine();
			} else {
				this.tickCounter += 20; // Client ticks every 20 ms
			}
		}
	}

	private void sayNextLine() {
		this.index += 1;
		this.tickCounter = 0;

		if (this.index < this.dialogueLines.length) {
			this.dialogueLines[this.index].playDialogue();
		} else {
			this.dialogueStatus = DialogueStatus.COMPLETED;
		}
	}

	public void startStory() {
		if (this.dialogueStatus == DialogueStatus.STOPPED || this.dialogueStatus == DialogueStatus.COMPLETED) {
			this.dialogueStatus = DialogueStatus.PLAYING;
			this.index = -1;
			this.sayNextLine();
		}
	}

	public void pauseStory() {
		if (this.dialogueStatus != DialogueStatus.PAUSED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].pauseDialogue();
			this.dialogueStatus = DialogueStatus.PAUSED;
		}
	}

	public void stopStory() {
		if (this.dialogueStatus != DialogueStatus.STOPPED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].stopDialogue();
			this.dialogueStatus = DialogueStatus.STOPPED;
		}
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
