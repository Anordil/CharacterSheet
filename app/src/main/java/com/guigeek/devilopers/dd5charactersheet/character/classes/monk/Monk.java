package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian_ancestral;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian_berserker;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian_storm;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian_totem;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian_zealot;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by totou on 15/06/2016.
 */
public class Monk extends BaseClass {
    static final long serialVersionUID = 2700L;

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.DEX,
                Enumerations.SavingThrows.STR
        };
    }

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel >= 3 && _archetypes.size() == 0 ? R.array.monkArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.monk_astral))) {
            return new Monk_astral();
        } else if (iName.equals(App.getResString(R.string.monk_death))) {
            return new Monk_death();
        } else if (iName.equals(App.getResString(R.string.monk_drunken))) {
            return new Monk_drunken();
        } else if (iName.equals(App.getResString(R.string.monk_elements))) {
            return new Monk_elements();
        } else if (iName.equals(App.getResString(R.string.monk_hand))) {
            return new Monk_hand();
        } else if (iName.equals(App.getResString(R.string.monk_kensei))) {
            return new Monk_kensei();
        } else if (iName.equals(App.getResString(R.string.monk_mercy))) {
            return new Monk_mercy();
        } else if (iName.equals(App.getResString(R.string.monk_shadow))) {
            return new Monk_shadow();
        } else if (iName.equals(App.getResString(R.string.monk_sunsoul))) {
            return new Monk_sunsoul();
        }

        return null;
    }

    @Override
    public String getClassName() {
        return App.getResString(R.string.class_monk);
    }

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor.getAC(character);

        if (canUseMonkBenefits(character)) {
            ac = 10 + character.getModifier(Enumerations.Attributes.DEX) + character.getModifier(Enumerations.Attributes.WIS);
        }

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        return ac;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        int level = character._class instanceof Monk ? character._level : character._levelSecondaryClass;

        if (canUseMonkBenefits(character)) {
            if (level >= 2) {
                int movementSpeed = level >= 18 ? 30 : level >= 14? 25 : level >= 10 ? 20 : level >= 6 ? 15 : 10;
                fettles.add(new Fettle(Enumerations.FettleType.MOVEMENT_SPEED_MODIFIER, movementSpeed, 0));
            }
        }
        if (level >= 10) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.POISON.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.DISEASES.ordinal()));
        }

        return fettles;
    }


    public Monk(){
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        int level = iCharacter._class instanceof Monk ? iCharacter._level : iCharacter._levelSecondaryClass;
        return (level >= 5 ? 2 : 1);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Monk level " + iNewCharacterLevel + "!");

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Martial Arts");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("You can now use Ki");
            levelUp.add("You gained Unarmored movement");
        }
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Deflect Missiles");
            levelUp.add("Select a Monastery Tradition");
        }
        if (iNewCharacterLevel == 4) {
            levelUp.add("You gained Slow Fall");
        }
        if (iNewCharacterLevel == 5) {
            levelUp.add("You gained an Extra Attack");
            levelUp.add("You gained Stunning Strike (Ki)");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained an Ki-Empowered Strikes");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Evasion");
            levelUp.add("You gained Stillness of Mind");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Purity of Body");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("You gained Tongue of the Sun and Moon");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Diamond Soul");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Timeless Body");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Empty Body");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Perfect Self");
        }

        return levelUp;
    }

    public int getMonkDamageDie(int iLevel) {
        return iLevel >= 17? 10 : iLevel >= 11? 8 : iLevel >= 5 ? 6 : 4;
    }

    @Override
    public boolean isCaster() {
        boolean caster = false;
        for (Archetype arc: _archetypes) {
            if (arc instanceof Monk_elements) {
                caster = true;
            }
        }

        return caster;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.WIS;
    }

    public boolean canUseMonkBenefits(Character character) {
        // No armor nor shield
        return (character._equippedArmor == null || character._equippedArmor._type == Enumerations.ArmorTypes.NONE)
                && (character._equippedShield == null || character._equippedShield._type == Enumerations.ArmorTypes.NONE);
    }

    @Override
    public LinkedList<Power> getAllPowers(int iLevel, Character iCharac) {
        LinkedList<Power> allItems = getPowers(iLevel, iCharac);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getPowers(iLevel, iCharac));

                // Override for Monk because of the Disciplines for the Way of the Four elements
                if (arc instanceof Monk_elements) {
                    allItems.addAll(((Monk_elements)arc)._chosenDisciplines);
                }
            }
        }

        return allItems;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
        	powers.add(new Power("Martial Arts", "Your practice of martial arts gives you mastery of combat styles that use unarmed strikes and monk weapons, which are shortswords and any simple melee weapons that don’t have the two-handed or heavy property.\n" +
                    "\n" +
                    "You gain the following benefits while you are unarmed or wielding only monk weapons and you aren’t wearing armor or wielding a shield:\n" +
                    "\n" +
                    "You can use Dexterity instead of Strength for the attack and damage rolls of your unarmed strikes and monk weapons.\n" +
                    "You can roll a d" + getMonkDamageDie(iLevel) + " in place of the normal damage of your unarmed strike or monk weapon.\n" +
                    "When you use the Attack action with an unarmed strike or a monk weapon on your turn, you can make one unarmed strike as a bonus action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 2) {
            int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);

            String kiDescription = "You can spend Ki poins to do the following:\n" +
                    "- Flurry of Blows: Immediately after you take the Attack action on your turn, you can spend 1 ki point to make two unarmed strikes as a bonus action." +
                    "\n\n- Patient Defense: You can spend 1 ki point to take the Dodge action as a bonus action on your turn." +
                    "\n\n- Step of the Wind: You can spend 1 ki point to take the Disengage or Dash action as a bonus action on your turn, and your jump distance is doubled for the turn.";

            if (iLevel >= 5) {
                kiDescription += "\n\n- Stunning Strike: When you hit another creature with a melee weapon attack, you can spend 1 ki point to attempt a stunning strike. The target must succeed on a Constitution saving throw or be stunned until the end of your next turn.";
            }
            if (iLevel >= 14) {
                kiDescription += "\n\n- Diamond Soul: You can spend 1 ki to reroll a failed saving throw and take the second result.";
            }
            if (iLevel >= 15) {
                kiDescription += "\n\n- Empty Body: You can use your action to spend 4 ki and become invisible for 1mn. Yu also get resistance to all damage but force during that time.";
                kiDescription += "\n\n- Empty Body II: You can spend 8 ki to cast astral projection without needing the material components. You cannot take any other creature with you..";
            }

            powers.add(new Power("Ki", kiDescription, "", iLevel, dc, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Unarmored movement", "If you're not wearing armor or using a shield, you get a bonus to your movement speed."
                    + (iLevel >= 9 ? "\nYou gain the ability to move along vertical surfaces and across liquids on your turn without falling during the move." : ""), "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 3) {
            int dmgRed = iLevel + iCharac.getModifier(Enumerations.Attributes.DEX);

            powers.add(new Power("Deflect Missile", "You can use your reaction to catch or deflect the missile when hit by a ranged attack. When you do so, the damage you take from the attack is reduced by 1d10 + " + dmgRed + "." +
                    "\n\nIf you reduce the damage to 0, you can catch the missile if it is small enough for you to hold in one hand and you have at least one hand free. If you catch a missile in this way, you can spend 1 ki point to make a ranged attack with the weapon or piece of ammunition you just caught, as part of the same reaction. You make this attack with proficiency, regardless of your weapon proficiencies, and the missile counts as a monk weapon for the attack, which has a normal range of 20 feet and a long range of 60 feet.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 4) {
            powers.add(new Power("Slow Fall", "You can use your reaction to reduce fall damage by " + (5*iLevel), "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Ki-Empowered Strikes", "Your unarmed strikes count as magical to overcome resistance and immunities.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Evasion", "When you are subjected to an effect that allows you to make a Dexterity saving throw to take only half damage, you instead take no damage if you succeed on the saving throw, and only half damage if you fail.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Stillness of Mind", "You can use your action to end one effect on yourself that is causing you to be charmed or frightened.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Purity of Body", "You're immune to poisons and diseases.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Tongue of the Sun and Moon", "You learn to touch the ki of other minds so that you understand all spoken languages. Moreover, any creature that can understand a language can understand what you say.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Diamond Soul", "Your mastery of ki grants you proficiency in all saving throws.\n" +
                    "\n" +
                    "Additionally, whenever you make a saving throw and fail, you can spend 1 ki point to reroll it and take the second result.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Timeless Body", "Your ki sustains you so that you suffer none of the frailty of old age, and you can’t be aged magically. You can still die of old age, however. In addition, you no longer need food or water.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Empty Body", "You can use your action to spend 4 ki points to become invisible for 1 minute. During that time, you also have resistance to all damage but force damage.\n" +
                    "\n" +
                    "Additionally, you can spend 8 ki points to cast the astral projection spell, without needing material components. When you do so, you can’t take any other creatures with you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Perfect Self", "When you roll for initiative and have no ki points remaining, you regain 4 ki points.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_barbarian;
    }
}
