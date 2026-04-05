package com.narxoz.rpg.chain;

import com.narxoz.rpg.arena.ArenaFighter;
import java.util.Random;

public class BlockHandler extends DefenseHandler {
    private Random random = new Random();
    private double blockChance;
    private double blockReduction;
    
    public BlockHandler(double blockChance, double blockReduction) {
        this.blockChance = blockChance;
        this.blockReduction = blockReduction;
    }
    
    @Override
    public int handle(ArenaFighter defender, int damage) {
        if (random.nextDouble() < blockChance) {
            int reducedDamage = (int)(damage * (1 - blockReduction));
            System.out.println(defender.getName() + " BLOCKS! " + damage + " -> " + reducedDamage);
            return reducedDamage;
        }
        if (next != null) return next.handle(defender, damage);
        return damage;
    }
}
