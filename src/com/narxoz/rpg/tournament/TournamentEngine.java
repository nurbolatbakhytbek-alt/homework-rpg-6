package com.narxoz.rpg.tournament;

import com.narxoz.rpg.arena.ArenaFighter;
import com.narxoz.rpg.chain.*;
import com.narxoz.rpg.command.*;
import java.util.*;

public class TournamentEngine {
    private List<ArenaFighter> fighters;
    private List<ArenaFighter> opponents;
    private Map<ArenaFighter, DefenseHandler> defenseChains;
    private Random random;
    
    public TournamentEngine(List<ArenaFighter> fighters, List<ArenaFighter> opponents) {
        this.fighters = new ArrayList<>(fighters);
        this.opponents = new ArrayList<>(opponents);
        this.random = new Random();
        this.defenseChains = new HashMap<>();
        initializeDefenseChains();
    }
    
    private void initializeDefenseChains() {
        for (ArenaFighter fighter : fighters) {
            DefenseHandler dodge = new DodgeHandler(0.2);
            DefenseHandler block = new BlockHandler(0.3, 0.5);
            DefenseHandler armor = new ArmorHandler(5);
            DefenseHandler hp = new HpHandler();
            dodge.setNext(block);
            block.setNext(armor);
            armor.setNext(hp);
            defenseChains.put(fighter, dodge);
        }
        for (ArenaFighter opponent : opponents) {
            DefenseHandler armor = new ArmorHandler(3);
            DefenseHandler hp = new HpHandler();
            armor.setNext(hp);
            defenseChains.put(opponent, armor);
        }
    }
    
    public void runTournament() {
        System.out.println("\n🏆 GRAND ARENA TOURNAMENT 🏆");
        for (ArenaFighter fighter : fighters) {
            for (ArenaFighter opponent : opponents) {
                fighter.heal(fighter.getMaxHp());
                opponent.heal(opponent.getMaxHp());
                runBattle(fighter, opponent);
            }
        }
    }
    
    private void runBattle(ArenaFighter fighter, ArenaFighter opponent) {
        System.out.println("\n⚔️ BATTLE: " + fighter.getName() + " vs " + opponent.getName());
        ActionQueue queue = new ActionQueue();
        int round = 1;
        
        while (fighter.isAlive() && opponent.isAlive() && round <= 20) {
            System.out.println("\n--- Round " + round + " ---");
            if (random.nextBoolean()) {
                executeTurn(fighter, opponent, queue);
                if (opponent.isAlive()) executeTurn(opponent, fighter, queue);
            } else {
                executeTurn(opponent, fighter, queue);
                if (fighter.isAlive()) executeTurn(fighter, opponent, queue);
            }
            round++;
        }
        
        queue.executeAll();
        
        if (fighter.isAlive() && !opponent.isAlive()) System.out.println("\n🏆 " + fighter.getName() + " WINS!");
        else if (!fighter.isAlive() && opponent.isAlive()) System.out.println("\n🏆 " + opponent.getName() + " WINS!");
        else System.out.println("\n🤝 DRAW!");
    }
    
    private void executeTurn(ArenaFighter attacker, ArenaFighter defender, ActionQueue queue) {
        int action = random.nextInt(10);
        ActionCommand command;
        if (action < 6) command = new AttackCommand(attacker, defender);
        else if (action < 8) command = new HealCommand(attacker, attacker);
        else command = new DefendCommand(attacker);
        queue.enqueue(command);
    }
    
    public void demonstrateUndo() {
        System.out.println("\n🔄 UNDO DEMONSTRATION");
        ArenaFighter hero = fighters.get(0);
        ArenaFighter enemy = opponents.get(0);
        
        hero.heal(hero.getMaxHp());
        enemy.heal(enemy.getMaxHp());
        
        System.out.println("\nInitial: " + hero);
        System.out.println("Initial: " + enemy);
        
        AttackCommand attack = new AttackCommand(hero, enemy);
        HealCommand heal = new HealCommand(hero, hero);
        
        System.out.println("\nExecuting commands...");
        attack.execute();
        heal.execute();
        
        System.out.println("\nAfter execute: " + hero);
        System.out.println("After execute: " + enemy);
        
        System.out.println("\nUndoing heal...");
        heal.undo();
        
        System.out.println("\nAfter undo: " + hero);
        System.out.println("After undo: " + enemy);
    }
}
