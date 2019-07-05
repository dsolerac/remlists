package com.remlists.list.read.application.queries;

import com.remlists.shared.application.queries.Query;
import com.remlists.shared.domain.validation.UUID;

import javax.validation.constraints.NotEmpty;

public final class GetRemlistQuery implements Query {

    @NotEmpty
    @UUID
    private String remlistId;

    public GetRemlistQuery(String remlistId) {
        this.remlistId = remlistId;
    }

    public String getRemlistId() {
        return remlistId;
    }

    @Override
    public String toString() {
        return "GetRemlistQuery{" +
                "remlistId='" + remlistId + '\'' +
                '}';
    }
}
