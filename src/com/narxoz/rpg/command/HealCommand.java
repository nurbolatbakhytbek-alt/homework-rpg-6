package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class HealCommand implements ActionCommand {
    private ArenaFighter actor;
    private ArenaFighter target;
    private int healAmount;
    private int actualHeal;
    private boolean executed;
    
    public HealCommand(ArenaFighter actor, ArenaFighter target) {
        this.actor = actor;
        this.target = target;
        this.healAmount = actor.getHealPower();
        this.actualHeal = 0;
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) return;
        int oldHp = target.getCurrentHp();
        target.heal(healAmount);
        actualHeal = target.getCurrentHp() - oldHp;
        executed = true;
        System.out.println(actor.getName() + " heals " + target.getName() + " for " + actualHeal + " HP!");
    }
    
    @Override
    public void undo() {
        if (!executed) return;
        target.takeDamage(actualHeal);
        executed = false;
        System.out.println("Undo: " + actor.getName() + "'s heal reversed");
    }
    
    @Override
    public ArenaFighter getActor() { return actor; }
    
    @Override
    public ArenaFighter getTarget() { return target; }
}
