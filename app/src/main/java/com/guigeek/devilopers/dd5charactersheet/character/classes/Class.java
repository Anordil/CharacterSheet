package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Class  {

    public String getClassName();
    public String getQualifiedClassName();
    public int getHitDie();

    public boolean isCaster();
    public int[] getSpellSlots(int iCharacterLevel);
    public int[] getSpellsKnown(int iCharacterLevel);
    public int getAttacksPerRound(Character iCharacter);

    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context);
    public LinkedList<Power> getPowers(int iLevel, Character iCharac);

    public Enumerations.Attributes getMainSpellAttribute();

    public int getIconResource();

    public LinkedList<Fettle> getFettles(Character character);

    public int getAC(Character character);

    public void addArchetype(Archetype iArchetype, int iNewCharacterLevel, Context context);
    public Archetype getArchetypeByName(String iName);

    public Enumerations.SavingThrows[] getSavingThrowsProficiencies();

    public int getChoosableArchetypes(int iNewLevel);

    public List<String> getAllLevelUpBenefits(int iNewCharacterLevel, Context context);
    public LinkedList<Power> getAllPowers(int iLevel, Character iCharac);
    public LinkedList<Fettle> getAllFettles(Character character);

    public List<Attack> getSpecialClassAttacks(Character iCharacter);
    public List<Attack> getAllSpecialClassAttacks(Character iCharacter);

    public void doLevelUp(int oldLevel, int newLevel, Context context, Runnable showBenefits);
    public void doLevelDown(int oldLevel, int newLevel);
}