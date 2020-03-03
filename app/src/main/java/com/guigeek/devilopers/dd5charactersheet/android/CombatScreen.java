package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.fighter.Fighter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.fighter.Fighter_gunslinger;
import com.guigeek.devilopers.dd5charactersheet.character.classes.rogue.Rogue_swashbuckler;
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
    TextView viewHPCurrent, viewTempHP, viewHPMax, viewHitDiceCurrent, viewHitDiceMax, tvAC, tvAtk, tvDmg, tvHitDiceDesc, tvHitDiceDescSec, tvAtkThrown, tvDmgThrown;
    TextView viewHitDiceCurrentSecondary, viewHitDiceMaxSecondary;
    TextView viewSpeed, viewInit, spellAtk, spellDD, weaponName, weaponNameOffHand, tvFirearmDesc, tvFirearmDescOffhand, tvReloadMin, tvReloadMax, tvReloadMinOffhand, tvReloadMaxOffhand;
    ImageView imageWeaponHit, imageWeaponHitOffhand;
    TableRow rowThrown, rowThrownOffHand, rowNameOffHand, rowMeleeOffHand, rowDamageModsTitle, rowFirearm, rowFirearmOffhand;
    ProgressBar pb;
    TableRow rowSecondaryHD;

    LinearLayout failRow, successRow;
    TableLayout allTables;

    TextView tvAtkOffHand, tvDmgOffHand, tvAtkThrownOffHand, tvDmgThrownOffHand;

    List<TextView> spellSlotTextViews;
    TableLayout fettleTable, damageModsTable, fettleTableOffHand;

    Button btnRevive;
    Button btnReloadMin, btnReloadPlus, btnReloadMinOffhand, btnReloadPlusOffhand;

    TableRow rowSpecial;
    TableLayout tableSpecial;

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

    @Override
    public void onResume() {
        super.onResume();
        refreshSheet();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        View rootView = inflater.inflate(R.layout.fragment_combat, container, false);
        viewHPCurrent = (TextView)rootView.findViewById(R.id.tvHPCurrent);
        viewTempHP = (TextView)rootView.findViewById(R.id.tvTempHP);
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

        rowSpecial = rootView.findViewById(R.id.combatRowSpecialAttacks);
        tableSpecial = rootView.findViewById(R.id.combatTableSpecialAttacks);

        Class aSpellCasterClass = _character._class;
        if (!aSpellCasterClass.isCaster() && _character._secondaryClass != null) {
            aSpellCasterClass = _character._secondaryClass;
        }

        spellDD.setText(Integer.toString(8 + _character.getProficiencyBonus() + _character.getModifier(aSpellCasterClass.getMainSpellAttribute())));
        spellAtk.setText("+" + Integer.toString(_character.getProficiencyBonus() + _character.getModifier(aSpellCasterClass.getMainSpellAttribute())));


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
        imageWeaponHitOffhand = (ImageView)rootView.findViewById(R.id.imgCombatHitBonusOffHand);
        rowThrown = (TableRow)rootView.findViewById(R.id.combatRowThrown);

        rowThrownOffHand = (TableRow)rootView.findViewById(R.id.combatRowThrownOffHand);
        rowNameOffHand = (TableRow)rootView.findViewById(R.id.rowNameOffHand);
        rowMeleeOffHand = (TableRow)rootView.findViewById(R.id.rowMeleeOffHand);
        rowDamageModsTitle = (TableRow)rootView.findViewById(R.id.rowDamageModsTitle);


        if (!aSpellCasterClass.isCaster()) {
            TableRow spellRow = (TableRow)rootView.findViewById(R.id.rowSpell);
            spellRow.setVisibility(View.GONE);
        }

        fettleTable = (TableLayout)rootView.findViewById(R.id.combatWeaponProperties);
        fettleTableOffHand = (TableLayout)rootView.findViewById(R.id.combatWeaponPropertiesOffHand);
        damageModsTable = (TableLayout)rootView.findViewById(R.id.combatTableDamageMods);

        // Did he dieded?
        failRow = (LinearLayout)rootView.findViewById(R.id.failedSavesLayout);
        successRow = (LinearLayout)rootView.findViewById(R.id.successSavesLayout);
        allTables = (TableLayout)rootView.findViewById(R.id.tablelayout);
        btnRevive = (Button)rootView.findViewById(R.id.btnRevive);
        btnRevive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                failRow.setVisibility(View.GONE);
                successRow.setVisibility(View.GONE);
                allTables.setVisibility(View.VISIBLE);
                _character.changeHP(1);
                viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
                viewTempHP.setText(Integer.toString(_character._hpTemp));
                pb.setProgress(_character._hpCurrent);
            }
        });

        if (_character._hpCurrent > 0) {
            failRow.setVisibility(View.GONE);
            successRow.setVisibility(View.GONE);
            allTables.setVisibility(View.VISIBLE);
        }
        else {
            failRow.setVisibility(View.VISIBLE);
            successRow.setVisibility(View.VISIBLE);
            allTables.setVisibility(View.GONE);
        }


        rowFirearm = rootView.findViewById(R.id.combatRowFirearm);
        rowFirearmOffhand = rootView.findViewById(R.id.combatRowFirearmOffhand);
        tvFirearmDesc  = rootView.findViewById(R.id.tvFirearmDesc);
        tvFirearmDescOffhand = rootView.findViewById(R.id.tvFirearmDescOffhand);
        tvReloadMin = rootView.findViewById(R.id.tvReloadMin);
        tvReloadMax = rootView.findViewById(R.id.tvReloadMax);
        tvReloadMinOffhand = rootView.findViewById(R.id.tvReloadMinOffhand);
        tvReloadMaxOffhand = rootView.findViewById(R.id.tvReloadMaxOffhand);
        btnReloadMin = rootView.findViewById(R.id.btnReloadMin);
        btnReloadPlus = rootView.findViewById(R.id.btnReloadPlus);
        btnReloadMinOffhand = rootView.findViewById(R.id.btnReloadMinOffhand);
        btnReloadPlusOffhand = rootView.findViewById(R.id.btnReloadPlusOffhand);


        addButtonListener(rootView);
        createFettlesBar(rootView);
        createSpellBars(rootView);
        createSpecialPowerBars(rootView, "Class Features", _character.getClassPowers());
        createSpecialPowerBars(rootView, "Racial Features", _character._race.getRacialFeatures(_character));
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
        View.OnClickListener tempHpList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTempHP(v);
            }
        };

        rootView.findViewById(R.id.btnHPMin1).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPPlus1).setOnClickListener(hpList);

        rootView.findViewById(R.id.btnHDMin1).setOnClickListener(hdList);
        rootView.findViewById(R.id.btnHDPlus1).setOnClickListener(hdList);

        rootView.findViewById(R.id.btnHDMin1Secondary).setOnClickListener(hdListSecondary);
        rootView.findViewById(R.id.btnHDPlus1Secondary).setOnClickListener(hdListSecondary);

        rootView.findViewById(R.id.btnTempHPMin).setOnClickListener(tempHpList);


        btnReloadMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvReloadMin.setText("" + Math.max(0, Integer.parseInt(tvReloadMin.getText().toString()) -1));
            }
        });
        btnReloadPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvReloadMin.setText("" + Math.min(Integer.parseInt(tvReloadMax.getText().toString()), Integer.parseInt(tvReloadMin.getText().toString()) +1));
            }
        });

        btnReloadMinOffhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvReloadMinOffhand.setText("" + Math.max(0, Integer.parseInt(tvReloadMinOffhand.getText().toString()) -1));
            }
        });
        btnReloadPlusOffhand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvReloadMinOffhand.setText("" + Math.min(Integer.parseInt(tvReloadMaxOffhand.getText().toString()), Integer.parseInt(tvReloadMinOffhand.getText().toString()) +1));
            }
        });
    }




    private void createFettlesBar(View root) {
        if (!_character.getCharacterFettles().isEmpty()) {
            TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

            // Title
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText("Passive effects");
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 6;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);

            spellSlotTextViews = new ArrayList<TextView>();
            for (Fettle fettle : _character.getCharacterFettles()) {
                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView description = new TextView(getContext());
                description.setText(fettle.toString());
                TableRow.LayoutParams paramRow = new TableRow.LayoutParams();
                paramRow.span = 6;
                description.setLayoutParams(paramRow);

                row.addView(description);
                ll.addView(row);
            }
        }
    }



    private void createSpellBars(View root) {
        if (_character._class.isCaster() || _character._secondaryClass != null && _character._secondaryClass.isCaster()) {
            TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

            // Title
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText("Spell slots by level");
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 6;
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
                level.setText("Spell level " + Integer.toString(i));
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
                level.setLayoutParams(rowParam);

                TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                buttonParams.width = 50;
                buttonParams.height = 120;
                minus.setLayoutParams(buttonParams);
                plus.setLayoutParams(buttonParams);

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
            paramsSaves.span = 6;
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
            rowParamName.span = 3;
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

                TableRow rowUsage= new TableRow(getContext());

                TextView aLongRestShortRestTV = new TextView(getContext());
                aLongRestShortRestTV.setText(power._isLongRest ? "per Long rest" : "per Short Rest");
                aLongRestShortRestTV.setLayoutParams(rowParam);

                TableRow.LayoutParams buttonParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                buttonParams.width = 50;
                buttonParams.height = 120;
                minus.setLayoutParams(buttonParams);
                plus.setLayoutParams(buttonParams);

                rowUsage.addView(aLongRestShortRestTV);
                rowUsage.addView(current);
                rowUsage.addView(max);
                rowUsage.addView(minus);
                rowUsage.addView(plus);

                rowUsage.setLayoutParams(lp);
                ll.addView(rowUsage);
            }

            TableRow.LayoutParams rowParamDesc = new TableRow.LayoutParams();
            rowParamDesc.span = 6;
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
        viewTempHP.setText(Integer.toString(_character._hpTemp));
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

        imageWeaponHit.setImageDrawable(this.getContext().getResources().getDrawable(Weapon.getWeaponIcon(weapon)));

        int propertyAttackBonus = 0;
        String propertyDamageBonus = "";

        // Character-wide bonuses
        for (Fettle property : _character.getCharacterFettles()) {
            if (property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                propertyAttackBonus += propertyAttackBonus;
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._value + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._valueStr + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
        }
        // Weapon-specific bonuses
        for (Fettle property : weapon._magicProperties) {
            if (property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                propertyAttackBonus += propertyAttackBonus;
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._value + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
            else if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE) {
                propertyDamageBonus += " " + (property._value >= 0 ? "+" : "") + property._valueStr + "(" + Enumerations.DamageTypes.values()[property._describer].toString() + ")";
            }
        }

        // Fighting styles
        boolean hasArchery = _character.hasPower("[Fighting Style] Archery");
        boolean hasDueling = _character.hasPower("[Fighting Style] Dueling");

        int abilityModifier = (distanceWeapon ? modDex : (finesseWeapon ? (Math.max(modDex, modStr)) : modStr));

        int dmgBonus = abilityModifier +  _character._dmgBonus + weapon._magicModifier;
        int attackBonus = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;

        if (hasArchery && distanceWeapon) {
            attackBonus += 2;
        }

        if (hasDueling && !distanceWeapon && weapon._hands != Enumerations.WeaponHandCount.TWO_HANDED && (_character._offHandWeapon == null || _character._offHandWeapon._type == Enumerations.WeaponTypes.UNARMED)) {
            dmgBonus += 2;
        }

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



        if (weapon._isFirearm) {
            rowFirearm.setVisibility(View.VISIBLE);
            tvFirearmDesc.setText("Reload: " + weapon._reload + ", misfire: " + weapon._misfire);

            String reload = Integer.toString(weapon._reload);
            tvReloadMin.setText(reload);
            tvReloadMax.setText(reload);
        } else {
            rowFirearm.setVisibility(View.GONE);
        }

        viewSpeed.setText(_character.getSpeedInFeet() + " ft.");
        int dexBonus = _character.getModifier(Enumerations.Attributes.DEX);

        int initiativeBonus = dexBonus;
        if (_character._class instanceof Rogue_swashbuckler && _character._level >= 3) {
            initiativeBonus += _character.getModifier(Enumerations.Attributes.CHA);
        }
        else if (_character._secondaryClass != null && _character._secondaryClass instanceof Rogue_swashbuckler && _character._levelSecondaryClass >= 3) {
            initiativeBonus += _character.getModifier(Enumerations.Attributes.CHA);
        }
        else if (_character._class instanceof Fighter && _character._level >= 7 && _character._class.getArchetype(0) instanceof Fighter_gunslinger) {
            initiativeBonus += _character.getProficiencyBonus();
        }

        viewInit.setText((initiativeBonus > 0 ? "+": "") + initiativeBonus);

        refreshOffHand();
        refreshDamageMods();
        addClassAttacks();
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
                rowFirearmOffhand.setVisibility(View.GONE);
                return;
            }
            else if (weapon._type == Enumerations.WeaponTypes.UNARMED) {
                rowMeleeOffHand.setVisibility(View.GONE);
                rowNameOffHand.setVisibility(View.GONE);
                rowThrownOffHand.setVisibility(View.GONE);
                rowFirearmOffhand.setVisibility(View.GONE);
                return;
            }
            else {
                rowMeleeOffHand.setVisibility(View.VISIBLE);
                rowNameOffHand.setVisibility(View.VISIBLE);
                rowThrownOffHand.setVisibility(View.VISIBLE);
                weaponNameOffHand.setText(weapon.toString());
            }

            boolean distanceWeapon = weapon._distance == Enumerations.WeaponDistanceTypes.DISTANCE;

            int abilityModifier = (distanceWeapon ? modDex : (weapon._isFinesse ? (Math.max(modDex, modStr)) : modStr));

            imageWeaponHitOffhand.setImageDrawable(this.getContext().getResources().getDrawable(Weapon.getWeaponIcon(weapon)));


            if (weapon._isFirearm) {
                rowFirearmOffhand.setVisibility(View.VISIBLE);
                tvFirearmDescOffhand.setText("Reload: " + weapon._reload + ", misfire: " + weapon._misfire);

                String reload = Integer.toString(weapon._reload);
                tvReloadMinOffhand.setText(reload);
                tvReloadMaxOffhand.setText(reload);
            } else {
                rowFirearmOffhand.setVisibility(View.GONE);
            }

            int propertyAttackBonus = 0;
            String propertyDamageBonus = "";
            // Character-wide bonuses
            for (Fettle property : _character.getCharacterFettles()) {
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
            // Weapon-specific bonuses
            for (Fettle property : weapon._magicProperties) {
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
            if(_character.hasPower("[Fighting Style] Two-Weapon Fighting")) {
                dmgBonus += abilityModifier;
            }
            int attackBonus = _character.getProficiencyBonus() + abilityModifier + weapon._magicModifier + propertyAttackBonus;

            int diceDamage = weapon._diceValue;
            if (weapon._hands == Enumerations.WeaponHandCount.VERSATILE && _character._equippedShield._type == Enumerations.ArmorTypes.NONE) {
                diceDamage =  weapon._diceValueVersatile;
            }

            String damage = weapon._diceCount + "D" + diceDamage + (dmgBonus > 0 ? "+":"") + (dmgBonus != 0 ? dmgBonus : "")
                    + (weapon._damageType != null ? " (" + weapon._damageType.toString().substring(0,2) + ")" : "")
                    + propertyDamageBonus;
            tvDmgOffHand.setText(damage);
            tvAtkOffHand.setText((attackBonus > 0 ? "+":"") + attackBonus + (distanceWeapon ? " " + weapon._distMin+"-"+weapon._distMax:"") );

            // Thrown?
            if (weapon._distance != Enumerations.WeaponDistanceTypes.THROWN) {
                rowThrownOffHand.setVisibility(View.GONE);
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

    public void addClassAttacks() {
        tableSpecial.removeAllViews();
        LinkedList<Attack> allSpecials = new LinkedList<>();
        allSpecials.addAll(_character._class.getAllSpecialClassAttacks(_character, _character._level));
        if (_character._secondaryClass != null) {
            allSpecials.addAll(_character._secondaryClass.getAllSpecialClassAttacks(_character, _character._levelSecondaryClass));
        }

        if (allSpecials.size() > 0) {
            rowSpecial.setVisibility(View.VISIBLE);

            for (Attack atk: allSpecials) {
                TableRow aRowName = new TableRow(getContext());

                TextView weaponName = new TextView(getContext());
                weaponName.setText(atk._weapon._name);

                if (atk._icon != -1) {
                    ImageView aIcon = new ImageView(getContext());
                    aIcon.setImageDrawable(getContext().getDrawable(atk._icon));
                    aRowName.addView(aIcon);
                }

                aRowName.addView(weaponName);
                tableSpecial.addView(aRowName);

                // Description
                if (atk._description != null) {
                    TableRow rowDesc = new TableRow(getContext());
                    TextView textDesc = new TextView(getContext());

                    TableRow.LayoutParams rowParamNameDesc = new TableRow.LayoutParams();
                    rowParamNameDesc.span = 6;
                    textDesc.setLayoutParams(rowParamNameDesc);

                    rowDesc.addView(textDesc);
                    tableSpecial.addView(rowDesc);
                    textDesc.setText(atk._description);
                }

                tableSpecial.addView(createWeaponAttackRow(atk));
            }
        } else {
            rowSpecial.setVisibility(View.GONE);
        }
    }

    public TableRow createWeaponAttackRow(Attack iAtk) {
        TableRow aRow = new TableRow(getContext());

        ImageView meleeOrDist = new ImageView(getContext());
        ImageView dmg = new ImageView(getContext());
        TextView atkDesc = new TextView(getContext());
        TextView dmgDesc = new TextView(getContext());

        aRow.addView(meleeOrDist);
        aRow.addView(atkDesc);
        aRow.addView(dmg);
        aRow.addView(dmgDesc);

        meleeOrDist.setImageDrawable(getContext().getDrawable(iAtk._weapon._distance == Enumerations.WeaponDistanceTypes.DISTANCE ? R.drawable.ic_thrown_daggers : R.drawable.ic_sword_clash));
        dmg.setImageDrawable(getContext().getDrawable(R.drawable.ic_broken_heart));

        atkDesc.setText((iAtk._attackMod > 0 ? "+" + iAtk._attackMod : iAtk._attackMod) + " " + (iAtk._atkCount > 1 ? "x" + iAtk._atkCount:""));

        String dmgDescText = iAtk._weapon._diceCount + "D" + iAtk._weapon._diceValue;
        if (iAtk._dmgMod != 0) {
            dmgDescText += (iAtk._dmgMod != 0 ? iAtk._dmgMod > 0 ? " +" + iAtk._dmgMod: " " + iAtk._dmgMod : "") + " ";
        }
        dmgDescText += iAtk._weapon._damageType.toString();

        dmgDesc.setText(dmgDescText);

        return aRow;
    }




    public void refreshDamageMods() {
        // Magic properties
        HashSet<Fettle> properties = _character.getCharacterFettles();
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
        final int value = Integer.parseInt(v.getTag().toString());

        // Amount?
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText text = new EditText(getActivity());
        text.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setTitle(value > 0 ? "Add HP":"Remove HP").setMessage(value > 0 ? "HP healed:" : "Damage taken:").setView(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
                final String amountStr = text.getText().toString();
                try {
                    int amount = Integer.parseInt(amountStr);
                    _character.changeHP(value * amount);
                    viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
                    viewTempHP.setText(Integer.toString(_character._hpTemp));
                    pb.setProgress(_character._hpCurrent);

                    if (_character._hpCurrent > 0) {
                        failRow.setVisibility(View.GONE);
                        successRow.setVisibility(View.GONE);
                        allTables.setVisibility(View.VISIBLE);
                    }
                    else {
                        failRow.setVisibility(View.VISIBLE);
                        successRow.setVisibility(View.VISIBLE);
                        allTables.setVisibility(View.GONE);
                    }

                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Invalid amount", Toast.LENGTH_SHORT).show();
                    text.setText("");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
            }
        });
        builder.create().show();
    }

    public void changeTempHP(View v) {
        // Amount?
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText text = new EditText(getActivity());
        text.setInputType(InputType.TYPE_CLASS_NUMBER);

        builder.setTitle("Gain temporary HP").setMessage("Temporary HP:").setView(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
                final String amountStr = text.getText().toString();
                try {
                    int amount = Integer.parseInt(amountStr);
                    _character.adjustTemporaryHP(amount);
                    viewTempHP.setText(Integer.toString(_character._hpTemp));
                }
                catch (Exception e) {
                    Toast.makeText(getContext(), "Invalid amount", Toast.LENGTH_SHORT).show();
                    text.setText("");
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface di, int i) {
            }
        });
        builder.create().show();
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
