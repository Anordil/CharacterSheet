package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Class  {

    String getClassName();
    String getQualifiedClassName();
    int getHitDie();

    boolean isCaster();
    int[] getSpellSlots(int iCharacterLevel);
    int[] getSpellsKnown(int iCharacterLevel);
    int getAttacksPerRound(Character iCharacter, int classLevel);

    List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context);
    LinkedList<Power> getPowers(int iLevel, Character iCharac);

    Enumerations.Attributes getMainSpellAttribute();

    int getIconResource();

    LinkedList<Fettle> getFettles(Character character, int classLevel);

    int getAC(Character character);

    void addArchetype(Archetype iArchetype, int iNewCharacterLevel, Context context);
    Archetype getArchetypeByName(String iName);

    Enumerations.SavingThrows[] getSavingThrowsProficiencies();

    int getChoosableArchetypes(int iNewLevel);
    Archetype getArchetype(int index);

    List<String> getAllLevelUpBenefits(int iNewCharacterLevel, Context context);
    LinkedList<Power> getAllPowers(int iLevel, Character iCharac);
    LinkedList<Fettle> getAllFettles(Character character, int classLevel);

    List<Attack> getSpecialClassAttacks(Character iCharacter, int classLevel);
    List<Attack> getAllSpecialClassAttacks(Character iCharacter, int classLevel);

    void doLevelUp(int oldLevel, int newLevel, Context context, Runnable showBenefits);
    void doLevelDown(int oldLevel, int newLevel);

    // Features to choose from at level up
    List<Power> getAllClassFeatures(int iClassLevel);
    // Do you get to change a feature at this level
    boolean canReplaceFeature(int iClasseLevel);
    void replaceFeature(Context context, int classLevel);
    void selectClassFeature(Context context, int classLevel, int current, int max);
    int gainedClassFeatures(int classLevel);
    int nbOfFeatures(int level);

    void selectSkills(final Context context, final Character iCharacter);
    String[] getClassSkills();
    int getClassSkillCount();

}
