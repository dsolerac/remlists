package com.remlists.user.write.integration.repository;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import org.assertj.core.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Tag("IntegrationTest")
@DisplayName("Integration for role repository")
@SpringBootTest
public class RoleRepositoryTest {

    private Logger LOG = LoggerFactory.getLogger(RoleRepositoryTest.class);

    @Autowired
    @Qualifier("roleWriteRepository")
    private RoleRepository roleRepo;


    @Autowired
    @Qualifier("userWriteRepository")
    private UserRepository userRepo;




    @DisplayName("VALID TESTS")
    @Nested
    class RoleRepositoryTest_ValidTests {

        @Test
        @DisplayName("create a valid role")
        void createValidRole() {

            //Given
            Id id_role = new Id(UUID.randomUUID());
            RoleName rname = new RoleName("SUPER_VISOR");
            RoleDescription description = new RoleDescription("Rol de supervisor");

            Role role = new Role(id_role, rname);
            role.setDescription(description);

            //When
            roleRepo.save(role);

            //Then
            Optional byId = roleRepo.findById(id_role);
            Assertions.assertThat(byId).isNotEmpty();
            Assertions.assertThat(byId.get()).isEqualTo(role);


        }

        @Test
        @DisplayName("Check if exits a role ")
        void checkIfExitsRole() {

            //Given
            Id id_role = new Id(UUID.randomUUID());
            RoleName rname = new RoleName("ROLE_SUPER_VISOR3");
            RoleDescription description = new RoleDescription("Rol de supervisor");

            Role role = new Role(id_role, rname);
            role.setDescription(description);

//            roleRepo.save(role);


            Id id_user = new Id(UUID.randomUUID());
            ShortName name = new ShortName("name1");
            EmailAddress email = new EmailAddress("name1@gg.com");
            Password password = new Password("pass");

            User user = new User(id_user, name, email, password );

            user.setArrayRoles(role);
//            userRepo.save(user);

            role.setArrayUsers(user);
            roleRepo.save(role);


            //When
            Optional<Role> byRoleName = roleRepo.findByRoleName(rname);

            //Then
            Assertions.assertThat(byRoleName).isNotEmpty();
            Assertions.assertThat(byRoleName.get()).isEqualTo(role);



        }

        @Test
        @DisplayName("Check if exits several roles at same time")
        void checkIfExitsSeveralRoles() {

            //Given
            RoleName name1 = new RoleName("ROLE_USER");
            RoleName name2 = new RoleName("ROLE_USER_FREE");

            //When
            Set allByRoleName = roleRepo.findByRoleNameIn(name1, name2);


            //Then
            Assertions.assertThat(allByRoleName).hasSize(2);



        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class RoleRepositoryTest_FailTests {

        @Test
        @DisplayName("fail test")
        void fail() {

            //Given

            //When

            //Then
//            assertThat();
        }
    }

}
