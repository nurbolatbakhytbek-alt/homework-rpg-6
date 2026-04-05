package com.narxoz.rpg.arena;

public abstract class ArenaFighter {
    protected String name;
    protected int maxHp;
    protected int currentHp;
    protected int attack;
    protected int healPower;
    protected int defenseBonus;
    
    public ArenaFighter(String name, int maxHp, int attack, int healPower) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attack = attack;
        this.healPower = healPower;
        this.defenseBonus = 0;
    }
    
    public String getName() { return name; }
    public int getCurrentHp() { return currentHp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getHealPower() { return healPower; }
    
    public void addDefenseBonus(int bonus) { this.defenseBonus += bonus; }
    public void removeDefenseBonus(int bonus) { this.defenseBonus = Math.max(0, this.defenseBonus - bonus); }
    
    public int takeDamage(int damage) {
        int reducedDamage = Math.max(0, damage - defenseBonus);
        int actualDamage = Math.min(currentHp, reducedDamage);
        currentHp -= actualDamage;
        defenseBonus = 0;
        return actualDamage;
    }
    
    public void heal(int amount) {
        currentHp = Math.min(maxHp, currentHp + amount);
    }
    
    public boolean isAlive() { return currentHp > 0; }
    public abstract String getClassType();
    
    public String toString() {
        return String.format("%s [%s] HP: %d/%d", name, getClassType(), currentHp, maxHp);
    }
}
