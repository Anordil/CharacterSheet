package com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by totou on 15/06/2016.
 */
public class Barbarian_totem extends BaseArchetype {
    static final long serialVersionUID = 2104L;
    public Barbarian_totem(){}

    @Override
    public String getName() {
        String name = App.getResString(R.string.barbarian_totem);

        if (_chosenStringFeature != null) {
            name += " - " + _chosenStringFeature;
        }

        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Totem Spirit");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Aspect of the Beast");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Spirit Walker");
        }
        else if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Totemic Attunement");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // This one is for any animal
        if (iLevel >= 10) {
            powers.add(new Power("Spirit Walker", "You can cast Commune with Nature as a ritual.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (_chosenStringFeature.equals("Bear")) {
            if (iLevel >= 3) {
                powers.add(new Power("Totem Spirit", "Resistance to all damage type except psychic while raging.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 6) {
                powers.add(new Power("Aspect of the Best", "You gain the might of a bear. Your carrying capacity (including maximum load and maximum lift) is doubled, and you have advantage on Strength checks made to push, pull, lift, or break objects.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 14) {
                powers.add(new Power("Totemic Attunement", "While you’re raging, any creature within 5 feet of you that’s hostile to you has disadvantage on attack rolls against targets other than you or another character with this feature. An enemy is immune to this effect if it can’t see or hear you or if it can’t be frightened.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        } else if (_chosenStringFeature.equals("Eagle")) {
            if (iLevel >= 3) {
                powers.add(new Power("Totem Spirit", "While you’re raging, other creatures have disadvantage on opportunity attack rolls against you, and you can use the Dash action as a bonus action on your turn. The spirit of the eagle makes you into a predator who can weave through the fray with ease.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 6) {
                powers.add(new Power("Aspect of the Best", "You gain the eyesight of an eagle. You can see up to 1 mile away with no difficulty, able to discern even fine details as though looking at something no more than 100 feet away from you. Additionally, dim light doesn’t impose disadvantage on your Wisdom (Perception) checks.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 14) {
                powers.add(new Power("Totemic Attunement", "While raging, you have a flying speed equal to your current walking speed. This benefit works only in short bursts; you fall if you end your turn in the air and nothing else is holding you aloft.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        } else if (_chosenStringFeature.equals("Elk")) {
            if (iLevel >= 3) {
                powers.add(new Power("Totem Spirit", "While you are raging and aren't wearing heavy armor, your walking speed increases by 15 feet. The spirit of the elk makes you extraordinarily swift.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 6) {
                powers.add(new Power("Aspect of the Best", "Whether mounted or on foot, your travel pace is doubled, as is the travel pace of up to ten companions while they’re within 60 feet of you and you’re not incapacitated (see “Adventuring,” for rules on travel pace). The elk spirit helps you roam far and fast.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 14) {
                powers.add(new Power("Totemic Attunement", "While raging, you can use a bonus action during your move to pass through the space of a Large or smaller creature. That creature must succeed on a Strength saving throw (DC 8 + your Strength bonus + your proficiency bonus) or be knocked prone and take bludgeoning damage equal to 1d12 + your Strength modifier.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        } else if (_chosenStringFeature.equals("Tiger")) {
            if (iLevel >= 3) {
                powers.add(new Power("Totem Spirit", "While raging, you can add 10 feet to your long jump distance and 3 feet to your high jump distance. The spirit of the tiger empowers your leaps.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 6) {
                powers.add(new Power("Aspect of the Best", "You gain proficiency in two skills from the following list: Athletics, Acrobatics, Stealth, and Survival. The cat spirit hones your survival instincts.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 14) {
                powers.add(new Power("Totemic Attunement", "While you’re raging, if you move at least 20 feet in a straight line toward a Large or smaller target right before making a melee weapon attack against it, you can use a bonus action to make an additional melee weapon attack against it.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        } else { // Wolf
            if (iLevel >= 3) {
                powers.add(new Power("Totem Spirit", "While you’re raging, your friends have advantage on melee attack rolls against any creature within 5 feet of you that is hostile to you. The spirit of the wolf makes you a leader of hunters.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 6) {
                powers.add(new Power("Aspect of the Best", "You gain the hunting sensibilities of a wolf. You can track other creatures while traveling at a fast pace, and you can move stealthily while traveling at a normal pace (see “Adventuring,” for rules on travel pace).", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (iLevel >= 14) {
                powers.add(new Power("Totemic Attunement", "While you’re raging, you can use a bonus action on your turn to knock a Large or smaller creature prone when you hit it with melee weapon attack.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }

        return powers;
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        if (iLevel == 3) {
            return R.array.barbarianTotemAnimals;
        }
        return -1;
    }
}
