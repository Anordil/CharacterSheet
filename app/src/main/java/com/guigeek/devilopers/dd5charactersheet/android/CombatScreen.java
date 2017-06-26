package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 22/02/2016.
 */
public class CombatScreen extends Fragment {

    protected Character _character;
    TextView viewHPCurrent, viewHPMax, viewHitDiceCurrent, viewHitDiceMax, tvAC, tvAtk, tvDmg, tvHitDiceDesc, tvHitDiceDescSec, tvAtkThrown, tvDmgThrown;
    TextView viewHitDiceCurrentSecondary, viewHitDiceMaxSecondary;
    TextView viewSpeed, viewInit, spellAtk, spellDD, weaponName, weaponNameOffHand;
    ImageView imageWeaponHit;
    TableRow rowThrown, rowThrownOffHand, rowNameOffHand, rowMeleeOffHand, rowDamageModsTitle;
    ProgressBar pb;
    TableRow rowSecondaryHD;

    TextView tvAtkOffHand, tvDmgOffHand, tvAtkThrownOffHand, tvDmgThrownOffHand;

    List<TextView> spellSlotTextViews;
    TableLayout fettleTable, damageModsTable, fettleTableOffHand;

    public CombatScreen() {}

    public static CombatScreen newInstance(Character iCharac) {
        CombatScreen fragment = new CombatScreen();
        Bundle args = new Bundle();
        args.putSerializable(Constants.CHARACTER, iCharac);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Serializable data = bundle.getSerializable(Constants.CHARACTER);
            _character = (Character) data;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        View rootView = inflater.inflate(R.layout.fragment_combat, container, false);
        viewHPCurrent = (TextView)rootView.findViewById(R.id.tvHPCurrent);
        viewHPMax = (TextView)rootView.findViewById(R.id.tvHPMax);
        viewHitDiceCurrent = (TextView)rootView.findViewById(R.id.tvHitDiceCurrent);
        viewHitDiceCurrentSecondary = (TextView)rootView.findViewById(R.id.tvHitDiceCurrentSecondary);
        viewHitDiceMax = (TextView)rootView.findViewById(R.id.tvHitDiceMax);
        viewHitDiceMaxSecondary = (TextView)rootView.findViewById(R.id.tvHitDiceMaxSecondary);
        tvAC = (TextView)rootView.findViewById(R.id.tvArmorClass);
        pb = (ProgressBar)rootView.findViewById(R.id.progressBar);

        tvAtk = (TextView)rootView.findViewById(R.id.tvAtkBonus);
        tvDmg = (TextView)rootView.findViewById(R.id.tvDamage);

        tvAtkThrown = (TextView)rootView.findViewById(R.id.tvAtkBonusThrown);
        tvDmgThrown = (TextView)rootView.findViewById(R.id.tvDamageThrown);

        tvAtkOffHand = (TextView)rootView.findViewById(R.id.tvAtkBonusOffHand);
        tvDmgOffHand = (TextView)rootView.findViewById(R.id.tvDamageOffHand);
        tvAtkThrownOffHand = (TextView)rootView.findViewById(R.id.tvAtkBonusThrownOffHand);
        tvDmgThrownOffHand = (TextView)rootView.findViewById(R.id.tvDamageThrownOffHand);

        viewSpeed = (TextView)rootView.findViewById(R.id.tvSpeed);
        viewInit = (TextView)rootView.findViewById(R.id.tvInitiative);

        spellAtk = (TextView)rootView.findViewById(R.id.tvSpellAtk);
        spellDD = (TextView)rootView.findViewById(R.id.tvSpellDD);

        spellDD.setText(Integer.toString(8 + _character.getProficiencyBonus() + _character.getModifier(_character._class.getMainSpellAttribute())));
        spellAtk.setText("+" + Integer.toString(_character.getProficiencyBonus() + _character.getModifier(_character._class.getMainSpellAttribute())));


        tvHitDiceDesc = (TextView)rootView.findViewById(R.id.textViewHitDice);
        tvHitDiceDesc.setText("HD (D" + _character._class.getHitDie() + ")");

        tvHitDiceDescSec = (TextView)rootView.findViewById(R.id.textViewHitDiceSecondary);
        if (_character._secondaryClass != null) {
            tvHitDiceDescSec.setText("HD (D" + _character._secondaryClass.getHitDie() + ")");
        }

        rowSecondaryHD = (TableRow)rootView.findViewById(R.id.rowHitDiceSec);

        weaponName = (TextView)rootView.findViewById(R.id.tvWeaponName);
        weaponNameOffHand = (TextView)rootView.findViewById(R.id.tvWeaponNameOffHand);
        weaponName.setText(_character._equippedWeapon.toString());

        imageWeaponHit = (ImageView)rootView.findViewById(R.id.imgCombatHitBonus);
        rowThrown = (TableRow)rootView.findViewById(R.id.combatRowThrown);

        rowThrownOffHand = (TableRow)rootView.findViewById(R.id.combatRowThrownOffHand);
        rowNameOffHand = (TableRow)rootView.findViewById(R.id.rowNameOffHand);
        rowMeleeOffHand = (TableRow)rootView.findViewById(R.id.rowMeleeOffHand);
        rowDamageModsTitle = (TableRow)rootView.findViewById(R.id.rowDamageModsTitle);


        if (!_character._class.isCaster()) {
            TableRow spellRow = (TableRow)rootView.findViewById(R.id.rowSpell);
            spellRow.setVisibility(View.GONE);
        }

        fettleTable = (TableLayout)rootView.findViewById(R.id.combatWeaponProperties);
        fettleTableOffHand = (TableLayout)rootView.findViewById(R.id.combatWeaponPropertiesOffHand);
        damageModsTable = (TableLayout)rootView.findViewById(R.id.combatTableDamageMods);

        addButtonListener(rootView);
        createFettlesBar(rootView);
        createSpellBars(rootView);
        createSpecialPowerBars(rootView, "Class Features", _character.getClassPowers());
        createSpecialPowerBars(rootView, "Racial Features", _character._race.getRacialFeatures());
        refreshSheet();

        return rootView;
    }

