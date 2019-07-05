package com.remlists.entrypoint.controller.user;

import com.remlists.user.domain.entities.User;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class RetrieveUserController {

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
//        return service.findAll();
        return null;
    }


    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {

//        User user = service.findOne(id);

//        if(user==null)
//            throw new UserNotFoundException("id-"+ id);


        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
//        Resource<User> resource = new Resource<User>(user);

//        ControllerLinkBuilder linkTo =
//                linkTo(ControllerLinkBuilder.methodOn( this.getClass() ).retrieveAllUsers());

//        resource.add(linkTo.withRel("all-users"));

        //HATEOAS

//        return resource;
        return null;
    }


}
