package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.util.LinkedList;
import java.util.List;


public class Monk_astral extends BaseArchetype {
    static final long serialVersionUID = 2701L;
    public Monk_astral(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_astral);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Arms of the Astral Self");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Visage of the Astral Self");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Awakening of the Astral Self");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Complete Astral Self");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Arms of the Astral Self", "On your turn, you can spend 2 ki points as a bonus action to summon the arms of your astral self for 10 minutes. These spectral arms hover near your shoulders. You determine the arms’ appearance based on the qualities of your character.\n" +
                    "\n" +
                    "While your astral arms are summoned, you gain the following benefits:\n" +
                    "\n" +
                    "- You can use your Wisdom modifier in place of your Strength modifier when making Strength checks and Strength saving throws.\n" +
                    "- The arms are monk weapons and have a reach of 10 feet. The arms deal radiant or necrotic damage (your choice). When you attack with the arms, you can use your Wisdom modifier instead of your Strength or Dexterity modifier for the attack and damage rolls.\n" +
                    "- Immediately after you use the Attack action with your astral arms on your turn, you can make one extra attack with your astral arms as a bonus action. The number of extra attacks increases when you reach certain levels in this class, increasing to two at 11th level and three at 17th level.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Visage of the Astral Self", "On your turn, you can spend 1 ki point as a bonus action, or as part of summoning your astral arms, to summon this visage for 10 minutes. The spectral visage covers your face like a helmet or mask. You determine its appearance based on the qualities of your character.\n" +
                    "\n" +
                    "While your visage is summoned, you gain the following benefits.\n" +
                    "\n" +
                    "[Wisdom of the Spirit] You have advantage on Wisdom (Insight) and Charisma (Intimidation) checks.\n" +
                    "\n" +
                    "[Astral Sight] You can see normally in darkness, both magical and nonmagical, to a distance of 120 feet.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Awakening of the Astral Self", "While you have both your astral arms and visage summoned, you gain the following benefits.\n" +
                    "\n" +
                    "[Deflect Energy] When you take acid, cold, fire, lightning, or force damage, you can use your reaction to deflect it. When you do so, the damage you take is reduced by 1d10 + your Wisdom modifier + your monk level.\n" +
                    "\n" +
                    "[Empowered Arms] Once on each of your turns when you hit a target with your astral arms, you can deal 1D" + ((Monk)iCharac._class).getMonkDamageDie(iLevel) + " extra damage to the target.\n" +
                    "\n" +
                    "[Word of the Spirit] When you speak through your visage, you can direct your words to a creature of your choice that you can see within 30 feet of you, making it so only that creature can hear you. Alternatively, you can amplify your voice so that all creatures within 600 feet can hear you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Complete Astral Self", "On your turn, you can spend 10 ki points as a bonus action to summon the arms, visage, and body of your astral self for 10 minutes. This spectral body covers your physical form like a suit of armor, connecting with the arms and visage. You determine its appearance based on the qualities of your character.\n" +
                    "\n" +
                    "While your astral self is summoned, you gain the following benefits.\n" +
                    "\n" +
                    "[Armor of the Spirit] You gain a +2 bonus to AC while you aren’t incapacitated.\n" +
                    "\n" +
                    "[Astral Barrage] Whenever you use the Extra Attack feature to attack twice, you can instead attack three times using your astral arms.\n" +
                    "\n" +
                    "[Ki Consumption] When a creature within 10 feet of you is reduced to 0 hit points, you can use your reaction to regain ki points equal to your Wisdom modifier (minimum 1).", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        return powers;
    }

    @Override
    public List<Attack> getSpecialClassAttacks(Character iCharacter) {
        LinkedList<Attack> astralArms = new LinkedList<>();

        int level = iCharacter._level;
        int proficiency = iCharacter.getProficiencyBonus();

        Weapon weapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
        weapon._hasReach = true;
        weapon._name = "Astral Arms";
        weapon._damageType = Enumerations.DamageTypes.RADIANT;
        weapon._diceCount = 1;
        weapon._diceValue = ((Monk)iCharacter._class).getMonkDamageDie(level);

        int dmg = 0;
        int atk = iCharacter.getProficiencyBonus();

        // STR or WIS or DEX
        atk += Math.max(iCharacter.getModifier(Enumerations.Attributes.STR), Math.max(iCharacter.getModifier(Enumerations.Attributes.WIS), iCharacter.getModifier(Enumerations.Attributes.DEX)));
        dmg += Math.max(iCharacter.getModifier(Enumerations.Attributes.STR), Math.max(iCharacter.getModifier(Enumerations.Attributes.WIS), iCharacter.getModifier(Enumerations.Attributes.DEX)));

        astralArms.add(new Attack(
                weapon,
                "When using Astral Arms only. Bonus Action extra attacks: " + (level >= 17 ? 3 : level >= 11 ? 2 : 1),
                atk,
                dmg,
                iCharacter.getAttacksPerRound(),
                R.drawable.ic_monk
        ));

        return astralArms;
    }
}
