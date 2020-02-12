package com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class BloodHunter_profane extends BaseArchetype {
    static final long serialVersionUID = 2303L;

    @Override
    public String getName() {
        return App.getResString(R.string.bloodhunter_profane_soul);
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        if (iLevel == 3) {
            return R.array.profaneSoulPatrons;
        }

        return -1;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Pact Magic");
            levelUp.add("You gained Rite Focus");
            levelUp.add("You know 2 Warlock cantrips");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Mystic Frenzy");
            levelUp.add("You gained Revealed Arcana");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You learned a 3rd cantrip.");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Diabolic Channel");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Unsealed Arcana");
        }
        else if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Soul Syphon");
        }

        return levelUp;
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        if (iLevel >= 3) {
            int slotLevel = iLevel >= 19 ? 4 : iLevel >= 13 ? 3 : iLevel >= 7 ? 2 : 1;
            int spells = iLevel >= 5 ? 2 : 1;
            powers.add(new Power("Pact Magic","Spell slot level: " + slotLevel, "", spells, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS), false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Rite Focus", "While you have an active rite, you can use your weapon as a spellcasting focus for your spells. Your chosen pact also enhances your rite:\n" + getRiteFocus(), "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Mystic Frenzy","When you use your action to cast a cantrip, you can make one weapon attack as a bonus action.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Revealed Arcana",getRevealedArcana(), "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Diabolic Channel","As an action, you can imbue your rite-enhanced weapon with one warlock spell you can cast or is already active, then make a single attack with that weapon. The spell must be using a warlock or profane soul spell slot. If that attack hits, all spell attack rolls for the imbued spell hit the target automatically and are considered part of the single weapon attack. The target takes weapon damage, and is subject to the effects of the spell, expending a spell slot accordingly. If the attack has advantage, the target’s initial saving throw against the spell has disadvantage. If the attack misses, the spell has no effect. Area spells originate from the target’s space. \n" +
                    "\n" +
                    "The spell must be of 1st level or higher, have a casting time of 1 action, or require an action to activate an already active concentration spell.", "", -1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Unsealed Arcana",getUnsealedArcana(), "", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Soul Syphon","When you reduce a creature to 0 hit points with an attack, kill the creature, and they have a challenge rating of 15 or above, you recover an expended spell slot.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        return powers;
    }

    protected String getRiteFocus() {
        if (_chosenStringFeature.equals("Archfey")) {
            return "If you deal rite damage to a creature, that creature loses any half or three-quarters cover bonuses, as well as invisibility, until the beginning of your next turn.";
        }
        if (_chosenStringFeature.equals("Celestial")) {
            return "You can expend a use of your Blood Maledict feature as a bonus action to heal one creature within 60 feet of you. They regain hit points equal to your crimson rite damage die rolled twice + your Wisdom modifier (minimum of 1).";
        }
        if (_chosenStringFeature.equals("Fiend")) {
            return "While using the Rite of the Flame, if you roll a 1 on your rite damage die, you may reroll the die. You may reroll only once per attack.";
        }
        if (_chosenStringFeature.equals("Great Old One")) {
            return "Whenever you deal a critical hit to a creature, that creature must make a Wisdom saving throw against your spell save DC. On a failure, the creature is frightened of you until the end of your next turn.";
        }
        if (_chosenStringFeature.equals("Hexblade")) {
            return "Whenever you target a creature with a blood curse, your next attack against the cursed creature is a critical hit on a roll of 19 or 20 on the attack.";
        }
        if (_chosenStringFeature.equals("Undying")) {
            return "Whenever you reduce a hostile creature to 0 hit points with a weapon attack, and kill it, you regain hit points equal to your crimson rite damage die.";
        }

        return null;
    }

    protected String getRevealedArcana() {
        if (_chosenStringFeature.equals("Archfey")) {
            return "You can cast blur once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }
        if (_chosenStringFeature.equals("Celestial")) {
            return "You can cast lesser restoration once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }
        if (_chosenStringFeature.equals("Fiend")) {
            return "You can cast scorching ray once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }
        if (_chosenStringFeature.equals("Great Old One")) {
            return "You can cast detect thoughts once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }
        if (_chosenStringFeature.equals("Hexblade")) {
            return "You can cast branding smite once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }
        if (_chosenStringFeature.equals("Undying")) {
            return "You can cast blindness/deafness once using a profane soul spell slot. You can’t do so again until you finish a long rest.";
        }

        return null;
    }

    protected String getUnsealedArcana() {
        if (_chosenStringFeature.equals("Archfey")) {
            return "You can cast slow once without using a profane soul spell slot.";
        }
        if (_chosenStringFeature.equals("Celestial")) {
            return "You can cast revivify once without using a profane soul spell slot.";
        }
        if (_chosenStringFeature.equals("Fiend")) {
            return "You can cast fireball once without using a profane soul spell slot. ";
        }
        if (_chosenStringFeature.equals("Great Old One")) {
            return "You can cast haste once without using a profane soul spell slot.";
        }
        if (_chosenStringFeature.equals("Hexblade")) {
            return "You can cast blink once without using a profane soul spell slot.";
        }
        if (_chosenStringFeature.equals("Undying")) {
            return "You can cast bestow curse once without using a profane soul spell slot. ";
        }

        return null;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int level) {
        LinkedList<Fettle> fettles = new LinkedList<>();


        return  fettles;
    }
}
