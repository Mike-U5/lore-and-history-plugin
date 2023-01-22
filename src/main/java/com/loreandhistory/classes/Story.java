package com.loreandhistory.classes;

import com.loreandhistory.enums.DialogueStatus;
import net.runelite.api.Client;

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

	public void tick(final Client client) {
		if (this.dialogueStatus == DialogueStatus.PLAYING) {
			final DialogueLine dialogue = this.dialogueLines[this.index];

			if (dialogue != null && this.tickCounter > dialogue.getDuration()) {
				this.nextLine(client);
			} else {
				this.tickCounter += 20; // Client ticks every 20 ms
			}
		}
	}

	private void nextLine(final Client client) {
		this.index += 1;
		this.tickCounter = 0;

		if (this.index < this.dialogueLines.length) {
			this.playLine(client);
		} else {
			this.dialogueStatus = DialogueStatus.COMPLETED;
		}
	}

	private void playLine(final Client client) {
		if (this.dialogueStatus != DialogueStatus.PLAYING && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].play(client);
			this.dialogueStatus = DialogueStatus.PLAYING;
		}
	}

	public void start(final Client client) {
		if (this.dialogueStatus == DialogueStatus.STOPPED || this.dialogueStatus == DialogueStatus.COMPLETED) {
			this.index = 0;
			this.dialogueStatus = DialogueStatus.PLAYING;
			this.playLine(client);
		}
	}

	public void pause(final Client client) {
		if (this.dialogueStatus != DialogueStatus.PAUSED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].pause(client);
			this.dialogueStatus = DialogueStatus.PAUSED;
		}
	}

	public void stop(final Client client) {
		if (this.dialogueStatus != DialogueStatus.STOPPED && this.index >= 0 && this.index <= this.dialogueLines.length) {
			this.dialogueLines[this.index].stop(client);
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
