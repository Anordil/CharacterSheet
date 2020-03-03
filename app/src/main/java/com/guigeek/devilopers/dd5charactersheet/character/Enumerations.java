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

    public enum Backgrounds {
        ACOLYTE("Acolyte", Skills.INSIGHT, Skills.RELIGION),
        CHARLATAN("Charlatan", Skills.DECEPTION, Skills.SLEIGHT_OF_HAND),
        CRIMINAL("Criminal", Skills.DECEPTION, Skills.STEALTH),
        ENTERTAINER("Entertainer", Skills.ACROBATICS, Skills.PERFORMANCE),
        FOLK_HERO("Folk Hero", Skills.ANIMAL_HANDLING, Skills.SURVIVAL),
        GLADIATOR("Gladiator", Skills.ACROBATICS, Skills.PERFORMANCE),
        ARTISAN("Artisan", Skills.INSIGHT, Skills.PERSUASION),
        HERMIT("Hermit", Skills.MEDICINE, Skills.RELIGION),
        KNIGHT("Knight", Skills.HISTORY, Skills.PERSUASION),
        NOBLE("Noble", Skills.HISTORY, Skills.PERSUASION),
        OUTLANDER("Outlander", Skills.ATHLETICS, Skills.SURVIVAL),
        PIRATE("Pirate", Skills.ATHLETICS, Skills.PERCEPTION),
        SAGE("Sage", Skills.ARCANA, Skills.HISTORY),
        SAILOR("Sailor", Skills.ATHLETICS, Skills.PERCEPTION),
        SOLDIER("Soldier", Skills.ATHLETICS, Skills.INTIMIDATION),
        URCHIN("Urchin", Skills.SLEIGHT_OF_HAND, Skills.STEALTH);

        public String _name;
        public Enumerations.Skills _firstSkill, _secondSkill;

        Backgrounds(String name, Enumerations.Skills first, Enumerations.Skills second) {
            _name = name;
            _firstSkill = first;
            _secondSkill = second;
        }

        public Enumerations.Backgrounds createByName(String name) {
            if (name.equals("Acolyte")) { return Backgrounds.ACOLYTE; }
            if (name.equals("Charlatan")) { return Backgrounds.CHARLATAN; }
            if (name.equals("Criminal")) { return Backgrounds.CRIMINAL; }
            if (name.equals("Entertainer")) { return Backgrounds.ENTERTAINER; }
            if (name.equals("Folk Hero")) { return Backgrounds.FOLK_HERO; }
            if (name.equals("Gladiator")) { return Backgrounds.GLADIATOR; }
            if (name.equals("Artisan")) { return Backgrounds.ARTISAN; }
            if (name.equals("Hermit")) { return Backgrounds.HERMIT; }
            if (name.equals("Knight")) { return Backgrounds.KNIGHT; }
            if (name.equals("Noble")) { return Backgrounds.NOBLE; }
            if (name.equals("Outlander")) { return Backgrounds.OUTLANDER; }
            if (name.equals("Pirate")) { return Backgrounds.PIRATE; }
            if (name.equals("Sage")) { return Backgrounds.SAGE; }
            if (name.equals("Sailor")) { return Backgrounds.SAILOR; }
            if (name.equals("Soldier")) { return Backgrounds.SOLDIER; }
            if (name.equals("Urchin")) { return Backgrounds.URCHIN; }

            return null;
        }

        public Power getPower(Enumerations.Backgrounds bg) {
            switch (bg) {
                case ACOLYTE:
                    return new Power("Shelter of the Faithful", "As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n" +
                            "\n" +
                            "You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.", "", -1, 0, false, ActionType.PASSIVE);
                case CHARLATAN:
                    return new Power("False Identity", "You have created a second identity that includes documentation, established acquaintances, and disguises that allow you to assume that persona. Additionally, you can forge documents including official papers and personal letters, as long as you have seen an example of the kind of document or the handwriting you are trying to copy.", "", -1, 0, false, ActionType.PASSIVE);
                case CRIMINAL:
                    return new Power("Criminal Contact", "You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.", "", -1, 0, false, ActionType.PASSIVE);
                case ENTERTAINER:
                    return new Power("By Popular Demand", "You can always find a place to perform, usually in an inn or tavern but possibly with a circus, at a theater, or even in a noble’s court. At such a place, you receive free lodging and food of a modest or comfortable standard (depending on the quality of the establishment), as long as you perform each night. In addition, your performance makes you something of a local figure. When strangers recognize you in a town where you have performed, they typically take a liking to you.", "", -1, 0, false, ActionType.PASSIVE);
                case FOLK_HERO:
                    return new Power("Rustic Hospitality", "Since you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.", "", -1, 0, false, ActionType.PASSIVE);
                case GLADIATOR:
                    return new Power("By Popular Demand", "You can always find a place to perform in any place that features combat for entertainment — perhaps a gladiatorial arena or secret pit fighting club. At such a place, you receive free lodging and food of a modest or comfortable standard (depending on the quality of the establishment), as long as you perform each night. In addition, your performance makes you something of a local figure. When strangers recognize you in a town where you have performed, they typically take a liking to you.", "", -1, 0, false, ActionType.PASSIVE);
                case ARTISAN:
                    return new Power("Guild Membership", "As an established and respected member of a guild, you can rely on certain benefits that membership provides. Your fellow guild members will provide you with lodging and food if necessary, and pay for your funeral if needed. In some cities and towns, a guildhall offers a central place to meet other members of your profession, which can be a good place to meet potential patrons, allies, or hirelings.\n" +
                            "\n" +
                            "Guilds often wield tremendous political power. If you are accused of a crime, your guild will support you if a good case can be made for your innocence or the crime is justifiable. You can also gain access to powerful political figures through the guild, if you are a member in good standing. Such connections might require the donation of money or magic items to the guild’s coffers.\n" +
                            "\n" +
                            "You must pay dues of 5 gp per month to the guild. If you miss payments, you must make up back dues to remain in the guild’s good graces.", "", -1, 0, false, ActionType.PASSIVE);
                case HERMIT:
                    return new Power("Discovery", "The quiet seclusion of your extended hermitage gave you access to a unique and powerful discovery. The exact nature of this revelation depends on the nature of your seclusion. It might be a great truth about the cosmos, the deities, the powerful beings of the outer planes, or the forces of nature. It could be a site that no one else has ever seen. You might have uncovered a fact that has long been forgotten, or unearthed some relic of the past that could rewrite history. It might be information that would be damaging to the people who or consigned you to exile, and hence the reason for your return to society.\n" +
                            "\n" +
                            "Work with your DM to determine the details of your discovery and its impact on the campaign.", "", -1, 0, false, ActionType.PASSIVE);
                case KNIGHT:
                    return new Power("Retainers", "You have the service of three retainers loyal to your family. These retainers can be attendants or messengers, and one might be a majordomo. Your retainers are commoners who can perform mundane tasks for you, but they do not fight for you, will not follow you into obviously dangerous areas (such as dungeons), and will leave if they are frequently endangered or abused.", "", -1, 0, false, ActionType.PASSIVE);
                case NOBLE:
                    return new Power("Position of Privilege", "Thanks to your noble birth, people are inclined to think the best of you. You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.", "", -1, 0, false, ActionType.PASSIVE);
                case OUTLANDER:
                    return new Power("Wanderer", "You have an excellent memory for maps and geography, and you can always recall the general layout of terrain, settlements, and other features around you. In addition, you can find food and fresh water for yourself and up to five other people each day, provided that the land offers berries, small game, water, and so forth.", "", -1, 0, false, ActionType.PASSIVE);
                case PIRATE:
                    return new Power("Bad Reputation", "No matter where you go, people are afraid of you due to your reputation. When you are in a civilized settlement, you can get away with minor criminal offenses, such as refusing to pay for food at a tavern or breaking down doors at a local shop, since most people will not report your activity to the authorities.", "", -1, 0, false, ActionType.PASSIVE);
                case SAILOR:
                    return new Power("Ship's Passage", "When you need to, you can secure free passage on a sailing ship for yourself and your adventuring companions. You might sail on the ship you served on, or another ship you have good relations with (perhaps one captained by a former crewmate). Because you’re calling in a favor, you can’t be certain of a schedule or route that will meet your every need. Your Dungeon Master will determine how long it takes to get where you need to go. In return for your free passage, you and your companions are expected to assist the crew during the voyage.", "", -1, 0, false, ActionType.PASSIVE);
                case SAGE:
                    return new Power("Researcher", "When you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature. Your DM might rule that the knowledge you seek is secreted away in an almost inaccessible place, or that it simply cannot be found. Unearthing the deepest secrets of the multiverse can require an adventure or even a whole campaign.", "", -1, 0, false, ActionType.PASSIVE);
                case SOLDIER:
                    return new Power("Military Rank", "You have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly military encampments and fortresses where your rank is recognized.", "", -1, 0, false, ActionType.PASSIVE);
                case URCHIN:
                    return new Power("City Secrets", "You know the secret patterns and flow to cities and can find passages through the urban sprawl that others would miss. When you are not in combat, you (and companions you lead) can travel between any two locations in the city twice as fast as your speed would normally allow.", "", -1, 0, false, ActionType.PASSIVE);
                default:
                    return null;
            }
        }
    }

    public enum WeaponCategories {
        SIMPLE("Simple"),
        UNKNOWN("Unknown"),
        UNARMED("Unarmed"),
        FIREARM("Firearm"),
        MARTIAL("Martial");

        private String _name;

        WeaponCategories(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }

    public enum Proficiencies {
        WEAPON_SIMPLE("Simple weapons"),
        WEAPON_FIREARM("Firearms"),
        WEAPON_MARTIAL("Martial weapons"),

        LONGSWORD("Longsword"),
        SHORTSWORD("Shortsword"),
        LONGBOW("Longbow"),
        SHORTBOW("Shortbow"),

        BATTLEAXE("Battleaxe"),
        HANDAXE("Handaxe"),
        LIGHT_HAMMER("Light hammer"),
        WARHAMMER("Warhammer"),

        HAND_CROSSBOW("Hand crosbbow"),
        RAPIER("Rapier"),

        ARMOR_LIGHT("Light armor"),
        ARMOR_MEDIUM("Medium armor"),
        ARMOR_HEAVY("Heavy armor"),
        SHIELD("Shield"),

        CLUB("Club"),
        DAGGER("Dagger"),
        DART("Dart"),
        JAVELIN("Javelin"),
        MACE("Mace"),
        QUARTERSTAFF("Quarterstaff"),
        SCIMITAR("Scimitar"),
        SICKLE("Sickle"),
        SLING("Sling"),
        SPEAR("Spear"),
        LIGHT_CROSSBOW("Light crossbow"),
        ;

        private String _name;

        Proficiencies(String n) {
            _name = n;
        }

        public String toString() {
            return _name;
        }
    }
}
