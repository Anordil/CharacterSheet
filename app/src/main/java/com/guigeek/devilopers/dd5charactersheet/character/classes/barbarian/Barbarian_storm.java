package com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.StringListAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Barbarian_storm extends BaseArchetype {
    static final long serialVersionUID = 2103L;
    public Barbarian_storm(){}

    @Override
    public String getName() {
        String name = App.getResString(R.string.barbarian_storm);

        if (_chosenStringFeature != null) {
            name += " - " + _chosenStringFeature;
        }

        return name;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> perks = new LinkedList<>();
        if (classLevel >= 6) {
            if (_chosenStringFeature.equals("Desert")) {
                perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.FIRE.ordinal()));
            }
            else if (_chosenStringFeature.equals("Sea")) {
                perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.LIGHTNING.ordinal()));
            }
            else { //Tundra
                perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.COLD.ordinal()));
            }
        }

        return perks;
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        return -1;
    }

    protected void selectAuraType(Context context) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Select a Storm Aura type");

        final String[] allOptions = context.getResources().getStringArray(R.array.barbarianStormAuras);
        final List<String> allOptionsList = Arrays.asList(allOptions);

        b.setAdapter(new StringListAdapter(context, R.layout.list_string, allOptionsList), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String selectedOption = allOptions[which];
                setArchetypeStringFeature(selectedOption);
            }
        });

        b.show();
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, final Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Storm Aura. You can change its type whenever you gain a level.");
            selectAuraType(context);
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Storm Soul");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Shielding Storm");
        }
        else if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Raging Storm");
        }

        if (iNewCharacterLevel > 3) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            dialog.dismiss();
                            selectAuraType(context);

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked -> nothing to do
                            dialog.dismiss();
                    }
                }
            };

            AlertDialog.Builder yesNoDialog = new AlertDialog.Builder(context);
            yesNoDialog.setMessage("Do you want to change the nature of your Storm Aura?")
                    .setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Storm Aura", "you emanate a stormy, magical aura while you rage. The aura extends 10 feet from you in every direction, but not through total cover.\n" +
                    "\n" +
                    "Your aura has an effect that activates when you enter your rage, and you can activate the effect again on each of your turns as a bonus action.\n" +
                    "\n" +
                    "If your aura’s effects require a saving throw, the DC equals 8 + your proficiency bonus + your Constitution modifier." + "\n" +
                    getAuraDescription(iLevel), "", -1, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.CON), true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Storm Soul", getStormSoulDescription(iLevel), "10 ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Shielding Storm", "Each creature of your choice has the damage resistance you gained from the Storm Soul feature while the creature is in your Storm Aura.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Raging Storm", getRagingStormDescription(), "", -1, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.CON), true, _chosenStringFeature.equals("Tundra") ? Enumerations.ActionType.PASSIVE : Enumerations.ActionType.REACTION));
        }

        return powers;
    }

    private String getRagingStormDescription() {
        if (_chosenStringFeature.equals("Desert")) {
            return "Immediately after a creature in your aura hits you with an attack, you can use your reaction to force that creature to make a Dexterity saving throw. On a failed save, the creature takes fire damage equal to half your barbarian level.";
        }
        else if (_chosenStringFeature.equals("Sea")) {
            return "When you hit a creature in your aura with an attack, you can use your reaction to force that creature to make a Strength saving throw. On a failed save, the creature is knocked prone, as if struck by a wave.";
        }
        else { //Tundra
            return "Whenever the effect of your Storm Aura is activated, you can choose one creature you can see in the aura. That creature must succeed on a Strength saving throw, or its speed is reduced to 0 until the start of your next turn, as magical frost covers it.";
        }
    }

    private String getStormSoulDescription(int iLevel) {
        if (_chosenStringFeature.equals("Desert")) {
            return "You gain resistance to fire damage, and you don’t suffer the effects of extreme heat, as described in the Dungeon Master’s Guide. Moreover, as an action, you can touch a flammable object that isn’t being worn or carried by anyone else and set it on fire.";
        }
        else if (_chosenStringFeature.equals("Sea")) {
            return "You gain resistance to lightning damage, and you can breathe underwater. You also gain a swimming speed of 30 feet.";
        }
        else { //Tundra
            return "You gain resistance to cold damage, and you don’t suffer the effects of extreme cold, as described in the Dungeon Master’s Guide. Moreover, as an action, you can touch water and turn a 5-foot cube of it into ice, which melts after 1 minute. This action fails if a creature is in the cube.";
        }
    }

    private String getAuraDescription(int iLevel) {
        if (_chosenStringFeature.equals("Desert")) {
            return "All other creatures in your aura take " + (iLevel >= 20 ? 6 : iLevel >= 15 ? 5 : iLevel >= 10 ? 4 : iLevel >= 5 ? 3 : 2) + " fire damage.";
        }
        else if (_chosenStringFeature.equals("Sea")) {
            return "When this effect is activated, you can choose one other creature you can see in your aura. The target must make a Dexterity saving throw. The target takes "
                    + (iLevel >= 20 ? "4D6" : iLevel >= 15 ? "3D6" : iLevel >= 10 ? "2D6" : "1D6") + " lightning damage on a failed save, or half on a succesful save.";
        }
        else { //Tundra
            return "When this effect is activated, each creature of your choice in your aura gains "
                    +  (iLevel >= 20 ? 6 : iLevel >= 15 ? 5 : iLevel >= 10 ? 4 : iLevel >= 5 ? 3 : 2)
                    + " temporary hit points, as icy spirits inure it to suffering.";
        }
    }
}
