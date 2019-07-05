package com.remlists.list.read.infrastructure.spring.springData;

import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.read.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.IdJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListQueryDataRepository;
import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListRead;


@Repository(remListQueryDataRepository)
@Transactional(transactionManagerListRead)
public interface RemListQueryDataRepository extends JpaRepository<RemListJPA, IdJPA>,
                                                    RemListRepository<RemListJPA, IdJPA> {


}
