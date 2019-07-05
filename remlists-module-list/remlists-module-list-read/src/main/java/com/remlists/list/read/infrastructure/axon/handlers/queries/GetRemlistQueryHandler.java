package com.remlists.list.read.infrastructure.axon.handlers.queries;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.exceptions.RemlistNotFoundException;
import com.remlists.list.read.application.queries.GetRemlistQuery;
import com.remlists.list.read.application.services.GetRemListService;
import com.remlists.list.read.infrastructure.axon.handlers.commands.CreateNewRemlistCommandHandler;
import com.remlists.shared.application.queries.SimpleQueryHandler;
import com.remlists.shared.domain.valueObjects.Id;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.UUID;

@Component
public class GetRemlistQueryHandler implements SimpleQueryHandler<RemList, GetRemlistQuery> {


    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistCommandHandler.class);


    private GetRemListService getRemListService;


    public GetRemlistQueryHandler(GetRemListService getRemListService) {
        this.getRemListService = getRemListService;
    }

    @QueryHandler
    @Override
    public RemList execute(@Valid GetRemlistQuery query) {
        Id id = new Id(UUID.fromString(query.getRemlistId()));


        RemList remList = getRemListService.getRemList(id).orElseThrow( () -> new RemlistNotFoundException("There aren't any Remlist with this ID: " + id.getUuid())  );

        return remList;
    }



}
