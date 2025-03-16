package com.willyan.handler;

import com.willyan.domain.command.Command;

public interface CommandHandler<C extends Command> {
    
    void handle(C command);
}
