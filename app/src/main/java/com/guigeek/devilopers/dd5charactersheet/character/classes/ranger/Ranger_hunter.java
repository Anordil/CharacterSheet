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
import com.guigeek.devilopers.dd5charactersheet.character.SavingThrow;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public class Ranger_hunter extends BaseArchetype {
    static final long serialVersionUID = 2804L;
    protected LinkedList<Power> _chosenFeatures = new LinkedList<>();

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        super.writeExternal(objectOutput);
        objectOutput.writeObject(_chosenFeatures);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        super.readExternal(objectInput);
        _chosenFeatures = (LinkedList<Power>) objectInput.readObject();
    }

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_hunter);
    }

    private LinkedList<Power> getHunterFeatures(int iLevel) {
        LinkedList<Power> features = new LinkedList<>();
        if (iLevel == 3) {
            features.add(new Power("Colossus Slayer", "Your tenacity can wear down the most potent foes. When you hit a creature with a weapon attack, the creature takes an extra 1d8 damage if it’s below its hit point maximum. You can deal this extra damage only once per turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            features.add(new Power("Giant Killer", "When a Large or larger creature within 5 feet of you hits or misses you with an attack, you can use your reaction to attack that creature immediately after its attack, provided that you can see the creature.", "", -1, -1, true, Enumerations.ActionType.REACTION));
            features.add(new Power("Hoard Breaker", "Once on each of your turns when you make a weapon attack, you can make another attack with the same weapon against a different creature that is within 5 feet of the original target and within range of your weapon.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel == 7) {
            features.add(new Power("Escape the Horde", "Opportunity attacks against you are made with disadvantage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            features.add(new Power("Multiattack Defense", "When a creature hits you with an attack, you gain a +4 bonus to AC against all subsequent attacks made by that creature for the rest of the turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            features.add(new Power("Steel Will", "You have advantage on saving throws against being frightened.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel == 11) {
            features.add(new Power("Volley", "You can use your action to make a ranged attack against any number of creatures within 10 feet of a point you can see within your weapon’s range. You must have ammunition for each target, as normal, and you make a separate attack roll for each target.", "", -1, -1, true, Enumerations.ActionType.ACTION));
            features.add(new Power("Whirlwind Attack", "You can use your action to make a melee attack against any number of creatures within 5 feet of you, with a separate attack roll for each target.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel == 15) {
            features.add(new Power("Evasion", "When you are subjected to an effect, such as a red dragon’s fiery breath or a lightning bolt spell, that allows you to make a Dexterity saving throw to take only half damage, you instead take no damage if you succeed on the saving throw, and only half damage if you fail.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            features.add(new Power("Stand Against the Tide", "When a hostile creature misses you with a melee attack, you can use your reaction to force that creature to repeat the same attack against another creature (other than itself) of your choice.", "", -1, -1, true, Enumerations.ActionType.REACTION));
            features.add(new Power("Uncanny Dodge", "When an attacker that you can see hits you with an attack, you can use your reaction to halve the attack’s damage against you.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        return features;
    }

    public int nbOfFeatures(int iLevel) {
        return iLevel >= 15 ? 4 : iLevel >= 11 ? 3 : iLevel >= 7 ? 2 : iLevel >= 3 ? 1 : 0;
    }

    public void doLevelDown(int inewLevel) {
        while(_chosenFeatures.size() > nbOfFeatures(inewLevel)) {
            _chosenFeatures.removeLast();
        }
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Hunter’s Prey");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Defensive Tactics");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Multiattack");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Superior Hunter’s Defense");
        }


        LinkedList<Power> features = getHunterFeatures(iNewCharacterLevel);
        if (features != null && !features.isEmpty()) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a new Hunter Feature");

            final Object[] featsFiltered = features.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, features), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenFeatures.add(feat);
                }
            });

            b.show();
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        return _chosenFeatures;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (character.hasPower("Steel Will")) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.FEAR.ordinal()));
        }

        return fettles;
    }
}
