package com.loreandhistory.classes;

import com.loreandhistory.LoreAndHistoryPlugin;
import net.runelite.api.ChatMessageType;

final public class DialogueLine {
	private final String text;
	private final int durationInMs;

	public DialogueLine(final String text) {
		this.text = text;
		this.durationInMs = 1000 + (text.length() * 40);
	}

	public void playDialogue() {
		LoreAndHistoryPlugin.CLIENT.addChatMessage(ChatMessageType.GAMEMESSAGE, "", this.text, null);
	}

	public void pauseDialogue() {

	}

	public void stopDialogue() {

	}

	public int getDuration() {
		return this.durationInMs;
	}
}
