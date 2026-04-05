package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class AttackCommand implements ActionCommand {
    private ArenaFighter actor;
    private ArenaFighter target;
    private int damageDealt;
    private boolean executed;
    
    public AttackCommand(ArenaFighter actor, ArenaFighter target) {
        this.actor = actor;
        this.target = target;
        this.damageDealt = 0;
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) return;
        int damage = actor.getAttack();
        damageDealt = target.takeDamage(damage);
        executed = true;
        System.out.println(actor.getName() + " attacks " + target.getName() + " for " + damageDealt + " damage!");
    }
    
    @Override
    public void undo() {
        if (!executed) return;
        target.heal(damageDealt);
        executed = false;
        System.out.println("Undo: " + actor.getName() + "'s attack reversed");
    }
    
    @Override
    public ArenaFighter getActor() { return actor; }
    
    @Override
    public ArenaFighter getTarget() { return target; }
}
