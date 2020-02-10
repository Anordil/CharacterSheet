package com.guigeek.devilopers.dd5charactersheet.character.classes.ranger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Ranger extends BaseClass {
    static final long serialVersionUID = 2800L;
    protected Power _fightingStyle;

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
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.rangerArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.ranger_beast))) {
            return new Ranger_beast();
        } else if (iName.equals(App.getResString(R.string.ranger_gloom))) {
            return new Ranger_gloom();
        } else if (iName.equals(App.getResString(R.string.ranger_slayer))) {
            return new Ranger_slayer();
        } else if (iName.equals(App.getResString(R.string.ranger_horizon))) {
            return new Ranger_horizon();
        } else if (iName.equals(App.getResString(R.string.ranger_hunter))) {
            return new Ranger_hunter();
        } else if (iName.equals(App.getResString(R.string.ranger_swarm))) {
            return new Ranger_swarm();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.STR,
                Enumerations.SavingThrows.DEX
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        super.doLevelDown(oldLevel, newLevel);

        if (!_archetypes.isEmpty() && _archetypes.get(0) instanceof Ranger_hunter) {
            ((Ranger_hunter)_archetypes.get(0)).doLevelDown(newLevel);
        }
    }

    @Override
    public boolean isCaster() {
        return  true;
    }

    int[][] _spellSlotsOverride = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},//lv 10
            {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},//lv 15
            {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 0, 0, 0, 0}//ln 20
    };


    public Ranger(){
        _spellSlots = _spellSlotsOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.WIS;
    }

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        return iCharacter._level >= 5 ? 2 : 1;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_ranger);
        return name;
    }

    @Override
    public int getAC(Character character) {
        int ac = super.getAC(character);

        if (character.hasPower("[Fighting Style] Defense") && character._equippedArmor != null && character._equippedArmor._type != Enumerations.ArmorTypes.NONE) {
            ac += 1;
        }

        return ac;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Ranger level " + iNewCharacterLevel + " benefits:");

        // Fighting style
        if (iNewCharacterLevel == 2) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a fighting style");

            LinkedList<Power> allStyles = new LinkedList<Power>();
            String[] styleNames = context.getResources().getStringArray(R.array.rangerStyleNames);
            String[] styleDesc = context.getResources().getStringArray(R.array.rangerStyleDesc);

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

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Favored Enemy");
            levelUp.add("Gained Natural Explorer");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained a 2nd Favored Enemy");
            levelUp.add("Gained a 2nd terrain for Natural Explorer");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained a 3rd terrain for Natural Explorer");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained a 3rd Favored Enemy");
        }
        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Primeval Awareness");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Land’s Stride");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Hide in Plain Sight");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Vanish");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Feral Senses");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Gained Foe Slayer");
        }


        return levelUp;
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Favored enemy", "Choose a type of favored enemy: aberrations, beasts, celestials, constructs, dragons, elementals, fey, fiends, giants, monstrosities, oozes, plants, or undead. Alternatively, you can select two races of humanoid (such as gnolls and orcs) as favored enemies.\n" +
                    "\n" +
                    "You have advantage on Wisdom (Survival) checks to track your favored enemies, as well as on Intelligence checks to recall information about them.\n" +
                    "\n" +
                    "When you gain this feature, you also learn one language of your choice that is spoken by your favored enemies, if they speak one at all.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Natural Explorer", "Choose one type of favored terrain: arctic, coast, desert, forest, grassland, mountain, swamp, or the Underdark. When you make an Intelligence or Wisdom check related to your favored terrain, your proficiency bonus is doubled if you are using a skill that you’re proficient in.\n" +
                    "\n" +
                    "While traveling for an hour or more in your favored terrain, you gain the following benefits:\n" +
                    "\n" +
                    "Difficult terrain doesn’t slow your group’s travel.\n" +
                    "Your group can’t become lost except by magical means.\n" +
                    "Even when you are engaged in another activity while traveling (such as foraging, navigating, or tracking), you remain alert to danger.\n" +
                    "If you are traveling alone, you can move stealthily at a normal pace.\n" +
                    "When you forage, you find twice as much food as you normally would.\n" +
                    "While tracking other creatures, you also learn their exact number, their sizes, and how long ago they passed through the area.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 3) {
            powers.add(new Power("Primeval Awareness", "You can use your action and expend one ranger spell slot to focus your awareness on the region around you. For 1 minute per level of the spell slot you expend, you can sense whether the following types of creatures are present within 1 mile of you (or within up to 6 miles if you are in your favored terrain): aberrations, celestials, dragons, elementals, fey, fiends, and undead. This feature doesn’t reveal the creatures’ location or number.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Land’s Stride", "Moving through nonmagical difficult terrain costs you no extra movement. You can also pass through nonmagical plants without being slowed by them and without taking damage from them if they have thorns, spines, or a similar hazard.\n" +
                    "\n" +
                    "In addition, you have advantage on saving throws against plants that are magically created or manipulated to impede movement, such those created by the entangle spell.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Hide in Plain Sight", "You can spend 1 minute creating camouflage for yourself. You must have access to fresh mud, dirt, plants, soot, and other naturally occurring materials with which to create your camouflage.\n" +
                    "\n" +
                    "Once you are camouflaged in this way, you can try to hide by pressing yourself up against a solid surface, such as a tree or wall, that is at least as tall and wide as you are. You gain a +10 bonus to Dexterity (Stealth) checks as long as you remain there without moving or taking actions. Once you move or take an action or a reaction, you must camouflage yourself again to gain this benefit.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Vanish", "You can use the Hide action as a bonus action on your turn. Also, you can’t be tracked by nonmagical means, unless you choose to leave a trail.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Feral Senses", "When you attack a creature you can’t see, your inability to see it doesn’t impose disadvantage on your attack rolls against it.\n" +
                    "\n" +
                    "You are also aware of the location of any invisible creature within 30 feet of you, provided that the creature isn’t hidden from you and you aren’t blinded or deafened.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Foe Slayer", "Once on each of your turns, you can add your Wisdom modifier to the attack roll or the damage roll of an attack you make against one of your favored enemies. You can choose to use this feature before or after the roll, but before any effects of the roll are applied.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (_fightingStyle != null) {
            powers.add(_fightingStyle);
        }
        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_ranger;
    }
}