    private void addButtonListener(View rootView) {

        View.OnClickListener hpList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHP(v);
            }
        };
        View.OnClickListener hdList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHD(v);
            }
        };
        View.OnClickListener hdListSecondary = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHDSecondary(v);
            }
        };

        rootView.findViewById(R.id.btnHPMin1).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPMin5).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPPlus1).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPPlus5).setOnClickListener(hpList);

        rootView.findViewById(R.id.btnHDMin1).setOnClickListener(hdList);
        rootView.findViewById(R.id.btnHDPlus1).setOnClickListener(hdList);

        rootView.findViewById(R.id.btnHDMin1Secondary).setOnClickListener(hdListSecondary);
        rootView.findViewById(R.id.btnHDPlus1Secondary).setOnClickListener(hdListSecondary);
    }




    private void createFettlesBar(View root) {
        if (!_character.getFettles().isEmpty()) {
            TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

            // Title
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText("Passive effects");
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 8;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);

            spellSlotTextViews = new ArrayList<TextView>();
            for (Fettle fettle : _character.getFettles()) {
                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView description = new TextView(getContext());
                description.setText(fettle.toString());
                TableRow.LayoutParams paramRow = new TableRow.LayoutParams();
                paramRow.span = 8;
                description.setLayoutParams(paramRow);

                row.addView(description);
                ll.addView(row);
            }
        }
    }



    private void createSpellBars(View root) {
        if (_character._class.isCaster()) {
            TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

            // Title
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText("Spell slots by level");
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 8;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);

            spellSlotTextViews = new ArrayList<TextView>();
            for (int i = 1; i <= 9; i++) {

                int maxSpellForSlot = _character._spellSlotsMax[i];

                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView level = new TextView(getContext()), current = new TextView(getContext()), max = new TextView(getContext());
                level.setText(Integer.toString(i));
                current.setText(Integer.toString(_character._spellSlotsCurrent[i]));
                max.setText(Integer.toString(_character._spellSlotsMax[i]));
                current.setTextColor(getResources().getColor(android.R.color.black));
                spellSlotTextViews.add(current);

                Button minus = new Button(getContext()), plus = new Button(getContext());
                minus.setText("-1");
                minus.setTag(i + " -1");
                plus.setText("+1");
                plus.setTag(i + " 1");
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSpellSlot(v);
                    }
                });
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSpellSlot(v);
                    }
                });

                TableRow.LayoutParams rowParam = new TableRow.LayoutParams();
                rowParam.span = 2;
                minus.setLayoutParams(rowParam);
                plus.setLayoutParams(rowParam);
                level.setLayoutParams(rowParam);

                row.addView(level);
                row.addView(current);
                row.addView(max);
                row.addView(minus);
                row.addView(plus);
                ll.addView(row);

                if (maxSpellForSlot == 0) {
                    row.setVisibility(View.GONE);
                }
            }
        }
    }

    private void createSpecialPowerBars(View root, String title, LinkedList<Power> powers) {
        TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

        if (powers.size() > 0) {
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText(title);
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 8;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);
        }

        for (Power power: powers) {

            TableRow row = new TableRow(getContext());
            TableRow rowDesc = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            rowDesc.setPadding(0, 0, 0, 20);

            TextView name = new TextView(getContext()), current = new TextView(getContext()), max = new TextView(getContext());
            TextView description = new TextView(getContext()), desc = new TextView(getContext()), dd = new TextView(getContext());

            name.setText(power._name);
            name.setTextSize(16.0f);
            name.setTextColor(getResources().getColor(android.R.color.black));
            current.setText(power._left + "");
            current.setTextColor(getResources().getColor(android.R.color.black));
            max.setText(power._max + "");
            description.setText(power.getUsageString());
            desc.setText(power._description);
            dd.setText((power._dd > 0 ? "DD" + power._dd : "") + "");

            TableRow.LayoutParams rowParamName = new TableRow.LayoutParams();
            rowParamName.span = 4;
            name.setLayoutParams(rowParamName);
            TableRow.LayoutParams rowParamNameDesc = new TableRow.LayoutParams();
            rowParamNameDesc.span = 3;
            description.setLayoutParams(rowParamNameDesc);

            row.addView(name);
            row.addView(description);
            row.addView(dd);
            ll.addView(row);

            // Uses?
            if (power._max > 0) {
                Button minus = new Button(getContext()), plus = new Button(getContext());
                minus.setText("-1");
                plus.setText("+1");
                minus.setOnClickListener(new PowerListener(true, power, current));
                plus.setOnClickListener(new PowerListener(false, power, current));

                TableRow.LayoutParams rowParam = new TableRow.LayoutParams();
                rowParam.span = 2;
                minus.setLayoutParams(rowParam);
                plus.setLayoutParams(rowParam);

                TableRow rowUsage= new TableRow(getContext());

                TextView aLongRestShortRestTV = new TextView(getContext());
                aLongRestShortRestTV.setText(power._isLongRest ? "LR" : "SR");
                aLongRestShortRestTV.setLayoutParams(rowParam);

                rowUsage.addView(aLongRestShortRestTV);
                rowUsage.addView(current);
                rowUsage.addView(max);
                rowUsage.addView(minus);
                rowUsage.addView(plus);

                rowUsage.setLayoutParams(lp);
                ll.addView(rowUsage);
            }

            TableRow.LayoutParams rowParamDesc = new TableRow.LayoutParams();
            rowParamDesc.span = 8;
            desc.setLayoutParams(rowParamDesc);
            rowDesc.addView(desc);
            ll.addView(rowDesc);
        }
    }

    public void refreshSheet() {
        Weapon weapon = _character._equippedWeapon;
        pb.setMax(_character._hpMax);
        pb.setProgress(_character._hpCurrent);

        viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
        viewHPMax.setText(Integer.toString(_character._hpMax));

        viewHitDiceMax.setText(Integer.toString(_character._level));
        viewHitDiceMaxSecondary.setText(Integer.toString(_character._levelSecondaryClass));

        viewHitDiceCurrent.setText(Integer.toString(_character._hitDice));
        viewHitDiceCurrentSecondary.setText(Integer.toString(_character._hitDiceSecondary));

        // Show secondary HD?

        if (_character._levelSecondaryClass == 0) {
            rowSecondaryHD.setVisibility(View.GONE);
        }
        else {
            rowSecondaryHD.setVisibility(View.VISIBLE);
        }

        tvAC.setText(_character.getAC() + "");

        int modDex = _character.getModifier(Enumerations.Attributes.DEX);
        int modStr = _character.getModifier(Enumerations.Attributes.STR);

        boolean distanceWeapon = weapon._distance == Enumerations.WeaponDistanceTypes.DISTANCE;
        boolean finesseWeapon = weapon._isFinesse;

        if (distanceWeapon) {
            imageWeaponHit.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_pocket_bow));
        }

        int propertyAttackBonus = 0;
        String propertyDamageBonus = "";
        for (Fettle property : _character.getFettles()) {
            if (property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                propertyAttackBonus = Math.max(property._value, propertyAttackBonus);
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._value + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._valueStr + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
        }

        int abilityModifier = (distanceWeapon ? modDex : (finesseWeapon ? (Math.max(modDex, modStr)) : modStr));

        int dmgBonus = abilityModifier +  _character._dmgBonus + weapon._magicModifier;
        int attackBonus = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;

        int diceDamage = weapon._diceValue;
        if (weapon._hands == Enumerations.WeaponHandCount.VERSATILE && _character._equippedShield._type == Enumerations.ArmorTypes.NONE) {
            diceDamage =  weapon._diceValueVersatile;
        }

        String damage = weapon._diceCount + "D" + diceDamage + (dmgBonus > 0 ? "+":"") + (dmgBonus != 0 ? dmgBonus : "")
                + (weapon._damageType != null ? " (" + weapon._damageType.toString().substring(0,2) + ")" : "")
                + propertyDamageBonus;
        tvDmg.setText(damage);
        tvAtk.setText((attackBonus > 0 ? "+":"") + attackBonus + (distanceWeapon ? " " + weapon._distMin+"-"+weapon._distMax:"") + " x" + _character.getAttacksPerRound() );

        // Thrown?
        if (weapon._distance != Enumerations.WeaponDistanceTypes.THROWN) {
            rowThrown.setVisibility(View.GONE);
        }
        else {
            int attackBonusThrown = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;
            int dmgBonusThrown = abilityModifier + _character._dmgBonus + weapon._magicModifier;
            String damageThrown = weapon._diceCount + "D" + diceDamage + (dmgBonusThrown > 0 ? "+":"") + (dmgBonusThrown != 0 ? dmgBonusThrown : "")
                    + (weapon._damageType != null ? " (" + weapon._damageType.toString().substring(0,2) + ")" : "")
                    + propertyDamageBonus;

            tvAtkThrown.setText((attackBonusThrown > 0 ? "+":"") + attackBonusThrown + " " + weapon._distMin+"-"+weapon._distMax);
            tvDmgThrown.setText(damageThrown);
        }

        viewSpeed.setText(_character._race.getSpeedInFeet() + " ft.");
        int dexBonus = _character.getModifier(Enumerations.Attributes.DEX);
        viewInit.setText((dexBonus > 0 ? "+": "") + dexBonus);


        // Magic properties
        LinkedList<Fettle> magicProperties = weapon._magicProperties;
        if (magicProperties != null) {
            for (Fettle effect : magicProperties) {
                if (effect._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE
                        || effect._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER
                        || effect._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                    continue;
                }
                TableRow aRow = new TableRow(getContext());
                TextView effectDescription = new TextView(getContext());
                effectDescription.setText(effect.toString());
                aRow.addView(effectDescription);
                fettleTable.addView(aRow);
            }
        }

        refreshOffHand();
        refreshDamageMods();
    }

    public void refreshOffHand() {
        {
            Weapon weapon = _character._offHandWeapon;
            int modDex = _character.getModifier(Enumerations.Attributes.DEX);
            int modStr = _character.getModifier(Enumerations.Attributes.STR);


            if (weapon == null) {
                rowMeleeOffHand.setVisibility(View.GONE);
                rowNameOffHand.setVisibility(View.GONE);
                rowThrownOffHand.setVisibility(View.GONE);
                return;
            }
            else if (weapon._type == Enumerations.WeaponTypes.UNARMED) {
                rowMeleeOffHand.setVisibility(View.GONE);
                rowNameOffHand.setVisibility(View.GONE);
                rowThrownOffHand.setVisibility(View.GONE);
                return;
            }
            else {
                rowMeleeOffHand.setVisibility(View.VISIBLE);
                rowNameOffHand.setVisibility(View.VISIBLE);
                rowThrownOffHand.setVisibility(View.VISIBLE);
                weaponNameOffHand.setText(weapon._name);
            }

            boolean distanceWeapon = weapon._distance == Enumerations.WeaponDistanceTypes.DISTANCE;

            int abilityModifier = (distanceWeapon ? modDex : (weapon._isFinesse ? (Math.max(modDex, modStr)) : modStr));

            if (distanceWeapon) {
                imageWeaponHit.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_pocket_bow));
            }

            int propertyAttackBonus = 0;
            String propertyDamageBonus = "";
            for (Fettle property : _character.getFettles()) {
                if (property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                    propertyAttackBonus = Math.max(property._value, propertyAttackBonus);
                }
                else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER) {
                    propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._value + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
                }
                else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE) {
                    propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._valueStr + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
                }
            }

            int dmgBonus = _character._dmgBonus + weapon._magicModifier; // No bonus damage in the off hand
            int attackBonus = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;

            int diceDamage = weapon._diceValue;
            if (weapon._hands == Enumerations.WeaponHandCount.VERSATILE && _character._equippedShield._type == Enumerations.ArmorTypes.NONE) {
                diceDamage =  weapon._diceValueVersatile;
            }

            String damage = weapon._diceCount + "D" + diceDamage + (dmgBonus > 0 ? "+":"") + (dmgBonus != 0 ? dmgBonus : "")
                    + (weapon._damageType != null ? " (" + weapon._damageType.toString().substring(0,2) + ")" : "")
                    + propertyDamageBonus;
            tvDmgOffHand.setText(damage);
            tvAtkOffHand.setText((attackBonus > 0 ? "+":"") + attackBonus + (distanceWeapon ? " " + weapon._distMin+"-"+weapon._distMax:"") + " x" + _character.getAttacksPerRound() );

            // Thrown?
            if (weapon._distance != Enumerations.WeaponDistanceTypes.THROWN) {
                rowThrown.setVisibility(View.GONE);
            }
            else {
                int attackBonusThrown = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;
                int dmgBonusThrown = _character._dmgBonus + weapon._magicModifier;
                String damageThrown = weapon._diceCount + "D" + diceDamage + (dmgBonusThrown > 0 ? "+":"") + (dmgBonusThrown != 0 ? dmgBonusThrown : "")
                        + (weapon._damageType != null ? " (" + weapon._damageType.toString().substring(0,2) + ")" : "")
                        + propertyDamageBonus;

                tvAtkThrownOffHand.setText((attackBonusThrown > 0 ? "+":"") + attackBonusThrown + " " + weapon._distMin+"-"+weapon._distMax);
                tvDmgThrownOffHand.setText(damageThrown);
            }

            // Magic properties
            LinkedList<Fettle> magicProperties = weapon._magicProperties;
            if (magicProperties != null) {
                for (Fettle effect : magicProperties) {
                    if (effect._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE
                            || effect._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER
                            || effect._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                        continue;
                    }

                    TableRow aRow = new TableRow(getContext());
                    TextView effectDescription = new TextView(getContext());
                    effectDescription.setText(effect.toString());
                    aRow.addView(effectDescription);
                    fettleTableOffHand.addView(aRow);
                }
            }
        }
    }


    public void refreshDamageMods() {
        // Magic properties
        HashSet<Fettle> properties = _character.getFettles();
        damageModsTable.removeAllViews();

        boolean hide = true;
        for (Fettle effect : properties) {
            if (effect._type == Enumerations.FettleType.DAMAGE_RESISTANCE || effect._type == Enumerations.FettleType.DAMAGE_VULNERABILITY) {
                TableRow aRow = new TableRow(getContext());
                TextView effectDescription = new TextView(getContext());
                effectDescription.setText(effect.toString());
                aRow.addView(effectDescription);
                damageModsTable.addView(aRow);
                hide = false;
            }
        }
        rowDamageModsTitle.setVisibility(hide ? View.GONE : View.VISIBLE);
    }

    public void changeHP(View v) {
        int value = Integer.parseInt(v.getTag().toString());
        _character.changeHP(value);
        viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
        pb.setProgress(_character._hpCurrent);
    }

    public void changeHD(View v) {
        int value = Integer.parseInt(v.getTag().toString());
        _character.changeHD(value);
        viewHitDiceCurrent.setText(Integer.toString(_character._hitDice));
    }

    public void changeHDSecondary(View v) {
        int value = Integer.parseInt(v.getTag().toString());
        _character.changeHDSecondary(value);
        viewHitDiceCurrentSecondary.setText(Integer.toString(_character._hitDiceSecondary));
    }

    public void changeSpellSlot(View v) {
        String tags = (String)v.getTag();
        String[] tokens = tags.split(" ");
        int level = Integer.parseInt(tokens[0]);
        int value = Integer.parseInt(tokens[1]);

        _character.changeSpellSlot(level, value);
        spellSlotTextViews.get(level -1).setText(Integer.toString(_character._spellSlotsCurrent[level]));
    }


    class PowerListener implements View.OnClickListener {

        boolean isMinus;
        Power power;
        TextView tv;

        public PowerListener(boolean isMin, Power p, TextView view) {
            isMinus = isMin;
            power = p;
            tv = view;
        }

        @Override
        public void onClick(View v) {

            power._left += (isMinus ? -1 : 1);
            power._left = Math.min(power._left, power._max);
            power._left = Math.max(power._left, 0);

            tv.setText(power._left + "");
        }
    }
}
