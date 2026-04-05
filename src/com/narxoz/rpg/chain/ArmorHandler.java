package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class ArmorHandler extends DefenseHandler {
    private int armorValue;
    
    public ArmorHandler(int armorValue) { this.armorValue = armorValue; }
    
    @Override
    public int handle(ArenaFighter defender, int damage) {
        int reducedDamage = Math.max(0, damage - armorValue);
        if (armorValue > 0 && reducedDamage < damage) {
            System.out.println("Armor: " + damage + " -> " + reducedDamage);
        }
        if (next != null) return next.handle(defender, reducedDamage);
        return reducedDamage;
    }
}
