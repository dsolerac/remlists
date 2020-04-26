package com.remlists.user.domain.services;

import com.remlists.user.domain.entities.RoleGroup;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.exceptions.EmailAddressAlreadyExistsException;
import com.remlists.user.domain.exceptions.ShortNameAlreadyExistsException;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.domain.repository.UserRepository;
import com.remlists.user.domain.valueObjects.Password;

public class CreateBasicRemlistsUserDomainService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoderDomainService encoder;


    public CreateBasicRemlistsUserDomainService(UserRepository userRepository,
                                                RoleRepository roleRepository,
                                                PasswordEncoderDomainService encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public User createAValidUser(User user) {


        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAddressAlreadyExistsException("This email address is already registered in the system.");

        if (userRepository.findByShortName(user.getShortName()).isPresent())
            throw new ShortNameAlreadyExistsException("This shortname is already registered in the system.");

        ensureNewUserHasRoleUser(user);

        encodeUserPassword(user);

        return userRepository.save(user);

    }

    private void ensureNewUserHasRoleUser(User user) {
        user.addRoleGroup(RoleGroup.createBasicUSerRoleGroup() );
    }

    private void encodeUserPassword(User user) {
        user.setPassword( new Password( encoder.encode( user.getPassword() ) ) );
    }

}
