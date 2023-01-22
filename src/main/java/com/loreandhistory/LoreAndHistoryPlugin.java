package com.loreandhistory;

import com.google.inject.Provides;
import com.loreandhistory.classes.Story;
import com.loreandhistory.components.StoryButton;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "LoreAndHistory"
)
final public class LoreAndHistoryPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private LoreAndHistoryConfig config;

	private StoryButton storyButton;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onClientTick(final ClientTick e)
	{
		final Player player = this.client.getLocalPlayer();

		if (player != null && this.storyButton != null) {
			final Story story = StoryRegistry.getStoryForZone(player.getWorldLocation());

			if (story != null) {
				this.storyButton.setStory(story);
				//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + story.getName(), null);
			}
		}
	}

	@Subscribe
	public void onWidgetLoaded(final WidgetLoaded e)
	{
		if (e.getGroupId() == WidgetInfo.FIXED_VIEWPORT_MINIMAP.getGroupId()) {
			final Widget parent = this.client.getWidget(WidgetInfo.FIXED_VIEWPORT_MINIMAP);

			if (parent != null) {
				this.storyButton = new StoryButton(parent);
			}
		}
	}

	@Provides
	LoreAndHistoryConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LoreAndHistoryConfig.class);
	}
}
