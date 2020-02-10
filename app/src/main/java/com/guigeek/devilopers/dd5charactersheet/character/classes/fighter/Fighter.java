package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

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
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_dreams;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_land;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_moon;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_shepherd;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_spores;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_wildfire;
import com.guigeek.devilopers.dd5charactersheet.character.classes.ranger.Ranger_hunter;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Fighter extends BaseClass {
    static final long serialVersionUID = 2600L;
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
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.fighterArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.fighter_archer))) {
            return new Fighter_archer();
        } else if (iName.equals(App.getResString(R.string.fighter_battlemaster))) {
            return new Fighter_battlemaster();
        } else if (iName.equals(App.getResString(R.string.fighter_cavalier))) {
            return new Fighter_cavalier();
        } else if (iName.equals(App.getResString(R.string.fighter_champion))) {
            return new Fighter_champion();
        } else if (iName.equals(App.getResString(R.string.fighter_eldritch))) {
            return new Fighter_eldritch();
        } else if (iName.equals(App.getResString(R.string.fighter_psychic))) {
            return new Fighter_psychic();
        } else if (iName.equals(App.getResString(R.string.fighter_purple))) {
            return new Fighter_purple();
        } else if (iName.equals(App.getResString(R.string.fighter_rune))) {
            return new Fighter_rune();
        } else if (iName.equals(App.getResString(R.string.fighter_samurai))) {
            return new Fighter_samurai();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.STR,
                Enumerations.SavingThrows.CON
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }



    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_fighter);
        return name;
    }

    public static int getActionSurgeUses(int iLevel) {
        return iLevel >= 17 ? 2 : iLevel >= 2 ? 1 : 0;
    }

    public static int getIndomitableUses(int iLevel) {
        return iLevel >= 17 ? 3 : iLevel >= 13 ? 2 : iLevel >= 9 ? 1 : 0;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        int level = iCharacter._level;
        return level >= 20 ? 4 : level >= 11 ? 3 : level >= 5 ? 2 : 1;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        super.doLevelDown(oldLevel, newLevel);

        if (!_archetypes.isEmpty() && _archetypes.get(0) instanceof Fighter_archer) {
            ((Fighter_archer)_archetypes.get(0)).doLevelDown(newLevel);
        }
        if (!_archetypes.isEmpty() && _archetypes.get(0) instanceof Fighter_battlemaster) {
            ((Fighter_battlemaster)_archetypes.get(0)).doLevelDown(newLevel);
        }
        if (!_archetypes.isEmpty() && _archetypes.get(0) instanceof Fighter_rune) {
            ((Fighter_rune)_archetypes.get(0)).doLevelDown(newLevel);
        }
    }

    @Override
    public boolean isCaster() {
        if (!_archetypes.isEmpty() && _archetypes.get(0) instanceof Fighter_eldritch) {
            return true;
        }
        return false;
    }

    int[][] _spellSlotsOverride = {
            // spell level 0-4 for Elditch Knight
            {0, 0, 0, 0, 0, 0,0,0,0,0},
            {0, 0, 0, 0, 0, 0,0,0,0,0}, //character lv 1
            {0, 0, 0, 0, 0, 0,0,0,0,0},
            {0, 2, 0, 0, 0, 0,0,0,0,0},// lv 3
            {0, 3, 0, 0, 0, 0,0,0,0,0},
            {0, 3, 0, 0, 0, 0,0,0,0,0},//lv 5
            {0, 3, 0, 0, 0, 0,0,0,0,0},
            {0, 4, 2, 0, 0, 0,0,0,0,0},
            {0, 4, 2, 0, 0, 0,0,0,0,0},
            {0, 4, 2, 0, 0, 0,0,0,0,0},
            {0, 4, 3, 0, 0, 0,0,0,0,0},//lv 10
            {0, 4, 3, 0, 0, 0,0,0,0,0},
            {0, 4, 3, 0, 0, 0,0,0,0,0},
            {0, 4, 3, 2, 0, 0,0,0,0,0},
            {0, 4, 3, 2, 0, 0,0,0,0,0},
            {0, 4, 3, 2, 0, 0,0,0,0,0},//lv 15
            {0, 4, 3, 3, 0, 0,0,0,0,0},
            {0, 4, 3, 3, 0, 0,0,0,0,0},
            {0, 4, 3, 3, 0, 0,0,0,0,0},
            {0, 4, 3, 3, 1, 0,0,0,0,0},
            {0, 4, 3, 3, 1, 0,0,0,0,0}//ln 20
    };

    public Fighter() {
        _spellSlots = _spellSlotsOverride;
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Fighter level " + iNewCharacterLevel + " benefits:");

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Second Wind");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Action Surge");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained another Action Surge");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Indomitable");
        }
        if (iNewCharacterLevel == 17 || iNewCharacterLevel == 13) {
            levelUp.add("Gained another use of Indomitable");
        }

        if (iNewCharacterLevel == 5 || iNewCharacterLevel == 11 || iNewCharacterLevel == 11) {
            levelUp.add("Gained an Extra Attack!");
        }

        // Fighting style
        if (iNewCharacterLevel == 1) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a fighting style");

            LinkedList<Power> allStyles = new LinkedList<Power>();
            String[] styleNames = context.getResources().getStringArray(R.array.fighterStyleNames);
            String[] styleDesc = context.getResources().getStringArray(R.array.fighterStyleDesc);

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

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Second Wind", "You have a limited well of stamina that you can draw on to protect yourself from harm. On your turn, you can use a bonus action to regain hit points equal to 1d10 + your fighter level.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 2) {
            powers.add(new Power("Action Surge", "You can push yourself beyond your normal limits for a moment. On your turn, you can take one additional action.", "", getActionSurgeUses(iLevel), -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Indomitable", "You can reroll a saving throw that you fail. If you do so, you must use the new roll, and you can’t use this feature again until you finish a long rest.", "", getIndomitableUses(iLevel), -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (_fightingStyle != null) {
            powers.add(_fightingStyle);
        }
        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_warrior;
    }
}
