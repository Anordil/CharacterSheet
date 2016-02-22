package com.guigeek.devilopers.dd5charactersheet.character;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Enumerations {

    public enum FettleType {
        ATTRIBUTE,
        ABILITY,
        ARMOR_CLASS,
        MOVEMENT_SPEED,
        ATTACK_BONUS,
        ATTACK_DAMAGE,
        DAMAGE_RESISTANCE,
        DAMAGE_VULNERABILITY,
        SAVING_THROW
    }


    public enum Attributes {
        STR, DEX, CON, INT, WIS, CHA
    }

    public enum AttackRanges {
        MELEE, DISTANCE
    }

    public enum DamageTypes {
        PIERCING, SLASHING, BLUDGEONING,
        ACID, COLD, FIRE, FORCE, LIGHTNING, NECROTIC, POISON, PSYCHIC, RADIANT, THUNDER
    }
}
