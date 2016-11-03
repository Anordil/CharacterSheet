package com.guigeek.devilopers.dd5charactersheet.character;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Enumerations {

    public enum FettleType {
        ATTRIBUTE_MODIFIER,
        ABILITY_CHECK_ADVANTAGE,
        ABILITY_CHECK_DISADVANTAGE,
        ABILITY_CHECK_MODIFIER,
        ARMOR_CLASS_MODIFIER,
        MOVEMENT_SPEED_MODIFIER,
        ATTACK_BONUS_MODIFIER,
        ATTACK_DAMAGE_MODIFIER,
        DAMAGE_RESISTANCE,
        DAMAGE_VULNERABILITY,
        SAVING_THROW_ADVANTAGE,
        SAVING_THROW_DISADVANTAGE,
        SAVING_THROW_MODIFIER,
        IMMUNITY
    }

    public enum ActionType {
        PASSIVE,
        ACTION,
        BONUS_ACTION,
        REACTION
    }


    public enum Attributes {
        STR, DEX, CON, INT, WIS, CHA
    }

    public enum AttackRanges {
        MELEE, DISTANCE
    }

    public enum DamageTypes {
        PIERCING("Piercing"), SLASHING("Slashing"), BLUDGEONING("Bludgeoning"),
        ACID("Acid"), COLD("Cold"), FIRE("Fire"), FORCE("Force"), LIGHTNING("Lightning"), NECROTIC("Necrotic"), POISON("Poison"),
        PSYCHIC("Psychic"), RADIANT("Radiant"), THUNDER("Thunder");

        private String _name;

        DamageTypes(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }


    public enum SavingThrows {
        PIERCING("Piercing damage"), SLASHING("Slashing damage"), BLUDGEONING("Bludgeoning damage"),
        ACID("Acid damage"), COLD("Cold damage"), FIRE("Fire damage"), FORCE("Force damage"), LIGHTNING("Lightning damage"), NECROTIC("Necrotic damage"), POISON("Poison damage"),
        PSYCHIC("Psychic damage"), RADIANT("Radiant damage"), THUNDER("Thunder damage"),
        CHARM_MAGIC("Charm magic"),
        ALL("All"),
        STR("Strength"), DEX("Dexterity"), CON("Constitution"), INT("Intelligence"), CHA("Charisma"), WIS("Wisdom");

        private String _name;

        SavingThrows(String n) {
            _name = n;
        }

        public boolean isBasicSavingThrow() {
            return (this == STR || this == DEX || this == CON || this == WIS || this == INT || this == CHA || this == ALL);
        }

        public String toString() {
            return _name;
        }
    }


    public enum Immunities {
        PIERCING("Piercing damage"), SLASHING("Slashing damage"), BLUDGEONING("Bludgeoning damage"),
        ACID("Acid damage"), COLD("Cold damage"), FIRE("Fire damage"), FORCE("Force damage"), LIGHTNING("Lightning damage"), NECROTIC("Necrotic damage"), POISON("Poison damage"),
        PSYCHIC("Psychic damage"), RADIANT("Radiant damage"), THUNDER("Thunder damage"),
        CHARM_MAGIC("Charm magic"), DISEASES("Diseases"), FEAR("Fear");

        private String _name;
        Immunities(String n) {
            _name = n;
        }
        public String toString() {
            return _name;
        }
    }


    public enum Skills {
        ACROBATICS("Acrobatics"),
        ANIMAL_HANDLING("AnimalHandling"),
        ARCANA("Arcana"),
        ATHLETICS("Athletics"),
        DECEPTION("Deception"),
        HISTORY("History"),
        INSIGHT("Insight"),
        INTIMIDATION("Intimidation"),
        INVESTIHATION("Investigation"),
        MEDICINE("Medicine"),
        NATURE("Nature"),
        PERCEPTION("Perception"),
        PERFORMANCE("Performance"),
        PERSUASION("Persuasion"),
        RELIGION("Religion"),
        SLEIGHT_OF_HAND("Sleightofhand"),
        STEALTH("Stealth"),
        SURVIVAL("Survival"),
        ALL("All skills");

        private String _name;

        Skills(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }
}
