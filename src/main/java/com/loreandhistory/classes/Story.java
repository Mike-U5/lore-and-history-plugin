package com.loreandhistory.classes;

import com.loreandhistory.enums.DialogueStatus;

final public class Story {
	final private String name;
	final private DialogueLine[] dialogueLines;
	private int index = 0;
	private DialogueStatus dialogueStatus = DialogueStatus.STOPPED;

	public Story(String name, DialogueLine[] dialogueLines) {
		this.name = name;
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
			this.dialogueLines[this.index].pause();
			this.dialogueStatus = DialogueStatus.STOPPED;

			return true;
		}

		return false;
	}
}
