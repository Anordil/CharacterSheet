package com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.monk.Monk_elements;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock_pact_blade;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock_pact_tome;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public class BloodHunter extends BaseClass {
    static final long serialVersionUID = 203L;
    protected Power _fightingStyle;

    protected Power[] bloodCurses = new Power[] {
        new Power("Blood Curse of Binding", "As a bonus action, you can attempt to bind an enemy no more than one size larger than you within 30 feet. The target must succeed on a Strength saving throw (DC equal to 8 + your proficiency bonus + your Wisdom modifier) or have their speed be reduced to 0 until the end of your next turn.\n" +
                "\n" +
                "Amplify. This curse becomes ongoing, and can affect a creature regardless of their size category. At the end of each of its turns, the cursed can make another Strength saving throw. On a success, this curse ends. You can end the curse whenever you like (no action required).", "", -1, -1, false, Enumerations.ActionType.BONUS_ACTION),
        new Power("Blood Curse of Mutual Suffering", "As a bonus action, you can link to a creature within 30 feet for up to a minute, forcing them to share in the pain they inflict upon you. The next time the cursed creature damages you with a weapon attack, this curse deals necrotic damage to the cursed creature equal to half of the damage you suffered. This curse then ends.\n" +
                "\n" +
                "Amplify. This curse instead deals damage equal to the damage you suffered, and it ignores necrotic resistance.", "", -1, -1, false, Enumerations.ActionType.BONUS_ACTION),
        new Power("Blood Curse of Purgation", "As a bonus action, you can manipulate the vitality of a creature within 60 feet to expunge a corruption in their blood. The target creature can immediately make a saving throw against a poisoned condition afflicting it.\n" +
                "\n" +
                "Amplify. Your target can instead immediately make a saving throw against one other condition afflicting it. This condition can be blinded, deafened, or paralyzed.", "", -1, -1, false, Enumerations.ActionType.BONUS_ACTION),
        new Power("Blood Curse of Spell Sunder", "When an enemy casts a spell within 60 feet that requires a spell attack roll and targets you, you can use your reaction to rend the spell from the air, imposing disadvantage on the spell attack roll.\n" +
                "\n" +
                "Amplify. You make a Wisdom ability check. The DC equals 10 + the spell’s level. On a success, the creature’s spell misses you automatically.", "60ft", -1, -1, false, Enumerations.ActionType.REACTION),
        new Power("Blood Curse of the Eyeless", "When an enemy who is not immune to blindness within 60 feet makes a weapon attack, you can use your reaction to impose disadvantage on the attack roll.\n" +
                "\n" +
                "Amplify. Following the triggering attack, the affected enemy has disadvantage on the next attack roll they make.", "60ft", -1, -1, false, Enumerations.ActionType.REACTION),
        new Power("Blood Curse of the Fallen Puppet", "The moment a creature falls unconscious or dies within 30 feet of you, you can use your reaction to give that creature a final act of aggression. That creature immediately makes a single weapon attack against a target of your choice within its attack range. After the attack, the creature returns to being unconscious or dead.\n" +
                "\n" +
                "Amplify. You grant a bonus to the attack roll and damage roll of the cursed creature’s attack equal to your Wisdom modifier (minimum of 1).", "30ft", -1, -1, false, Enumerations.ActionType.REACTION),
        new Power("Blood Curse of the Fending Rite", "When an enemy casts a spell that requires a Dexterity saving throw, you can use your reaction to deflect the spell with your crimson rite. You gain a bonus to the initial saving throw against that spell equal to your Wisdom modifier (minimum of 1). This curse is invoked before the saving throw is rolled.\n" +
                "\n" +
                "Amplify. You grant all allies within 5 feet of you this bonus to their saving throw against the triggering spell as well.", "", -1, -1, false, Enumerations.ActionType.REACTION),
        new Power("Blood Curse of the Marked", "As a bonus action, you can mark an enemy within 60 feet. Until the end of your turn, all crimson rite damage you deal to the target is doubled.\n" +
                "\n" +
                "Amplify. You cause the marked target to also lose resistance to your rite damage type until the beginning of your next turn.", "", -1, -1, false, Enumerations.ActionType.BONUS_ACTION)
    };

    protected Power[] primalRites = new Power[] {
            new Power("Rite of the Flame", "Your rite damage type is fire", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Rite of the Frozen", "Your rite damage type is cold", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Rite of the Storm", "Your rite damage type is lightning", "", -1, -1, false, Enumerations.ActionType.PASSIVE)
    };

    protected Power[] esotericRites = new Power[] {
            new Power("Rite of the Roar", "Your rite damage type is thunder", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Rite of the Oracle", "Your rite damage type is psychic", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Rite of the dead", "Your rite damage type is necrotic", "", -1, -1, false, Enumerations.ActionType.PASSIVE)
    };

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeObject(_fightingStyle);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        _fightingStyle = (Power)oi.readObject();
    }

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.bloodHunterOrders : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.bloodhunter_lycan))) {
            return new BloodHunter_lycan();
        } else if (iName.equals(App.getResString(R.string.bloodhunter_mutant))) {
            return new BloodHunter_mutant();
        } else if (iName.equals(App.getResString(R.string.bloodhunter_ghostslayer))) {
            return new BloodHunter_ghostslayer();
        } else if (iName.equals(App.getResString(R.string.bloodhunter_profane_soul))) {
            return new BloodHunter_profane();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.STR
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        // Hardened Soul
        if (classLevel >= 14) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FEAR.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.CHARM_MAGIC.ordinal()));
        }

        return fettles;
    }

    int[][] _spellsKnown = {
            // N/A, blood curses
            {0, 0},
            {0, 0}, //character lv 1
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 2},//lv 5
            {0, 2},
            {0, 2},
            {0, 2},
            {0, 3},
            {0, 3},//lv 10
            {0, 3},
            {0, 3},
            {0, 4},
            {0, 4},
            {0, 4},//lv 15
            {0, 4},
            {0, 5},
            {0, 5},
            {0, 5},
            {0, 6}//ln 20
    };



    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        return (classLevel >= 5 ? 2 : 1);
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_blood_hunter);
        return name;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        super.doLevelDown(oldLevel, newLevel);
        if (newLevel < 2) {
            _fightingStyle = null;
        }
    }

    @Override
    public int gainedClassFeatures(int classLevel) {
        // Blood Curses
        if (classLevel == 2 || classLevel == 5 || classLevel == 9 || classLevel == 13|| classLevel == 16 || classLevel == 20) {
            return 1;
        } else if (classLevel == 1 || classLevel == 6 || classLevel == 11 || classLevel == 14) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Power> getAllClassFeatures(int classLevel) {
        if (classLevel == 2 || classLevel == 5 || classLevel == 9 || classLevel == 13|| classLevel == 16 || classLevel == 20) {
            return Arrays.asList(bloodCurses);
        } else if (classLevel == 1 || classLevel == 6 || classLevel == 11) {
            return Arrays.asList(primalRites);
        } else if (classLevel == 14) {
            return Arrays.asList(esotericRites);
        }

        return null;
    }

    @Override
    public int nbOfFeatures(int iLevel) {
        // Blood curses at 2 5 9 13 16 20
        int total = iLevel >= 20 ? 6 : iLevel >= 16 ? 5 : iLevel >= 13 ? 4 : iLevel >= 9 ? 3 : iLevel >= 5 ? 2 : iLevel >= 2 ? 1 : 0;
        // Rites at 1 6 11 14
        total += iLevel >= 14 ? 4 : iLevel >= 11 ? 3 : iLevel >= 6 ? 2 : 1;

        return total;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Blood Hunter level " + iNewCharacterLevel + " benefits:");

        // Base-Blood Hunter powers
        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Hunter's Bane.");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("You gained Blood Maledict.");

            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a fighting style");

            LinkedList<Power> allStyles = new LinkedList<Power>();
            String[] styleNames = context.getResources().getStringArray(R.array.bloodHunterStyleNames);
            String[] styleDesc = context.getResources().getStringArray(R.array.bloodHunterStyleDesc);

            for (int i = 0; i < styleNames.length; ++i) {
                allStyles.add(new Power(styleNames[i], styleDesc[i], "Self", -1,-1, false, Enumerations.ActionType.PASSIVE));
            }

            final Object[] featsFiltered = allStyles.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, allStyles), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _fightingStyle = new Power(feat._name, feat._description, "", -1,-1, false, Enumerations.ActionType.PASSIVE);
                }
            });

            b.show();
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Blood Maledict is now 2x/rest.");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("You gained Grim Psychometry.");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Dark Velocity.");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Your Hunter's Bane improved.");
            levelUp.add("Blood Maledict is now 3x/rest.");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Hardened Soul.");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Blood Maledict is now 4x/rest.");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Sanguine Mastery.");
        }

        return levelUp;
    }

    private int getCrimsonRiteDie(int iLevel) {
        if (iLevel >= 17) return 10;
        else if (iLevel >= 11) return 8;
        else if (iLevel >= 5) return 6;
        else return 4;
    }

    private int getBloodMaledictUses(int iLevel) {
        if (iLevel >= 17) return 4;
        else if (iLevel >= 11) return 3;
        else if (iLevel >= 6) return 2;
        else return 1;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 11) {
            powers.add(new Power("Hunter's Bane 2", "You may suffer " + getCrimsonRiteDie(iLevel) + " damages to gain advantage on an Insight or Intimidation check.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        powers.add(new Power("Hunter's Bane", "You have advantage on Survival checks to track Fey, Fiends or Undead, as well as Intelligence checks to recall information about them. You cannot be surprised while actively tracking one of these creature types, and can only be tracking one type at a time.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));


        // Crimson Rites
        powers.add(new Power("Crimson Rite", "Suffer " + iLevel + " damages and lower your max HP by " + iLevel
                + " to imbue a weapon so it deals an extra 1D" + getCrimsonRiteDie(iLevel) + " damage of the type of a Rite you know. The Rite fades if you let go of the weapon. You may imbue several weapons, suffering the HP loss for each. When the rite fades, your max HP is restored to its previous level but you do not regain HP. Crimson rite can be used on multiple weapons, costing additional hit point loss. Most weapons can only be subject to a single rite at any given time. Each end of a polearm or quarterstaff is treated as a separate weapon for the purposes of this feature. A rite can be allowed to fade at any time (no action required).", "Self", -1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        powers.addAll(getCrimsonRites());

        if (iLevel >= 2) {
            powers.add(new Power("Blood Maledict", "You know " + _spellsKnown[iLevel][1] + " blood curses.\n" +
                    "When you use your Blood Maledict, you choose which curse to invoke.\n" +
                    "While invoking a blood curse, but before it affects the target, you may choose to amplify the curse by suffering 1D" + getCrimsonRiteDie(iLevel)
                    + ". An amplified curse gains an additional effect, noted in the curse’s description. Creatures that do not have blood in their bodies are immune to blood curses (DM’s discretion).", "", getBloodMaledictUses(iLevel), -1, false, Enumerations.ActionType.PASSIVE));
            powers.addAll(getBloodCurses());
        }

        if (iLevel >= 9) {
            powers.add(new Power("Grim Psychometry", "You can take 10 minutes to meditate on an object to discern vague details regarding any lingering evil or wicked past surrounding it. Make a Wisdom ability check. Based on the result, the DM may reveal obscure information about dark events that may have previously surrounded the object, or hints toward a sinister purpose. This feature has no effect on objects untouched by evil. An object can only be targeted by this feature once, and future attempts reveal no further details. You cannot use this feature again until you finish a short or long rest.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Dark Velocity", "You gain darkvision out to 30 feet, or if you have darkvision, extend it out an additional 30 feet. While in dim light or darkness, your speed increases by 10 feet, and attacks of opportunity made against you have disadvantage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Hardened Soul", "You can no longer become frightened, and you have advantage on saving throws against magical Charm effects.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Sanguine Mastery", "When you are below one fourth of your current maximum hit points, all of your crimson rite damage dice are maximized.\n" +
                    "\n" +
                    "In addition, when you critically hit with a weapon attack that bears your crimson rite, you regain a use of your Blood Maledict feature.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (_fightingStyle != null) {
            powers.add(_fightingStyle);
        }

        return powers;
    }

    private List<Power> getCrimsonRites() {
        LinkedList<Power> rites = new LinkedList<>();

        for (Power p : _chosenFeatures) {
            if (p._name.startsWith("Rite")) {
                rites.add(p);
            }
        }

        return rites;
    }

    private List<Power> getBloodCurses() {
        LinkedList<Power> curses = new LinkedList<>();

        for (Power p : _chosenFeatures) {
            if (p._name.startsWith("Blood Curse")) {
                curses.add(p);
            }
        }

        return curses;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_werewolf;
    }


    @Override
    public String[] getClassSkills() {
        return new String[] {
                Enumerations.Skills.ACROBATICS.toString(),
                Enumerations.Skills.ATHLETICS.toString(),
                Enumerations.Skills.ARCANA.toString(),
                Enumerations.Skills.HISTORY.toString(),
                Enumerations.Skills.INSIGHT.toString(),
                Enumerations.Skills.INVESTIHATION.toString(),
                Enumerations.Skills.RELIGION.toString(),
                Enumerations.Skills.SURVIVAL.toString(),
        };
    }

    @Override
    public int getClassSkillCount() {
        return 3;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.ARMOR_LIGHT);
        proficiencies.add(Enumerations.Proficiencies.ARMOR_MEDIUM);
        proficiencies.add(Enumerations.Proficiencies.SHIELD);

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.WEAPON_SIMPLE);
        proficiencies.add(Enumerations.Proficiencies.WEAPON_MARTIAL);

        return proficiencies;
    }
}
