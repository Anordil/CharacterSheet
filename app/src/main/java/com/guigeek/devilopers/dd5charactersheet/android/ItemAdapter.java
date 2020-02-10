package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Consumable;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 25/03/2016.
 */
public class ItemAdapter extends ArrayAdapter<Externalizable> {

    private List<Externalizable> _items;

    public ItemAdapter(Context context, int textViewResourceId, List<Externalizable> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item, null);
        }
        Object item = _items.get(position);

        TextView nameTv = (TextView) convertView.findViewById(R.id.listItemName);
        TextView listItemFirearmDetails = convertView.findViewById(R.id.listItemFirearmDetails);
        ImageView icon = (ImageView) convertView.findViewById(R.id.listItemIcon);
        TableLayout fettleTable = (TableLayout)convertView.findViewById(R.id.listItemFettleTable);

        LinkedList<Fettle> magicProperties = null;
        listItemFirearmDetails.setVisibility(View.GONE);

        nameTv.setText(item.toString());
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(getWeaponIcon(weapon)));
            magicProperties = weapon._magicProperties;

            if (weapon._isFirearm) {
                listItemFirearmDetails.setVisibility(View.VISIBLE);
                listItemFirearmDetails.setText("Reload: " + weapon._reload + ", misfire: " + weapon._misfire);
            }
        }
        else if (item instanceof Armor) {
            Armor armor = (Armor)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(getArmorIcon(armor)));
            magicProperties = armor._magicProperties;
        }
        else if (item instanceof Item) {
            Item theItem = (Item)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_ring));
            magicProperties = theItem._magicProperties;
        }
        else if (item instanceof Consumable) {
            Consumable theItem = (Consumable)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_potion_ball));
            magicProperties = new LinkedList<>();
            magicProperties.add(new Fettle(Enumerations.FettleType.TEXT_FETTLE, theItem._effect, 0));
        }


        if (magicProperties != null) {
            for (Fettle effect : magicProperties) {
                TableRow aRow = new TableRow(getContext());
                TextView effectDescription = new TextView(getContext());
                effectDescription.setSingleLine(false);
                effectDescription.setMaxLines(10);
                effectDescription.setHorizontalScrollBarEnabled(false);
                effectDescription.setText(effect.toString());
                TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                effectDescription.setLayoutParams(params);
                aRow.addView(effectDescription);
                fettleTable.addView(aRow);
            }
        }
        return convertView;
    }

    public static int getWeaponIcon(Weapon weapon) {
        switch(weapon._type) {
            case CLUB:
                return R.drawable.ic_spiked_mace;
            case DAGGER:
                return R.drawable.ic_broad_dagger;
            case GREAT_CLUB:
                return R.drawable.ic_spiked_mace;
            case HANDAXE:
                return R.drawable.ic_fire_axe;
            case JAVELIN:
                return R.drawable.ic_barbed_spear;
            case LIGHT_HAMMER:
                return R.drawable.ic_flat_hammer;
            case MACE:
                return R.drawable.ic_flanged_mace;
            case QUARTERSTAFF:
                return R.drawable.ic_wizard_staff;
            case SICKLE:
                return R.drawable.ic_sickle;
            case SPEAR:
                return R.drawable.ic_barbed_spear;
            case LIGHT_CROSSBOW:
                return R.drawable.ic_crossbow;
            case DART:
                return R.drawable.ic_thrown_daggers;
            case SHORTBOW:
                return R.drawable.ic_pocket_bow;
            case SLING:
                return R.drawable.ic_slingshot;
            case BATTLEAXE:
                return R.drawable.ic_battle_axe;
            case FLAIL:
                return R.drawable.ic_mace_head;
            case GLAIVE:
                return R.drawable.ic_halberd;
            case GREATAXE:
                return R.drawable.ic_battle_axe;
            case GREATSWORD:
                return R.drawable.ic_broadsword;
            case HALBERD:
                return R.drawable.ic_halberd;
            case LANCE:
                return R.drawable.ic_barbed_spear;
            case LONGSWORD:
                return R.drawable.ic_broadsword;
            case MAUL:
                return R.drawable.ic_flat_hammer;
            case MORNINGSTAR:
                return R.drawable.ic_mace_head;
            case PIKE:
                return R.drawable.ic_barbed_spear;
            case RAPIER:
                return R.drawable.ic_sword_hilt;
            case SCIMITAR:
                return R.drawable.ic_broadsword;
            case SHORTSWORD:
                return R.drawable.ic_broadsword;
            case TRIDENT:
                return R.drawable.ic_trident;
            case WAR_PICK:
                return R.drawable.ic_flat_hammer;
            case WARHAMMER:
                return R.drawable.ic_flat_hammer;
            case WHIP:
                return R.drawable.ic_whip;
            case BLOWGUN:
                return R.drawable.ic_sawed_off_shotgun;
            case HAND_CROSSBOW:
                return R.drawable.ic_crossbow;
            case HEAVY_CROSSBOW:
                return R.drawable.ic_crossbow;
            case LONGBOW:
                return R.drawable.ic_pocket_bow;
            case NET:
                return R.drawable.ic_fishing_net;
            case UNARMED:
                return R.drawable.ic_fist;

            case PISTOL:
            case PALM_PISTOL:
                return R.drawable.ic_crossed_pistols;
            case MUSKET:
                return R.drawable.ic_musket;
            case BLUNDERBUSS:
            case PEPPERBOX:
                return R.drawable.ic_blunderbuss;
            case BAD_NEWS:
            case HAND_MORTAR:
                return R.drawable.ic_mortar;

            default: return R.drawable.ic_fire_axe;
        }
    }

    public int getArmorIcon(Armor armor) {
        switch (armor._type) {
            case NONE:
                return R.drawable.ic_swap_bag;
            case PADDED:
                return R.drawable.ic_leather_vest;
            case LEATHER:
                return R.drawable.ic_leather_vest;
            case STUDDED_LEATHER:
                return R.drawable.ic_leather_vest;
            case HIDE:
                return R.drawable.ic_leather_vest;
            case CHAIN_SHIRT:
                return R.drawable.ic_chain_mail;
            case SCALE_MAIL:
                return R.drawable.ic_scale_mail;
            case BREASTPLATE:
                return R.drawable.ic_armor_vest;
            case HALF_PLATE:
                return R.drawable.ic_armor_vest;
            case RING_MAIL:
                return R.drawable.ic_breastplate;
            case CHAIN_MAIL:
                return R.drawable.ic_breastplate;
            case SPLINT:
                return R.drawable.ic_breastplate;
            case PLATE:
                return R.drawable.ic_breastplate;
            case SHIELD:
                return R.drawable.ic_round_shield;
            default:
                return R.drawable.ic_armor_vest;
        }
    }
}
