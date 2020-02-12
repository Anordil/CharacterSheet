package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Archetype {

    String getName();

    List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context);
    LinkedList<Power> getPowers(int iLevel, Character iCharac);
    LinkedList<Fettle> getFettles(Character character, int classLevel);
    List<Attack> getSpecialClassAttacks(Character iCharacter, int classLevel);

    void setArchetypeStringFeature(String iFeature);


    // This one is for simple "String" features (like type of aura, totem choice...)
    // They do not change except for Barbarian_storm (so it's implemented there only)
    int getChoosableFeature(int iLevel);

    // This one is for Power-like chosable features
    List<Power> getAllArchetypeFeatures(int iClassLevel);
    // Some power Features can be replaced at certain levels
    boolean canReplaceFeature(int iClasseLevel);
    // Do the replacement
    void replaceFeature(Context context, int classLevel);
    void selectArchetypeFeature(Context context, int classLevel, int current, int max);
    int gainedArchetypeFeatures(int classLevel);
    int nbOfFeatures(int level);

    void doLevelDown(int oldLevel, int newLevel);
}
