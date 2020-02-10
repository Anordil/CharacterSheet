package com.guigeek.devilopers.dd5charactersheet.character;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Enumerations {

    public enum FettleType {
        ATTRIBUTE_MODIFIER("Attribute mod"),
        ABILITY_CHECK_ADVANTAGE("Skill check adv"),
        ABILITY_CHECK_DISADVANTAGE("Skill check disadv"),
        ABILITY_CHECK_MODIFIER("Skill check mod"),
        ABILITY_PROFICIENCY("Skill check proficiency"),
        ARMOR_CLASS_MODIFIER("AC modifier"),
        MOVEMENT_SPEED_MODIFIER("Speed modifier"),
        ATTACK_BONUS_MODIFIER("Attack bonus mod"),
        ATTACK_DAMAGE_MODIFIER("Attack damage mod"),
        DAMAGE_RESISTANCE("Damage resistance"),
        DAMAGE_VULNERABILITY("Damage vulnerability"),
        SAVING_THROW_ADVANTAGE("Saving throw adj"),
        SAVING_THROW_DISADVANTAGE("Saving throw disadv"),
        SAVING_THROW_PROFICIENCY("Saving throw proficiency"),
        SAVING_THROW_MODIFIER("Saving throw mod"),
        IMMUNITY("Immunity"),
        ATTACK_DAMAGE_DICE("Atk damage (dice)"),
        TEXT_FETTLE("Free text");

        private String _name;

        FettleType(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }

    public enum ActionType {
        PASSIVE,
        ACTION,
        BONUS_ACTION,
        REACTION
    }


    public enum Attributes {
        STR("Strength"), DEX("Dexterity"), CON("Constitution"), INT("Intelligence"), WIS("Wisdom"), CHA("Charisma");

        private String _name;
        public String getShortName() {
            return _name.substring(0, 3);
        }

        Attributes(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }


    public enum DamageTypes {
        PIERCING("Piercing"), SLASHING("Slashing"), BLUDGEONING("Bludgeoning"),
        ACID("Acid"), COLD("Cold"), FIRE("Fire"), FORCE("Force"), LIGHTNING("Lightning"), NECROTIC("Necrotic"), POISON("Poison"),
        PSYCHIC("Psychic"), RADIANT("Radiant"), THUNDER("Thunder"), SPELLS("Spells");

        private String _name;

        DamageTypes(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }


    public enum SavingThrows {
        PIERCING_DMG("Piercing damage"), SLASHING_DMG("Slashing damage"), BLUDGEONING_DMG("Bludgeoning damage"),
        ACID_DMG("Acid damage"), COLD_DMG("Cold damage"), FIRE_DMG("Fire damage"), FORCE_DMG("Force damage"), LIGHTNING_DMG("Lightning damage"),
        NECROTIC_DMG("Necrotic damage"), POISON_DMG("Poison damage"),
        PSYCHIC_DMG("Psychic damage"), RADIANT_DMG("Radiant damage"), THUNDER_DMG("Thunder damage"),
        CHARM_MAGIC("Charm magic"), SPELLS("Spells"),
        FEAR("Fear"), DISEASE("Disease"),
        ALL("All"),
        STR("Strength"), DEX("Dexterity"), CON("Constitution"), INT("Intelligence"), CHA("Charisma"), WIS("Wisdom"),
        DEX_BARBARIAN("Dexterity (from effects you can see)"), POISON("Poison");

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
        CHARM_MAGIC("Charm magic"), DISEASES("Diseases"), FEAR("Fear"),
        BLINDED("Blinded"), DEAFENED("Deafened"), POISONED("Poisoned");

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
        ANIMAL_HANDLING("Animal Handling"),
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
        SLEIGHT_OF_HAND("Sleight of hand"),
        STEALTH("Stealth"),
        SURVIVAL("Survival"),
        ALL("All skills"),

        INITIATIVE("Initiative");

        private String _name;
        Skills(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }


    public enum ArmorCategories {
        NONE, LIGHT, MEDIUM, HEAVY, SHIELD;
    }

    public enum ArmorTypes {
        NONE("No armor", ArmorCategories.NONE),
        PADDED("Padded armor", ArmorCategories.LIGHT),
        LEATHER("Leather armor", ArmorCategories.LIGHT),
        STUDDED_LEATHER("Studded leather armor", ArmorCategories.LIGHT),
        HIDE("Hide armor", ArmorCategories.MEDIUM),
        CHAIN_SHIRT("Chain shirt", ArmorCategories.MEDIUM),
        SCALE_MAIL("Scale mail", ArmorCategories.MEDIUM),
        BREASTPLATE("Breatplate", ArmorCategories.MEDIUM),
        HALF_PLATE("Half plate", ArmorCategories.MEDIUM),
        RING_MAIL("Ring mail", ArmorCategories.HEAVY),
        CHAIN_MAIL("Chain mail", ArmorCategories.HEAVY),
        SPLINT("Splint armor", ArmorCategories.HEAVY),
        PLATE("Plate armor", ArmorCategories.HEAVY),
        SHIELD("Shield", ArmorCategories.NONE);

        private String _name;
        private ArmorCategories _category;
        ArmorTypes(String n, ArmorCategories c) {
            _name = n;
            _category = c;
        }

        public String toString() {
            return _name;
        }
    }

    public enum WeaponDistanceTypes {
        MELEE, DISTANCE, THROWN;
    }

    public enum WeaponWeightCategory {
        LIGHT, NORMAL, HEAVY;
    }

    public enum WeaponHandCount {
        ONE_HANDED, VERSATILE, TWO_HANDED;
    }

    public enum WeaponTypes {
        CLUB("Club"), DAGGER("Dagger"), GREAT_CLUB("Great club"), HANDAXE("Hand axe"), JAVELIN("Javelin"), LIGHT_HAMMER("Light hammer"),
        MACE("Mace"), QUARTERSTAFF("Quarterstaff"), SICKLE("Sickle"), SPEAR("Spear"), LIGHT_CROSSBOW("Light crossbow"), DART("Dart"),
        SHORTBOW("Shortbow"), SLING("Sling"), BATTLEAXE("Battle axe"), FLAIL("Flail"), GLAIVE("Glaive"), GREATAXE("Great axe"),
        GREATSWORD("Great sword"), HALBERD("Halberd"), LANCE("Lance"), LONGSWORD("Longsword"), MAUL("Maul"), MORNINGSTAR("Morningstar"),
        PIKE("Pike"), RAPIER("Rapier"), SCIMITAR("Scimitar"), SHORTSWORD("Shortsword"), TRIDENT("Trident"), WAR_PICK("War pick"),
        WARHAMMER("Warhammer"), WHIP("Whip"), BLOWGUN("Blowgun"), HAND_CROSSBOW("Hand crossbow"), HEAVY_CROSSBOW("Heavy crossbow"),
        LONGBOW("Longbow"), NET("Net"),
        PALM_PISTOL("Palm Pistol"), PISTOL("Pistol"), MUSKET("Musket"), BLUNDERBUSS("Blunderbuss"), PEPPERBOX("Pepperbox"), BAD_NEWS("Bad News"), HAND_MORTAR("Hand Mortar"),
        UNARMED("Unarmed");

        public String _name;

        WeaponTypes(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }
}
