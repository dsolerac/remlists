package com.remlists.list.read.application.commands;

import com.remlists.shared.application.commands.Command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class CreateNewRemListCommand implements Command {

    @NotEmpty
    private String name;
    @NotNull
    private UUID uuid;

    public CreateNewRemListCommand(UUID uuid, String name) {

        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "CreateNewRemListCommand{" +
                "name='" + name + '\'' +
                ", uuid=" + uuid +
                '}';
    }

}