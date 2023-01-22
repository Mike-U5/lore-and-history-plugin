package com.loreandhistory;

import com.google.inject.Provides;
import com.loreandhistory.classes.Story;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "LoreAndHistory"
)
public class LoreAndHistoryPlugin extends Plugin
{
	private int tickCounter = 0;

	@Inject
	private Client client;

	@Inject
	private LoreAndHistoryConfig config;

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
		this.tickCounter++;

		if (this.tickCounter % 10 == 0) {
			final Player player = this.client.getLocalPlayer();

			if (player != null) {
				final Story story = StoryRegistry.getStoryForZone(player.getWorldLocation());

				if (story != null) {
					client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + story.getName(), null);
				}
			}

			this.tickCounter = 0;
		}
	}

	@Provides
	LoreAndHistoryConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LoreAndHistoryConfig.class);
	}
}
