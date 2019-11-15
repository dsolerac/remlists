package com.remlists.user.write.integration.repository;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.*;
import org.assertj.core.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;
import java.util.UUID;

@Tag("IntegrationTest")
@DisplayName("Integration for User Repository")
@SpringBootTest
public class UserRepositoryTest {

    private Logger LOG = LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    @Qualifier("userWriteRepository")
    private UserRepository userRepo;

    @Autowired
    @Qualifier("roleWriteRepository")
    private RoleRepository roleRepo;


    @DisplayName("VALID TESTS")
    @Nested
    class UserRepositoryTest_ValidTests {

        @Test
        @DisplayName("create a user")
        void createAValidUser(){

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("ddd");
            EmailAddress email = new EmailAddress("ddd@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            //When
            userRepo.save(user);

            //Then
            Optional byId = userRepo.findById(id);
            Assertions.assertThat(byId.get()).isEqualTo(user);

        }

        @Test
        @DisplayName("find User By Short Name")
        void findUserByShortName() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("xxx");
            EmailAddress email = new EmailAddress("xxx@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            userRepo.save(user);

            //When
            Optional<User> userFound = userRepo.findByShortName(sname);

            //Then
            Assertions.assertThat(userFound).isNotEmpty();
            Assertions.assertThat(userFound.get()).isEqualTo(user);

        }

        @Test
        @DisplayName("find User By Email")
        void findUserByEmail() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("xxx3");
            EmailAddress email = new EmailAddress("xxx3@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            userRepo.save(user);

            //When
            Optional<User> userFound = userRepo.findByEmail(email);

            //Then
            Assertions.assertThat(userFound).isNotEmpty();
            Assertions.assertThat(userFound.get()).isEqualTo(user);

        }

        @Test
        @DisplayName("find User By Short Name And Email")
        void findUserByShortNameAndEmail() {

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("xxx2");
            EmailAddress email = new EmailAddress("xxx2@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            userRepo.save(user);

            //When
            Optional<User> userFound = userRepo.findByEmailOrShortName(email, sname);

            //Then
            Assertions.assertThat(userFound).isNotEmpty();
            Assertions.assertThat(userFound.get()).isEqualTo(user);

        }

        @Test
        @DisplayName("Save a relationship between user and role")
        void createUserWithRoleRelationship(){

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("aaa");
            EmailAddress email = new EmailAddress("aaa@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            Id id_role = new Id(UUID.randomUUID());
            RoleName rname = new RoleName("ROLE_SUPER_VISOR");
            RoleDescription description = new RoleDescription("Rol de supervisor");

            Role role = new Role(id_role, rname);
            role.setDescription(description);

            //When
            Role rolePersisted = roleRepo.save(role);

            user.setArrayRoles(rolePersisted);
            User userPersisted = (User) userRepo.save(user);

            //Then
            Assertions.assertThat( userPersisted.getRoles() ).containsExactly(role);
            Assertions.assertThat( userPersisted ).isEqualTo(user);

        }

        @Test
        @DisplayName("find User with roles associated By Short Name And Email")
        void findUserByShortNameAndEmailWithRolesAssociated(){

            //Given
            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("aaabb");
            EmailAddress email = new EmailAddress("aaabb@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email,password);

            Id id_role = new Id(UUID.randomUUID());
            RoleName rname = new RoleName("ROLE_SUPER_VISOR2");
            RoleDescription description = new RoleDescription("Rol de supervisor");

            Role role = new Role(id_role, rname);
            role.setDescription(description);

            Role rolePersisted = roleRepo.save(role);

            user.setArrayRoles(rolePersisted);
            User userPersisted = (User) userRepo.save(user);

            //When
//            Optional<User> userFound = userRepo.findByEmailOrShortName(email, sname);
            Optional<User> userFound = userRepo.findByShortName(sname);




        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class UserRepositoryTest_FailTests {

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
