package com.remlists.list.write.infrastructure.jpa.impl;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.write.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.write.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.list.write.infrastructure.jpa.valueObjects.RemListNameJPA;
import com.remlists.list.write.infrastructure.mapper.RemListMapper;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.repository.impl.RemListBaseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.remListMapperWrite;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listWriteRepositoryJPA;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.remListDataCustomRepositoryImpl;
import static com.remlists.list.write.infrastructure.spring.BeanNames.Infrastructure.Spring.transactionManagerListWrite;


@Repository(listWriteRepositoryJPA)
@Transactional(transactionManagerListWrite)
public class RemListRepositoryImplRemList extends RemListBaseRepository< RemList,
                                                                 Id,
                                                                 RemListJPA,
                                                                 IdJPA>
                                        implements RemListRepository<RemList, Id> {

    private RemListRepository repository;
    private RemListMapper mapper;


    public RemListRepositoryImplRemList(@Qualifier(remListDataCustomRepositoryImpl) RemListRepository repository,
                                        @Qualifier(remListMapperWrite) RemListMapper mapper) {

        super( repository, mapper );

        this.repository = repository;
        this.mapper = mapper;

    }


    @Override
    public <VO extends ValueObject> Optional<RemList> findByName(VO name) {

        RemListNameJPA nameJPA = mapper.getMapper().map(name, RemListNameJPA.class);

        Optional<RemListJPA> byName = repository.<RemListNameJPA>findByName(nameJPA);


        if(byName.isEmpty())
            return Optional.empty();

        return  Optional.of( mapper.getMapper().map(byName.orElseThrow(), RemList.class) );
    }
}
