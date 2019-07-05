package com.remlists.entrypoint.controller.user.resource;

import com.remlists.user.domain.entities.User;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class UserResourceAssembler<T> extends ResourceAssemblerSupport<User, UserResource> {

    public UserResourceAssembler(Class<T> controllerClass) {
//        super(GetRemListsController.class, UserResource.class);
        super(controllerClass, UserResource.class);
    }

    public UserResource toResource(User user) {

        UserResource userResource = createResourceWithId(user.getId().getUuid(), user); // adds a "self" link
        // TODO: copy properties from RemList to UserResource
        userResource.setID(user.getId());
        userResource.setShortName(user.getShortName());


        return userResource;
    }
}
