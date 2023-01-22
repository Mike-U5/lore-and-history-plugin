package com.loreandhistory.components;

import javax.inject.Inject;
import net.runelite.api.ChatMessageType;
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
final public class LoreButton {
	@Inject
	private Client client;
	private int spriteId = 1706;
	private final Widget widget;

	public LoreButton(final Widget parent) {
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

	private void onOptionSelected(final ScriptEvent e)
	{
		if (e.getOp() == 1) {

		} else if (e.getOp() == 2) {

		} else if (e.getOp() == 3) {

		}

		this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "CHOSE " + e.getOp(), null);
	}

	private void onMouseHover(final ScriptEvent e)
	{
		this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "HOVER", null);
	}

	private void onMouseLeave(final ScriptEvent e)
	{
		this.client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "LEAVE", null);
	}
}
