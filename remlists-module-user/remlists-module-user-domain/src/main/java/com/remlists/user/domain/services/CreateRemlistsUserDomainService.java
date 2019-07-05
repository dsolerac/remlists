package com.remlists.user.domain.services;

import com.remlists.user.domain.entities.Role;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.exceptions.EmailAddressAlreadyExistsException;
import com.remlists.user.domain.exceptions.RoleNotFoundException;
import com.remlists.user.domain.exceptions.ShortNameAlreadyExistsException;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.RoleName;

import java.util.Optional;
import java.util.Set;

public class CreateRemlistsUserDomainService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoderDomainService encoder;


    public CreateRemlistsUserDomainService(UserRepository userRepository,
                                           RoleRepository roleRepository,
                                           PasswordEncoderDomainService encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User createAValidUser(User user) {


        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAddressAlreadyExistsException("This email address already exists in the system.");

        if (userRepository.findByShortName(user.getShortName()).isPresent())
            throw new ShortNameAlreadyExistsException("This shortname already exists in the system.");

        checkingValidityOfRolesToBeRelated(user.getRoles());

        relateCommonRoles(user);

        encodeUserPassword(user);

        return (User) userRepository.save(user);


    }

    private void checkingValidityOfRolesToBeRelated(Set<Role> roles) {

        if (! roles.isEmpty() ){

            Optional<Role> byRoleName;
            for (Role role: roles) {
                byRoleName = roleRepository.findByRoleName(role.getRole());
                byRoleName
                        .map(Role::getRole)
                        .orElseThrow( () -> new RoleNotFoundException("There are not any role with this name: " + role.getRole().getRole() + ". Please contact with your administrator." ) );
            }
        }

    }

    private void relateCommonRoles(User user){

        Set allByRoleName = roleRepository.findByRoleNameIn(new RoleName("USER"), new RoleName("USER_FREE"));
        user.setRoles(allByRoleName);

    }


    private void encodeUserPassword(User user) {
        user.setPassword( new Password( encoder.encode( user.getPassword().getPassword() ) ) );
    }





}
