package com.remlists.list.write.application.commands;

import com.remlists.shared.application.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class CreateNewRemListCommand implements Command {

    private Logger LOG = LoggerFactory.getLogger(CreateNewRemListCommand.class);


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