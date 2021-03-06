package com.remlists.user.write.infrastructure.spring.springData;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.entities.UserJPA;
import com.remlists.user.write.infrastructure.jpa.repository.UserRepositoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.userWriteDataRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;

@Repository(userWriteDataRepository)
@Transactional(transactionManagerUserWrite)
public interface UserWriteDataRepository extends JpaRepository <UserJPA, IdJPA>,
                                                 UserRepositoryJPA {


}
