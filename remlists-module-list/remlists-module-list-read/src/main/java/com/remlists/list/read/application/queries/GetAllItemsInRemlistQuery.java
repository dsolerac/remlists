package com.remlists.list.read.application.queries;

import com.remlists.shared.application.queries.Query;

import javax.validation.constraints.NotEmpty;

public final class GetAllItemsInRemlistQuery implements Query {

    @NotEmpty
    private String remlistName;

    public GetAllItemsInRemlistQuery(String remlistName) {
        this.remlistName = remlistName;
    }

    public String getRemlistName() {
        return remlistName;
    }

    @Override
    public String toString() {
        return "GetAllItemsInRemlistQuery{" +
                "remlistName='" + remlistName + '\'' +
                '}';
    }
}
