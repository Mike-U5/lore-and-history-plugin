package com.loreandhistory.components;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.ScriptEvent;
import net.runelite.api.SpriteID;
import net.runelite.api.widgets.JavaScriptCallback;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetType;

final public class LoreButton {
	private final Client client;
	private final Widget widget;

	public LoreButton(final Client client, final Widget parent) {
		this.client = client;

		this.widget = parent.createChild(-1, WidgetType.GRAPHIC);
		this.widget.setPos(205, 0);
		this.widget.setSize(40, 40);
		this.widget.setSpriteId(SpriteID.SPELL_NPC_CONTACT);

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
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "CHOSE " + e.getOp(), null);
	}

	private void onMouseHover(final ScriptEvent e)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "HOVER", null);
	}

	private void onMouseLeave(final ScriptEvent e)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "LEAVE", null);
	}
}
