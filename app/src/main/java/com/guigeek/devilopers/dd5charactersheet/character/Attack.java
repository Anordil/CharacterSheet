package com.guigeek.devilopers.dd5charactersheet.character;

import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

public class Attack {
    public Weapon _weapon;
    public int _attackMod, _dmgMod, _atkCount;
    public int _icon;

    public String _description;

    public Attack(Weapon w, String des, int atk, int dmg, int atkCount, int ic) {
        _weapon = w;
        _attackMod = atk;
        _dmgMod = dmg;
        _atkCount = atkCount;
        _icon = ic;
        _description = des;
    }
}
