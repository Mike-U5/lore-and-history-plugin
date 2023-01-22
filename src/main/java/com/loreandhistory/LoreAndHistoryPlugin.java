package com.loreandhistory;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.PlayerChanged;
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
	public void onGameStateChanged(final PlayerChanged e)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
	}

	@Provides
	LoreAndHistoryConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LoreAndHistoryConfig.class);
	}
}
