package com.loreandhistory;

import com.google.inject.Provides;
import com.loreandhistory.components.StoryButton;
import com.loreandhistory.controller.StoryController;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
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
	public static Client CLIENT;

	@Inject
	private Client client;
	@Inject
	private LoreAndHistoryConfig config;

	@Override
	protected void startUp() throws Exception {
		LoreAndHistoryPlugin.CLIENT = this.client;
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception {
		log.info("Example stopped!");
	}

	@Subscribe
	public void onClientTick(final ClientTick e) {
		if (StoryController.isInitialised()) {
			StoryController.get().tick();
		}
	}

	@Subscribe
	public void onWidgetLoaded(final WidgetLoaded e) {
		if (e.getGroupId() == WidgetInfo.FIXED_VIEWPORT_MINIMAP.getGroupId() && !StoryController.isInitialised()) {
			final Widget parent = this.client.getWidget(WidgetInfo.FIXED_VIEWPORT_MINIMAP);

			if (parent != null) {
				StoryController.initialise(this.client, new StoryButton(parent));
			}
		}
	}

	@Provides
	LoreAndHistoryConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(LoreAndHistoryConfig.class);
	}
}
