package com.narxoz.rpg;

import com.narxoz.rpg.arena.*;
import com.narxoz.rpg.tournament.TournamentEngine;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("🎮 RPG GRAND ARENA TOURNAMENT 🎮");
        
        List<ArenaFighter> fighters = Arrays.asList(
            new ArenaOpponent("Aragorn", 120, 25, 10, "Warrior"),
            new ArenaOpponent("Legolas", 100, 30, 5, "Archer"),
            new ArenaOpponent("Gandalf", 90, 20, 25, "Wizard")
        );
        
        List<ArenaFighter> opponents = Arrays.asList(
            new ArenaOpponent("Orc Chieftain", 110, 28, 0, "Orc"),
            new ArenaOpponent("Dark Elf", 95, 32, 8, "Elf"),
            new ArenaOpponent("Troll", 150, 22, 0, "Troll")
        );
        
        System.out.println("\nHEROES:");
        fighters.forEach(System.out::println);
        System.out.println("\nOPPONENTS:");
        opponents.forEach(System.out::println);
        
        TournamentEngine tournament = new TournamentEngine(fighters, opponents);
        tournament.runTournament();
        tournament.demonstrateUndo();
        
        System.out.println("\n✅ DONE!");
    }
}
