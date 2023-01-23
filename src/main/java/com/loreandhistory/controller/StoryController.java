package com.loreandhistory.controller;

import com.loreandhistory.StoryRegistry;
import com.loreandhistory.classes.Story;
import com.loreandhistory.components.StoryButton;
import net.runelite.api.Client;

final public class StoryController {
	public static StoryController instance;

	private final Client client;
	private final StoryButton storyButton;
	private Story story = null;

	public static void initialise(final Client client, final StoryButton storyButton) {
		StoryController.instance = new StoryController(client, storyButton);
	}

	public static StoryController get() {
		return StoryController.instance;
	}

	public static boolean isInitialised()
	{
		return StoryController.instance != null;
	}

	public StoryController(final Client client, final StoryButton storyButton) {
		this.client = client;
		this.storyButton = storyButton;
	}

	public void tick() {
		if (this.story == null || !this.story.isInProgress()) {
			// Set new active story
			this.story = StoryRegistry.getStoryForZone(this.client.getLocalPlayer().getWorldLocation());
			this.storyButton.setStory(this.story);
		} else {
			// Progress active story
			this.story.tick(this.client);
		}
	}
}
