package com.remlists.user.write.infrastructure.mapper;

import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserRolesJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import org.h2.engine.Setting;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.Converter;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.roleMapperWrite;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.userMapperWrite;


@Component(roleMapperWrite)
public class RoleMapper extends MapperBase {

    private Logger LOG = LoggerFactory.getLogger(RoleMapper.class);



    private ModelMapper mapper;

    public RoleMapper(ModelMapper mapper) {
        super(  mapper,
                Role.class,
                RoleJPA.class,
                IdJPA.class,
                new TypeToken<List<Role>>() {}.getType(),
                new TypeToken<List<RoleJPA>>() {}.getType(),
                new TypeToken<List<IdJPA>>() {}.getType()
        );

        this.mapper = mapper;

    }



    public RoleNameJPA roleNameToRoleNameJPA(RoleName roleName){

        return mapper.map(roleName, RoleNameJPA.class);

    }

    public Optional<Role> roleJPAToRole(Optional<RoleJPA> roleJPA){

        if(roleJPA.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.map(roleJPA.orElseThrow(), Role.class) );
    }


    public Set<RoleNameJPA> rolesNameToRolesNameJPA( Set<?> rolesName ){
        return iterableEntitiesToIterableEntitiesJPA2( rolesName, new TypeToken<Set<RoleNameJPA>>() {}.getType());
    }

    public Set<Role> rolesJPAToRoles(Set<RoleJPA> rolesJPA){

        Set<Role> roles = new HashSet<>();


        Set<UserJPA> usersJpa;
        Set<User> users = new HashSet<>();

        Role role;
        for (RoleJPA roleJPA: rolesJPA) {

            LOG.info("@@@ 1 -->" + roleJPA);

            usersJpa=roleJPA.getUsers().stream()
                    .map(UserRolesJPA::getUser)
                    .peek(x->x.setRoles(Set.of()))
                    .collect(Collectors.toSet());


            mapper.createTypeMap( UserJPA.class, User.class )
//                     .addMappings(mapper -> mapper.map(x -> {return Set.of();}, User::setRoles ) )
                     .addMappings(mapper -> mapper.skip(User::setRoles));


            for (UserJPA userJPA: usersJpa) {

                users.add((User) entityJPAToEntity2(userJPA, User.class));

            }

//            users = iterableEntitiesJPAToIterableEntities2(usersJpa, new TypeToken<Set<User>>() {}.getType());



//            roleJPA.setUsers(null);
            role = (Role) entityJPAToEntity2(roleJPA, Role.class);

            role.setUsers(users);
            roles.add(role);

        }

        return roles;
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