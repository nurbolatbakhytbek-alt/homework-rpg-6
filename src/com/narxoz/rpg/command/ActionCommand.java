package com.narxoz.rpg.command;

import com.narxoz.rpg.arena.ArenaFighter;

public interface ActionCommand {
    void execute();
    void undo();
    ArenaFighter getActor();
    ArenaFighter getTarget();
}