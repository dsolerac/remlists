package com.remlists.user.write.application.commands;

import com.remlists.shared.application.commands.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class RegistryUserCommand  implements Command {

    private Logger LOG = LoggerFactory.getLogger(RegistryUserCommand.class);

    @NotNull
    private UUID uuid;

    @NotBlank
    private String shortName;

    @Email(message = "{EmailAddress.emailAddress.Email}")
    private String emailAddress;

    private String password;

    public RegistryUserCommand(@NotNull UUID uuid,
                               @NotBlank String shortName,
                               @NotBlank String emailAddress,
                               String password ) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.emailAddress = emailAddress;
        this.password = password;
    }


    public UUID getUuid() {
        return uuid;
    }

    public String getShortName() {
        return shortName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "RegistryUserCommand{" +
                "uuid=" + uuid +
                ", shortName='" + shortName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password=" + password +
                '}';
    }
}
