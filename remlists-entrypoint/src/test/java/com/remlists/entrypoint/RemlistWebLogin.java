package com.remlists.entrypoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemlistWebLogin {

    String username;
    String password;

    public RemlistWebLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "RemlistWebLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String toJSON() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);

    }

}
