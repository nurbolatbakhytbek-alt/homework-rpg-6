package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public class DefendCommand implements ActionCommand {
    private ArenaFighter actor;
    private ArenaFighter target;
    private int defenseBonus;
    private boolean executed;
    
    public DefendCommand(ArenaFighter actor) {
        this.actor = actor;
        this.target = actor;
        this.defenseBonus = 10;
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) return;
        actor.addDefenseBonus(defenseBonus);
        executed = true;
        System.out.println(actor.getName() + " defends! Defense +" + defenseBonus);
    }
    
    @Override
    public void undo() {
        if (!executed) return;
        actor.removeDefenseBonus(defenseBonus);
        executed = false;
        System.out.println("Undo: " + actor.getName() + "'s defense removed");
    }
    
    @Override
    public ArenaFighter getActor() { return actor; }
    
    @Override
    public ArenaFighter getTarget() { return target; }
}
