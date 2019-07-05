package com.remlists.entrypoint.controller.list.resource;

import com.remlists.entrypoint.controller.list.GetRemListsController;
import com.remlists.list.domain.entities.RemList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class RemListsResourceAssembler extends ResourceAssemblerSupport<RemList, RemListResource> {

    public RemListsResourceAssembler() {
        super(GetRemListsController.class, RemListResource.class);
    }

    public RemListResource toResource(RemList remList) {

        RemListResource remListResource = createResourceWithId(remList.getId().getUuid(), remList); // adds a "self" link
        // TODO: copy properties from RemList to UserResource
        remListResource.setID(remList.getId());
        remListResource.setName(remList.getName());

        return remListResource;
    }
}
