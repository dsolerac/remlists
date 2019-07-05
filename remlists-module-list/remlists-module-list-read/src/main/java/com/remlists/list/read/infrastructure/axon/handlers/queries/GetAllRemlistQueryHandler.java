package com.remlists.list.read.infrastructure.axon.handlers.queries;

import com.remlists.list.read.application.queries.GetAllRemListsQuery;
import com.remlists.list.read.application.services.GetAllRemListsService;
import com.remlists.list.domain.entities.RemList;
import com.remlists.list.read.infrastructure.axon.handlers.commands.CreateNewRemlistCommandHandler;
import com.remlists.shared.application.queries.IterableQueryHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class GetAllRemlistQueryHandler implements IterableQueryHandler<RemList, GetAllRemListsQuery> {


    private Logger LOG = LoggerFactory.getLogger(CreateNewRemlistCommandHandler.class);


    private GetAllRemListsService getAllRemListsService;


    public GetAllRemlistQueryHandler(GetAllRemListsService getAllRemListsService) {
        this.getAllRemListsService = getAllRemListsService;
    }

    @QueryHandler
    @Override
    public Iterable<RemList> execute(@Valid GetAllRemListsQuery query) {

        return getAllRemListsService.getAllRemLists();

    }

}
