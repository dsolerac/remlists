package com.remlists.user.domain.repository;


import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.user.domain.entities.User;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.ShortName;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Id> {


    Optional<User> findByShortName(ShortName username);
    Optional<User> findByEmail(EmailAddress email);
    Optional<User> findByEmailOrShortName(EmailAddress email, ShortName username);


}
