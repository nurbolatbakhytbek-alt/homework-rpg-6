package com.narxoz.rpg.arena;

public class ArenaOpponent extends ArenaFighter {
    private String type;
    
    public ArenaOpponent(String name, int maxHp, int attack, int healPower, String type) {
        super(name, maxHp, attack, healPower);
        this.type = type;
    }
    
    @Override
    public String getClassType() { return type; }
}
