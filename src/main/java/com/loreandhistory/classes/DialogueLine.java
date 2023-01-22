package com.loreandhistory.classes;

import javax.inject.Inject;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;

final public class DialogueLine {
	@Inject
	private Client client;
	private final String text;
	private final int durationInMs;

	public DialogueLine(String text) {
		this.text = text;
		this.durationInMs = 1000 + (text.length() * 40);
	}

	public void play() {
		this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", this.text, null);
	}

	public void pause() {

	}

	public void stop() {

	}

	public int getDuration() {
		return this.durationInMs;
	}
}
