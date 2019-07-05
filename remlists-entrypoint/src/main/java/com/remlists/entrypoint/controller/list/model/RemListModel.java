package com.remlists.entrypoint.controller.list.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remlists.shared.domain.validation.UUID;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel(description = "All details about the remlist")
public class RemListModel {

    @NotEmpty
    @ApiModelProperty(notes = "Name of the remlist")
    private String name;

    @UUID
    @NotBlank
    @ApiModelProperty(notes = "Remlist's Id in UUID format")
    private String uuid;

    public RemListModel(String uuid, String name) {

        this.uuid = uuid;
        this.name = name;
    }

    public RemListModel() {
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return "RemListModel{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String toJSON() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(this);

    }

}
