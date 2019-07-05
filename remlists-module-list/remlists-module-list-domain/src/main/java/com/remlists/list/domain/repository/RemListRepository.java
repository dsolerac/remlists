package com.remlists.list.domain.repository;

import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.ValueObject;

import java.util.Optional;

public interface RemListRepository<E, ID> extends CrudRepository<E, ID> {

    <VO extends ValueObject> Optional<E> findByName(VO name);

}
