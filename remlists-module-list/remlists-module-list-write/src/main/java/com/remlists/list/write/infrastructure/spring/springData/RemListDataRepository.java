package com.remlists.list.write.infrastructure.spring.springData;

import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.write.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.write.infrastructure.jpa.valueObjects.IdJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListDataRepository;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListWrite;

@Repository(remListDataRepository)
@Transactional(transactionManagerListWrite)
public interface RemListDataRepository extends  JpaRepository<RemListJPA, IdJPA>,
                                                RemListRepository<RemListJPA, IdJPA> {



}
