package com.remlists.shared.application.commands;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface CommandHandler<T extends Command> {

    void execute(@Valid T command);

}
