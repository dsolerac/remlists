package com.remlists.user.write.infrastructure.mapper;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserRolesJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.userMapperWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;


@Component(userMapperWrite)
public class UserMapper extends MapperBase {

    private Logger LOG = LoggerFactory.getLogger(UserMapper.class);



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
            return new NoSuitableEntityException("This entity must be a User");

        User user = (User) entity;

        Set<RoleJPA> rolesJPA = null;
        Set<Role> roles = null;


        if (!user.getRoles().isEmpty()){

            roles = user.getRoles();

            rolesJPA = super.iterableEntitiesToIterableEntitiesJPA2(roles, new TypeToken<Set<RoleJPA>>() {}.getType() );

            user.setRoles(Set.of()); //Limpio la entidad para hacer el mapeo de la entidad User sin problemas,
                                     //porque luego se los añadiré por medio del método addRole()

        }

        TypeMap<UserJPA, User> userJPAUserTypeMap = mapper.createTypeMap(UserJPA.class, User.class)
                    .addMappings(mapper -> mapper.skip(User::setRoles));


        UserJPA userJPA = (UserJPA) super.<UserJPA, User>entityToEntityJPA2(user, UserJPA.class);

        //En ese punto se meten los roles por medio del método addRole que mantiene las relaciones de muchos a muchos.
        rolesJPA.stream().forEach(x -> userJPA.addRole(x));

        //Se devuevle el objeto a su estado original para no alteralo.
        user.setRoles(roles);

        return userJPA;
    }

    @Override
    @Transactional(transactionManagerUserWrite)
    public Object entityJPAToEntity(Object entityJPA) {

        if (!(entityJPA instanceof UserJPA))
            return new NoSuitableEntityException("This entity must be a UserJPA");

        UserJPA userJPA = (UserJPA)entityJPA;

        User user = (User)super.<User, UserJPA>entityJPAToEntity2(userJPA, User.class);

        return user;
    }


    @Override
    @Transactional(transactionManagerUserWrite)
    public Optional entityJPAToOptionalEntity(Optional entityJPA) {

        UserJPA userJPA = (UserJPA) entityJPA.orElseThrow();

        Set<RoleJPA> rolesJPA = userJPA.getRoles().stream()
                                                .map(UserRolesJPA::getRole)
                                                .collect(Collectors.toSet());

        mapper.getTypeMap(RoleJPA.class, Role.class)
                .addMappings(mapper -> mapper.map(x -> {return Set.of();}, Role::setUsers ) )
                .addMappings(mapper -> mapper.map(RoleJPA::getId, Role::setId) );

        Set<Role> roles = super.<Role, RoleJPA>iterableEntitiesJPAToIterableEntities2(  rolesJPA,
                                                                            new TypeToken<Set<Role>>() {}.getType());


        Optional<User> user = super.<UserJPA, User>entityJPAToOptionalEntity2(entityJPA, User.class );
        user.ifPresent(x -> x.setRoles(roles));

        return user;

    }









//    -------------

    //TODO: si funciona moverlo a una excepción de domnio
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