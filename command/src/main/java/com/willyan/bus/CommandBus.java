package com.willyan.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.willyan.domain.command.Command;
import com.willyan.handler.CommandHandler;

import ch.qos.logback.core.util.StringUtil;

@Component
public class CommandBus {

    @Autowired
    private ApplicationContext context;

    public void execute(final Command command) {
        try {
            String handlerBeanName = StringUtil.lowercaseFirstLetter(command.getClass().getSimpleName() + "Handler");
            CommandHandler handler = (CommandHandler) context.getBean(handlerBeanName);
            handler.handle(command);

        } catch (Exception e) {
            throw new RuntimeException("Command handler execution failed", e);
        }
    }
}
