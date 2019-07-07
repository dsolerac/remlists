package com.remlists.user.write.infrastructure.mapper;

import com.remlists.shared.domain.valueObjects.CreatedAt;
import com.remlists.shared.domain.valueObjects.UpdatedAt;
import com.remlists.shared.infrastructure.jpa.valueObjects.CreatedAtJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.UpdatedAtJPA;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.EmailVerified;
import com.remlists.user.domain.valueObjects.RoleName;
import com.remlists.user.domain.valueObjects.ShortName;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserRolesJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailAddressJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailVerifiedJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


@Mapper(componentModel = "spring")
public interface RoleMapperStruct  {

    Logger LOG = LoggerFactory.getLogger(RoleMapperStruct.class);

    RoleMapperStruct INSTANCE = Mappers.getMapper( RoleMapperStruct.class );



    Set<RoleNameJPA> rolesNameToRolesNameJPA( Set<RoleName> rolesName );

    @IterableMapping(qualifiedByName="roleJPAToRole_withUsers")
    Set<Role> rolesJPAToRoles(Set<RoleJPA> rolesJPA);

    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "verified.verified", target = "verified.verified"),
            @Mapping(source = "updatedAt.updatedAt", target = "updatedAt.updatedAt"),
            @Mapping(source = "createdAt.createdAt", target = "createdAt.createdAt"),
            @Mapping(source = "shortName.shortName", target = "shortName.shortName"),
            @Mapping(source = "email.emailAddress", target = "email.emailAddress"),
            @Mapping(source = "password.password", target = "password.password"),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "arrayRoles", ignore = true)
    })
    User userJPAToUser(UserJPA userJPA);




    /*==== NAMED ====*/

    @Named("roleJPAToRole_withoutUsers")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "roleName.role", target = "role.role"),
            @Mapping(source = "description.description", target = "description.description"),
            @Mapping(target = "users", ignore = true)
    })
    Role roleJPAToRole(RoleJPA roleJPA);

    @Named("roleJPAToRole_withUsers")
    @Mappings({
            @Mapping(source = "id.uuid", target = "id.uuid"),
            @Mapping(source = "roleName.role", target = "role.role"),
            @Mapping(source = "description.description", target = "description.description")
    })
    Role roleJPAToRole_withUsers(RoleJPA roleJPA);


    @Mappings({
            @Mapping(source = "user.id", target = "id"),
            @Mapping(source = "user.verified", target = "verified"),
            @Mapping(source = "user.updatedAt", target = "updatedAt"),
            @Mapping(source = "user.createdAt", target = "createdAt"),
            @Mapping(source = "user.shortName", target = "shortName"),
            @Mapping(source = "user.email", target = "email"),
            @Mapping(source = "user.password", target = "password"),
            @Mapping(target = "roles", ignore = true),
            @Mapping(target = "arrayRoles", ignore = true)
    })
    User userRolesJPAToUser(UserRolesJPA userRole);






    /* ==== Value Objects ====*/

    ShortName ShortNameToShortNameJPA(ShortNameJPA shortNameJPA);
    EmailAddress emailAddressToEmailAddressJPA(EmailAddressJPA emailAddressJPA);
    EmailVerified emailVerifiedToEmailVerifiedJPA(EmailVerifiedJPA emailVerifiedJPA);
    UpdatedAt updatedAtToUpdatedAtJPA(UpdatedAtJPA updatedAtJPA);
    CreatedAt createdAtToCreatedAtJPA(CreatedAtJPA createdAtJPA);
    RoleNameJPA roleNameToRoleNameJPA(RoleName roleName);


}
