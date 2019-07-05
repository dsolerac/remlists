package com.remlists.entrypoint.controller.user.model;

import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.write.application.commands.RegistryUserCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserModel {

    private Logger LOG = LoggerFactory.getLogger(RegistryUserCommand.class);

    @com.remlists.shared.domain.validation.UUID
    private String uuid;

    @NotBlank
    private String shortName;

    @Email(message = "{EmailAddress.emailAddress.Email}")
    private String emailAddress;

    @NotBlank
    private String password;

    private Set<String> roles;


    public UserModel(String uuid,
                     @NotBlank String shortName,
                     @Email(message = "{EmailAddress.emailAddress.Email}") String emailAddress,
                     @NotBlank String password ) {
        this.uuid = uuid;
        this.shortName = shortName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    protected UserModel() {
    }

    public String getUuid() {
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
        return "UserModel{" +
                "uuid='" + uuid + '\'' +
                ", shortName='" + shortName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
