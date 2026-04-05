package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class DodgeHandler extends DefenseHandler {
    private Random random = new Random();
    private double dodgeChance;
    
    public DodgeHandler(double dodgeChance) { this.dodgeChance = dodgeChance; }
    
    @Override
    public int handle(ArenaFighter defender, int damage) {
        if (random.nextDouble() < dodgeChance) {
            System.out.println(defender.getName() + " DODGES!");
            return 0;
        }
        if (next != null) return next.handle(defender, damage);
        return damage;
    }
}
