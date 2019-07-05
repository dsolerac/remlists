package com.remlists.entrypoint.controller.list;

import com.remlists.entrypoint.controller.list.resource.RemListResource;
import com.remlists.entrypoint.controller.list.resource.RemListsResourceAssembler;
import com.remlists.list.domain.entities.RemList;
import com.remlists.list.read.application.queries.GetAllRemListsQuery;
import com.remlists.list.read.application.queries.GetRemlistQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "api/lists")
public class GetRemListsController {

    private Logger LOG = LoggerFactory.getLogger(GetRemListsController.class);


    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public ResponseEntity<List<RemListResource>> getAllAvailableLists() {

        GetAllRemListsQuery allRemLists = new GetAllRemListsQuery();

        List<RemList> allLists = queryGateway.query(allRemLists,
                ResponseTypes.multipleInstancesOf(RemList.class)).join();

        RemListsResourceAssembler rra = new RemListsResourceAssembler();
        ResponseEntity<List<RemListResource>> re = new ResponseEntity( rra.toResources(allLists), HttpStatus.ACCEPTED );

        return re;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemListResource> getList(@PathVariable("id") String id ) {

        GetRemlistQuery query = new GetRemlistQuery(id);

        RemList remList = queryGateway.query( query, ResponseTypes.instanceOf( RemList.class ) ).join();

        RemListsResourceAssembler rra = new RemListsResourceAssembler();
        ResponseEntity<RemListResource> re = new ResponseEntity(rra.toResource(remList), HttpStatus.ACCEPTED);

        return re;

    }


}