package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_ancients extends BaseArchetype {
    static final long serialVersionUID = 224L;

    public Paladin_ancients(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_ancients);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Aura of Warding");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Undying Sentinel");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Elder Champion");
        }

        return levelUp;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> perks = new LinkedList<>();

        if (character._level >= 7) {
            perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.SPELLS.ordinal()));
        }

        return perks;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "Nature’s Wrath. You can use your Channel Divinity to invoke primeval forces to ensnare a foe. As an action, you can cause spectral vines to spring up and reach for a creature within 10 feet of you that you can see. The creature must succeed on a Strength or Dexterity saving throw (its choice) or be restrained. While restrained by the vines, the creature repeats the saving throw at the end of each of its turns. On a success, it frees itself and the vines vanish.\n" +
                    "\n" +
                    "Turn the Faithless. You can use your Channel Divinity to utter ancient words that are painful for fey and fiends to hear. As an action, you present your holy symbol, and each fey or fiend within 30 feet of you that can hear you must make a Wisdom saving throw. On a failed save, the creature is turned for 1 minute or until it takes damage.\n" +
                    "\n" +
                    "A turned creature must spend its turns trying to move as far away from you as it can, and it can’t willingly move to a space within 30 feet of you. It also can’t take reactions. For its action, it can use only the Dash action or try to escape from an effect that prevents it from moving. If there’s nowhere to move, the creature can use the Dodge action.\n" +
                    "\n" +
                    "If the creature’s true form is concealed by an illusion, shapeshifting, or other effect, that form is revealed while it is turned.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            String range = iLevel >= 18 ? "30ft" : "10ft";
            powers.add(new Power("Aura of Warding", "You and friendly creatures within " + range +" of you have resistance to damage from spells.", range, -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Undying Sentinel", "When you are reduced to 0 hit points and are not killed outright, you can choose to drop to 1 hit point instead. Once you use this ability, you can’t use it again until you finish a long rest.\n" +
                    "\n" +
                    "Additionally, you suffer none of the drawbacks of old age, and you can’t be aged magically.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Elder Champion", "Using your action, you undergo a transformation. For 1 minute, you gain the following benefits:\n" +
                    "\n" +
                    "At the start of each of your turns, you regain 10 hit points.\n" +
                    "Whenever you cast a paladin spell that has a casting time of 1 action, you can cast it using a bonus action instead.\n" +
                    "Enemy creatures within 10 feet of you have disadvantage on saving throws against your paladin spells and Channel Divinity options.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
