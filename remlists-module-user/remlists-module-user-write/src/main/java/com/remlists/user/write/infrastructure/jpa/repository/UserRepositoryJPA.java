package com.remlists.user.write.infrastructure.jpa.repository;

import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailAddressJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;

import java.util.Optional;

public interface UserRepositoryJPA extends CrudRepository<UserJPA, IdJPA> {

    Optional<UserJPA> findByShortName(ShortNameJPA username);
    Optional<UserJPA> findByEmail(EmailAddressJPA email);
    Optional<UserJPA> findByEmailOrShortName(EmailAddressJPA email, ShortNameJPA username);

}
