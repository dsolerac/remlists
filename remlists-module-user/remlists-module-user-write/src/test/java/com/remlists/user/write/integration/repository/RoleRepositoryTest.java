package com.remlists.user.write.integration.repository;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.valueObjects.RoleDescription;
import com.remlists.user.domain.valueObjects.RoleName;
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
    @Qualifier("roleWriteRepositoryJPA")
    private RoleRepository repo;


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
            repo.save(role);

            //Then
            Optional byId = repo.findById(id_role);
            Assertions.assertThat(byId).isNotEmpty();
            Assertions.assertThat(byId.get()).isEqualTo(role);


        }

        @Test
        @DisplayName("Check if exits several roles at same time")
        void checkIfExitsSeveralRoles() {

            //Given
            RoleName name1 = new RoleName("USER");
            RoleName name2 = new RoleName("USER_FREE");

            //When
            Set allByRoleName = repo.findByRoleNameIn(name1, name2);




            //Then
//            Optional byId = repo.findById(id_role);
//            Assertions.assertThat(byId).isNotEmpty();
//            Assertions.assertThat(byId.get()).isEqualTo(role);


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
