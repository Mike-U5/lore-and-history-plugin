package com.loreandhistory;

import com.loreandhistory.classes.DialogueLine;
import com.loreandhistory.classes.Story;
import com.loreandhistory.classes.Zone;

final public class StoryRegistry {
	private static final Story[] stories = {
		new Story("a_curious_client",
			new Zone[] {
				new Zone(3224, 3233, 3238, 3245)
			},
			new DialogueLine[]{
				new DialogueLine("\"Ah, you're after that old tale, are you?” The mercenary chuckled and set down his flagon on the table. “Well, stranger, I hope you’re sitting comfortably, because it’s a long one.\""),
				new DialogueLine("He stroked his beard and settled back into his chair, its aged joints creaking."),
				new DialogueLine("Let’s see now… it all started just after they lost the Accord, back in the 135th decade."),
				new DialogueLine("The lizardmen were pummeling the border and we young recruits were sent out to face ‘em.")
			})
	};
}
