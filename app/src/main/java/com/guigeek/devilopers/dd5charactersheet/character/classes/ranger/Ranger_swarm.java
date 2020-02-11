package com.guigeek.devilopers.dd5charactersheet.character.classes.ranger;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Ranger_swarm extends BaseArchetype {
    static final long serialVersionUID = 2806L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_swarm);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Gathered Swarm");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Writhing Tide");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Scuttling Eyes");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Storm of Minions");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            int dice = iLevel >= 11 ? 2 : 1;
            powers.add(new Power("Gathered Swarm", "You magically attract a swarm of fey spirits that look like Tiny beasts of your choice. The swarm remains in your space, crawling on you or through your clothing, or flying and skittering immediately around you within your space.\n" +
                    "\n" +
                    "As a bonus action, you can agitate the swarm for 1 minute. For the duration, some of the swarm clings to your weapons or follows your strikes when you attack: once during each of your turns when you hit a creature with a weapon attack, you can deal an extra " + dice + "d6 force damage to that creature, and the swarm moves the creature up to 5 feet toward you or away from you (your choice).", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Writhing Tide", "You can condense part of your swarm into a focused mass that lifts or sweeps you along. Whenever you activate your Gathered Swarm feature, choose one of the following additional benefits:\n" +
                    "\n" +
                    "Your walking speed increases by 10 feet, and you can take the Disengage action as a bonus action.\n" +
                    "You gain a climb speed equal to your walking speed. You can climb difficult surfaces, including upside down on ceilings, without making an ability check.\n" +
                    "You gain a flying speed of 10 feet and can hover.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Scuttling Eyes", "As an action, you can magically form one of the spirits of your swarm into the shape of a Tiny beast of your choice. The transformation lasts for 1 hour, at which point the spirit disappears. For the duration, the spirit has a speed of 40 feet, which it can use to walk, climb, fly, or swim. The spirit has your senses and telepathically relays what it sees and hears to you. During your turn, you can speak through the spirit, telepathically command it to move, and it can Hide using your bonus to Dexterity (Stealth) checks. The spirit has AC 18. If it takes damage, you must succeed on a Wisdom saving throw (DC equal to 10, or half the damage dealt, whichever is higher) or the spirit disappears.\n" +
                    "\n" +
                    "As an action, you can dismiss the spirit early. If you do, you can magically teleport to an unoccupied space within 5 feet of where the spirit disappeared.\n" +
                    "\n" +
                    "Once you use this feature, you can’t do so again until you finish a long rest. You can also use it again by expending a spell slot of 3rd level or higher.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Storm of Minions", "Your swarm can expel a seething storm of spirits that drains life from others. As an action, you create a magical sphere filled with an enraged swarm centered on a point you can see within 120 feet of you. The sphere has a 10-foot-radius and lasts for 1 minute. The sphere is difficult terrain for creatures other than you. A creature other than you that starts its turn in the sphere’s area must make a Constitution saving throw against your spell save DC. On a failed save, the creature takes 2d8 necrotic damage and is blinded until the start of its next turn. On a successful save, it takes half as much damage and isn’t blinded. At the start of your turn, if any number of Small or larger creatures took necrotic damage from the swarm, you regain 1d8 hit points. On subsequent turns, you can use a bonus action to move the sphere up to 30 feet.\n" +
                    "\n" +
                    "When you activate this feature, you can choose any number of creatures you can see to be unaffected by it.\n" +
                    "\n" +
                    "Once you use this feature, you can’t do so again until you finish a long rest. You can also use it again by expending a spell slot of 4th level or higher.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }


        return powers;
    }
}
