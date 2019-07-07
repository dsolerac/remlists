package com.remlists.user.write.infrastructure.mapper;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.roleMapperWrite;


@Component(roleMapperWrite)
public class RoleMapper extends MapperBase {

    private Logger LOG = LoggerFactory.getLogger(RoleMapper.class);


    private RoleMapperStruct mstruct;

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





    public Set<RoleNameJPA> rolesNameToRolesNameJPA( RoleName... rolesName ){

        Set<RoleNameJPA> rolesNameJPA = mstruct.INSTANCE.rolesNameToRolesNameJPA(Set.of((RoleName [])rolesName));

        return rolesNameJPA;
    }


    public Set<Role> rolesJPAToRoles(Set<RoleJPA> rolesJPA){

        Set<Role> roles = mstruct.INSTANCE.rolesJPAToRoles(rolesJPA);

/*        Set roles = new HashSet();

        Set<UserRolesJPA> userRolesJPAS;
        UserJPA userJPA;
        Role role;
        User user;
        for (RoleJPA roleJPA: rolesJPA) {

            role = mstruct.INSTANCE.roleJPAToRole(roleJPA);

            userRolesJPAS = roleJPA.getUsers();


            for (UserRolesJPA userRoleJPA: userRolesJPAS) {

                userJPA = userRoleJPA.getUser();

                user = mstruct.INSTANCE.userJPAToUser_withoutRoles(userJPA);

                role.setArrayUsers(user);

            }

            roles.add(role);

        }*/

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