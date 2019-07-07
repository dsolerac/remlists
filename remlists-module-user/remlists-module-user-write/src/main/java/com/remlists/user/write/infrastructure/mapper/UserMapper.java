package com.remlists.user.write.infrastructure.mapper;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.userMapperWrite;


@Component(userMapperWrite)
public class UserMapper extends MapperBase {

    private Logger LOG = LoggerFactory.getLogger(UserMapper.class);


    private UserMapperStruct mstruct;

    private ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        super(  mapper,
                User.class,
                UserJPA.class,
                IdJPA.class,
                new TypeToken<List<User>>() {}.getType(),
                new TypeToken<List<UserJPA>>() {}.getType(),
                new TypeToken<List<IdJPA>>() {}.getType()
        );

        this.mapper = mapper;

    }


    @Override
    public Object entityToEntityJPA(Object entity) {

        if (!(entity instanceof User))
            throw new NoSuitableEntityException("This entity must be a User");

        User user = (User) entity;

        UserJPA userJPA = mstruct.INSTANCE.userToUserJPA_withoutRoles(user);
        if (user.getRoles().isEmpty())
            return userJPA;


        Set<Role> roles = user.getRoles();

        RoleJPA roleJPA;
        for (Role role: roles) {

            roleJPA = mstruct.INSTANCE.roleToRoleJPA(role);

            userJPA.addRole(roleJPA);

        }


        return userJPA;

    }

    @Override
    public Object entityJPAToEntity(Object entityJPA) {

        if (!(entityJPA instanceof UserJPA))
            return new NoSuitableEntityException("This entity must be a UserJPA");

        UserJPA userJPA = (UserJPA)entityJPA;

        if (userJPA.getRoles().isEmpty())
            return mstruct.INSTANCE.userJPAToUser_withoutRoles(userJPA);

        return mstruct.INSTANCE.userJPAToUser_withRoles(userJPA);

    }


    @Override
    public Optional entityJPAToOptionalEntity(Optional entityJPA) {

        if(entityJPA.isEmpty())
            return Optional.<User>empty();

        UserJPA userJPA = (UserJPA) entityJPA.get();

        User user = null;
        if (userJPA.getRoles().isEmpty())
            user = mstruct.INSTANCE.userJPAToUser_withoutRoles(userJPA);


        return Optional.<User>of(user);

    }









//    -------------

    //TODO: si funciona moverlo a una excepción de dominio
    private class NoSuitableEntityException extends RuntimeException {
        public NoSuitableEntityException(String s) {
            super(s);
        }
    }
}

    /*

https://blog.codefx.org/java/casting-in-java-8-and-beyond/

        TypeMap<Installation, DeviceTokenCurator> typeMap = mapper.createTypeMap(Installation.class, DeviceTokenCurator.class)
                .addMappings(mapper -> mapper.skip(DeviceTokenCurator::setId))
                .addMappings(mapper -> mapper.skip(DeviceTokenCurator::setCreatedAt))
                .addMappings(mapper -> mapper.map(x -> {return jobId;}, DeviceTokenCurator::setJobId))
                .addMappings(mapper -> mapper.map(x -> {return "PENDING";}, DeviceTokenCurator::setPushSentStatus));

        Type listType = new TypeToken<List<DeviceTokenCurator>>() {}.getType();
        List<DeviceTokenCurator> devicesCurator = mapper.map( installations, listType );

     */