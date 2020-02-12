package com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.util.LinkedList;
import java.util.List;

public class BloodHunter_ghostslayer extends BaseArchetype {
    static final long serialVersionUID = 2301L;

    @Override
    public String getName() {
        return App.getResString(R.string.bloodhunter_ghostslayer);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Rite of the Dawn");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Hallowed Veins");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Supernal Surge");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Gravesight");
        }
        else if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Vengeful Spirit");
        }

        return levelUp;
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        if (iLevel >= 3) {
            powers.add(new Power("Rite of the Dawn","Your rite damage is radiant type. The damage you suffer from activating this rite is halved.\n" +
                    "\n" +
                    "If you hit an Undead creature with your Rite of the Dawn, it suffers additional radiant damage equal to your Wisdom modifier.\n" +
                    "\n" +
                    "Upon reaching 11th level, any creature you hit with your Rite of the Dawn suffers this additional radiant damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Hallowed Veins","Your blood curses can now affect any creature, regardless of their form or lack of blood.\n" +
                    "\n" +
                    "In addition, when you amplify a use of your Blood Maledict feature, after rolling the amplify damage you would sustain, you may choose to reroll the die and must use the new roll.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Supernal Surge","When you use the Attack action on your turn, you can expend a use of this feature to attack three times, instead of twice, and you temporarily become spectral. Until the end of your next turn, you can move through other creatures and objects as if they were difficult terrain. You take 1d10 force damage if you end your turn inside an object. If you are inside an object when you are no longer spectral, you are immediately shunted to the nearest unoccupied space that you can occupy and take force damage equal to twice the number of feet you moved. You may expend a use of this feature as a bonus action instead of making an attack.", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Gravesight","You can see through magical darkness up to 30 feet, as well as see invisible creatures and objects up to 30 feet.", "30ft", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Vengeful Spirit","Whenever your hit points drop to 0, you can choose to let your soul emerge from your body to fight on. Your body remains unconscious and subject to death saving throws per normal. At the beginning of your next turn, you manifest a spirit form in your space that picks up your weapons and continues fighting on, acting on your turn and every one of your subsequent turns under your control. Your spirit form has your physical attributes and armor class, as well as your weapons and ammunition, and can move through other creatures and objects as if they were difficult terrain. This form is immune to cold, necrotic, and non-magical weapon damage. Your spirit form has access to all of your abilities and suffers no damage from your Crimson Rite feature.\n" +
                    "\n" +
                    "If your spirit form takes any damage, your body dies, or you regain any hit points, your spirit form vanishes. If your spirit form vanishes, it drops your weapons in its space.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int level) {
        LinkedList<Fettle> fettles = new LinkedList<>();


        return  fettles;
    }
}
