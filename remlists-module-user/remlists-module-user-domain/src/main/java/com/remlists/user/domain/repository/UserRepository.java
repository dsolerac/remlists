package com.remlists.user.domain.repository;


import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;

import java.util.Optional;


public interface UserRepository<E, ID> extends CrudRepository<E, ID> {


    <VO extends ValueObject> Optional<E> findByShortName(VO username);
    <VO extends ValueObject> Optional<E> findByEmail(VO email);
    <VO extends ValueObject> Optional<E> findByEmailOrShortName(VO email, VO username);


}
