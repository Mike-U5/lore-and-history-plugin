package com.loreandhistory.components;

import com.loreandhistory.classes.Story;
import net.runelite.api.Client;
import net.runelite.api.ScriptEvent;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetType;

//1706: Post
//2149: Letter
//2275: League Icon
//2415: Newspaper
//3018: Paper with question mark
//3295: Clan Book Icon
//3424: Yellow scroll
//3431: Yellow book
//3566: Scroll with question mark orange outline
//4175: Book relic (4177)
//4753: Play(ish) button
final public class StoryButton {
	private final Widget widget;
	private Client client;
	private Story activeStory = null;

	public StoryButton(final Widget parent, final Client client) {
		this.client = client;

		this.widget = parent.createChild(-1, WidgetType.GRAPHIC);
		this.widget.setPos(205, 0);
		this.widget.setSize(40, 40);
		this.widget.setSpriteId(2149);

		this.widget.setAction(0, "Play");
		this.widget.setAction(1, "Pause");
		this.widget.setAction(2, "Stop");

		this.widget.setOnOpListener((JavaScriptCallback)this::onOptionSelected);
		this.widget.setOnMouseOverListener((JavaScriptCallback)this::onMouseHover);
		this.widget.setOnMouseLeaveListener((JavaScriptCallback)this::onMouseLeave);
		this.widget.setHasListener(true);
	}

	public void setStory(final Story story) {
		this.activeStory = story;
	}

	private void onOptionSelected(final ScriptEvent e)
	{
		if (e.getOp() == 1 && this.activeStory != null) {
			this.activeStory.start();
		} else if (e.getOp() == 2) {
			this.activeStory.pause();
		} else if (e.getOp() == 3) {
			this.activeStory.stop();
		}
	}

	private void onMouseHover(final ScriptEvent e)
	{
		//LoreAndHistoryPlugin.clientSingleton.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "HOVER", null);
	}

	private void onMouseLeave(final ScriptEvent e)
	{
		//LoreAndHistoryPlugin.clientSingleton.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "LEAVE", null);
	}
}
