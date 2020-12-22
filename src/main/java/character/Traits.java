package character;

import java.util.Arrays;
import java.util.List;

public class Traits {
	public static final List<String> traits=Arrays.asList("", "Agility", "Smarts", "Spirit", "Strength", "Vigor",
			"Academics", "Athletics", "Battle", "Boating", "Common Knowledge", "Driving", "Electronics", "Fighting",
			"Gambling", "Hacking", "Healing", "Intimidation", "Notice", "Occult", "Performance", "Persuasion",
			"Piloting", "Repair", "Research", "Riding", "Science", "Shooting", "Stealth", "Survival", "Taunt", "Thievery",
			"Faith", "Focus", "Psionics", "Spellcasting", "Techno-Wizardry", "Weird Science");
	
	//public static final int length=38;
	
	public static int index(String trait) {
		return traits.indexOf(trait);
	}

}
