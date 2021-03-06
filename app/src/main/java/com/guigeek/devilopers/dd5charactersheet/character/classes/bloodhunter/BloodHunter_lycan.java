package com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.util.LinkedList;
import java.util.List;

public class BloodHunter_lycan extends BaseArchetype {
    static final long serialVersionUID = 204L;

    @Override
    public String getName() {
        return App.getResString(R.string.bloodhunter_lycan);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Welcome to the order of the Lycan.");
            levelUp.add("You now have Heightened Senses.");
            levelUp.add("You gained Hybrid Transformation.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Stalker's Prowess");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Advanced Transformation");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Iron Volition");
        }
        else if (iNewCharacterLevel == 18) {
            levelUp.add("You can now use Hybrid Transformation 3x / rest.");
            levelUp.add("You gained the Blood Curse of the Howl");
        }

        return levelUp;
    }

    @Override
    public List<Attack> getSpecialClassAttacks(Character iCharacter, int level) {
        LinkedList<Attack> hybridStrikes = new LinkedList<>();

        int proficiency = iCharacter.getProficiencyBonus();

        Weapon weapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
        weapon._isMagical = level >= 7;
        weapon._name = "Predatory Strikes";
        weapon._damageType = Enumerations.DamageTypes.SLASHING;
        weapon._diceCount = 1;
        weapon._diceValue = level >= 18 ? 10 : level >= 11 ? 8 : 6;

        int dmg = 0;
        int atk = iCharacter.getProficiencyBonus();

        // Feral might
        dmg += (int)Math.floor(proficiency/2);

        // Predatory strikes - DEX or STR
        atk += Math.max(iCharacter.getModifier(Enumerations.Attributes.STR), iCharacter.getModifier(Enumerations.Attributes.DEX));
        dmg += Math.max(iCharacter.getModifier(Enumerations.Attributes.STR), iCharacter.getModifier(Enumerations.Attributes.DEX));

        // Beastly precision
        if (level >= 11) {
            atk += (int)Math.floor(proficiency/2);
        }

        hybridStrikes.add(new Attack(
                weapon,
                "When in hybrid form only. You may use a Bonus Action to attack again if you used your Action to attack.",
                atk,
                dmg,
                iCharacter.getAttacksPerRound(),
                R.drawable.ic_werewolf
        ));

        return hybridStrikes;
    }

    public String getUnarmedStrikesDice(int iLevel) {
        if (iLevel >= 18) return "1D10";
        if (iLevel >= 11) return "1D8";
        return "1D6";
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        if (iLevel >= 3) {
            powers.add(new Power("Hybrid Transformation","Transform for " + (iLevel >= 11? "30":"10") + "mn. When transformed:" +
                    "\n- Melee damage roll: +" + (int)Math.floor(iCharac.getProficiencyBonus()/2) +
                    "\n- Advantage on STR checks & saving throws" +
                    "\n- Resistance to bludgeoning, piercing and slashing damage from nonmagical weapons that aren't silvered." +
                    "\n- +1 to AC if not wearing Heavy Armor" +
                    "\n- Unarmed strikes are considered a single weapon for Crimson Rites. You may use DEX instead of STR for attack and damage rolls of unarmed strikes." +
                    " If you use your Action to attack as an unarmed strike, you may use a Bonus Action to make another unarmed strike." +
                    " Your unarmed strikes deal " + getUnarmedStrikesDice(iLevel) + " slashing damages." +
                    "\n- You are vulnerable to silvered weapons." +
                    "\n- At the start of your turn, if you’ve taken any damage since the beginning of your last turn, you must make a Wisdom saving throw to maintain control. The DC equals 10, or half of the total damage you’ve taken from attacks since your last turn, whichever number is higher. On a failed save, you must move directly towards the nearest creature to you and use the Attack action against that creature. If there is more than one possible target, the DM chooses the target. You then regain control for the remainder of your turn. If you are under an effect that prevents you from concentrating (like the Barbarian’s rage feature), you automatically fail this saving throw." +
                    (iLevel >= 11?
                            "\n- Regain " + (1 + iCharac.getModifier(Enumerations.Attributes.CON)) + "HP at the start of your turn if you have no more than half your HP (and more than 0)."
                            + "\n- Bonus to unarmed melee attack rolls: " + (int)Math.floor(iCharac.getProficiencyBonus() /2)
                        : "")
                    + (iLevel >= 15? "\n- You have advantage on an attack roll against a creature if at least one of your allies is within 5 feet of the creature and the ally isn’t incapacitated." : "")
                    , "",(iLevel >= 18 ? 3 : 2), -1, false, (iLevel >= 11? Enumerations.ActionType.BONUS_ACTION : Enumerations.ActionType.ACTION)));
            powers.add(new Power("Heightened Senses","You have advantage on Perception checks that rely on hearing or smell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Stalker's Prowess","Speed increased by 10ft.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Improved unarmed strikes","While transformed, your unarmed strikes are considered magical for the purpose of overcoming resistance and immunities.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Iron Volition","Whenever you make a Wisdom saving throw to maintain control of your hybrid form, you do so with advantage..", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Blood Curse of the Howl","As an action, you howl at any number of creatures within 30 feet, chilling their blood and stunning them with fear. Any of the target creatures that can hear you must succeed on a Wisdom saving throw (DC equal to 8 + your proficiency bonus + your Wisdom modifier) or become frightened of you until the end of your next turn. If they fail their saving throw by 5 or more, they are stunned until the end of your next turn instead. A creature that succeeds on this saving throw is immune to this blood curse for the next 24 hours.\n" +
                    "\n" +
                    "Amplify. This curse can target any number of creatures within 60 feet of you.", "30/60ft", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.WIS), false, Enumerations.ActionType.ACTION));
        }
        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int level) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (level >= 7) {
            fettles.add(new Fettle(Enumerations.FettleType.MOVEMENT_SPEED_MODIFIER, 10, 0));
        }

        return  fettles;
    }
}
