package com.narxoz.rpg.command;

import java.util.*;

public class ActionQueue {
    private Queue<ActionCommand> queue;
    private Stack<ActionCommand> history;
    
    public ActionQueue() {
        this.queue = new LinkedList<>();
        this.history = new Stack<>();
    }
    
    public void enqueue(ActionCommand command) {
        queue.offer(command);
        System.out.println("Enqueued: " + command.getClass().getSimpleName());
    }
    
    public void executeAll() {
        while (!queue.isEmpty()) {
            ActionCommand command = queue.poll();
            command.execute();
            history.push(command);
        }
    }
    
    public void undoLast() {
        if (history.isEmpty()) {
            System.out.println("Nothing to undo!");
            return;
        }
        ActionCommand lastCommand = history.pop();
        lastCommand.undo();
    }
    
    public void undoAll() {
        while (!history.isEmpty()) {
            undoLast();
        }
    }
}
