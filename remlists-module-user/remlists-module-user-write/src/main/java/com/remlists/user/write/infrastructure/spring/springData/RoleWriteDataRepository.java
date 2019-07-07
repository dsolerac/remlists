package com.remlists.user.write.infrastructure.spring.springData;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.domain.repository.RoleRepository;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import com.remlists.user.write.infrastructure.jpa.repository.RoleRepositoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.roleWriteDataRepository;
import static com.remlists.user.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerUserWrite;

@Repository(roleWriteDataRepository)
@Transactional(transactionManagerUserWrite)
public interface RoleWriteDataRepository extends JpaRepository <RoleJPA, IdJPA>,
                                                 RoleRepositoryJPA{


}
