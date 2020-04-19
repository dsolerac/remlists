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
import java.util.Set;
import java.util.UUID;

@Tag("IntegrationTest|ApplicationServiceTest|EntityTest|DomainServiceTest|ValueObjectsTest")
@DisplayName("Integration |Application Service |Entity |Domain Service |Value Object for .....")
@SpringBootTest
//@Transactional(transactionManagerUserWrite)
public class UserRoleRepositoryTest {

    private Logger LOG = LoggerFactory.getLogger(UserRoleRepositoryTest.class);

    @Autowired
    @Qualifier("userWriteRepository")
    private UserRepository userRepo;

    @Autowired
    @Qualifier("roleWriteRepository")
    private RoleRepository roleRepo;


    @DisplayName("VALID TESTS")
    @Nested
    class UserRoleRepositoryTest_ValidTests {

        @Test
        @DisplayName("create valid user and two new roles and a relationship between them.")
        void createAValidUserWithRoles(){

            //Given
            Id id_role = new Id(UUID.randomUUID());
            RoleName rname = new RoleName("SUPER_VISOR");
            Role role = new Role(id_role, rname);
            role.setDescription(new RoleDescription("bla bla bla bla"));


            Id id_role2 = new Id(UUID.randomUUID());
            RoleName rname2 = new RoleName("ADMIN");
            Role role2 = new Role(id_role2, rname2);
            role2.setDescription(new RoleDescription("bla bla bla bla"));

            roleRepo.saveAll(Set.of(role, role2));



            Id id = new Id(UUID.randomUUID());
            ShortName sname = new ShortName("ddd");
            EmailAddress email = new EmailAddress("ddd@gm.com");
            Password password = new Password("pass");

            User user = new User(id, sname, email, password);

            user.setArrayRoles(role, role2);


            //When
            userRepo.save(user);


            //Then
            Optional<User> byId = userRepo.findById(user.getId());
            Assertions.assertThat(byId.get()).isEqualToComparingFieldByFieldRecursively(user);


            System.out.println();
            System.out.println("User -->" + user.getRoles().stream().findFirst().get().getRoleName());
            System.out.println();
            System.out.println("UserById -->" + byId.get().getRoles().stream().findFirst().get().getRoleName());
            System.out.println();

        }

    }

    @DisplayName("FAILED TESTS")
    @Nested
    class UserRoleRepositoryTest_FailTests {

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
