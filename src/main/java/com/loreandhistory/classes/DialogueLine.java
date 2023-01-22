package com.loreandhistory.classes;

final public class DialogueLine {
	private final String text;
	private int durationInMs;

	public DialogueLine(String text) {
		this.text = text;
		this.durationInMs = 1000 + (text.length() * 40);
	}

	public void play()
	{

	}

	public void pause()
	{

	}

	public void stop()
	{

	}
}
