package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;

public class HpHandler extends DefenseHandler {
    @Override
    public int handle(ArenaFighter defender, int damage) {
        int actualDamage = defender.takeDamage(damage);
        if (actualDamage > 0) {
            System.out.println(defender.getName() + " takes " + actualDamage + " damage! HP: " + 
                             defender.getCurrentHp() + "/" + defender.getMaxHp());
        }
        return actualDamage;
    }
}
