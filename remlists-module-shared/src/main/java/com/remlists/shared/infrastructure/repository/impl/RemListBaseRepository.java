package com.remlists.shared.infrastructure.repository.impl;

import com.remlists.shared.domain.repository.CrudRepository;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public abstract class RemListBaseRepository<E,
                                        ID extends Id,
                                        E_JPA,
                                        ID_JPA>{

    private Logger LOG = LoggerFactory.getLogger(RemListBaseRepository.class);


    private CrudRepository<E_JPA,ID_JPA> repository;
    private MapperBase mapper;


    public RemListBaseRepository(CrudRepository<E_JPA, ID_JPA> repository,
                                 MapperBase mapper) {

        this.mapper = mapper;
        this.repository = repository;
    }




    public <S extends E> S save(S entity) {
        
        E_JPA entityJPA = (E_JPA) mapper.entityToEntityJPA(entity);

        E_JPA entityJPAPersisted = repository.save(entityJPA);

        return (S) mapper.entityJPAToEntity(entityJPAPersisted);

    }


    public <S extends E> Iterable<S> saveAll(Iterable<S> entities){

        List<E_JPA> entitiesJPA = mapper.iterableEntitiesToIterableEntitiesJPA( entities );

        List<E_JPA> iter = (List<E_JPA>) repository.saveAll(entitiesJPA);

        return mapper.iterableEntitiesJPAToIterableEntities(iter);

    }


    public Optional<E> findById(ID id) {

        ID_JPA idJPA = (ID_JPA) mapper.idToIdJPA(id);

        Optional<E_JPA> byId = repository.findById(idJPA);

        if(byId.isEmpty())
            return Optional.empty();

        return mapper.entityJPAToOptionalEntity(byId);
    }


    public boolean existsById(ID id) {

        ID_JPA idJPA = (ID_JPA) mapper.idToIdJPA(id);

        return repository.existsById(idJPA);

    }


    public Iterable<E> findAll() {

        List<E_JPA> all = (List<E_JPA>) repository.findAll();

        return mapper.iterableEntitiesJPAToIterableEntities(all);


    }


    public Iterable<E> findAllById(Iterable<ID> ids) {

        List<ID_JPA> idsJPA = mapper.iterableIdToIterableIdJPA(ids);

        List<E_JPA> allById = (List<E_JPA>) repository.findAllById(idsJPA);

        return mapper.iterableEntitiesJPAToIterableEntities(allById);
    }


    public long count() {
        return  repository.count();
    }


    public void deleteById(ID id) {

        ID_JPA idJPA = (ID_JPA) mapper.idToIdJPA(id);

        repository.deleteById(idJPA);

    }


    public void delete(E entity) {

        E_JPA RemListJPA = (E_JPA) mapper.entityToEntityJPA(entity);

        repository.delete(RemListJPA);
    }


    public void deleteAll(Iterable<? extends E> entities) {

        List<E_JPA> entitiesJPA = mapper.iterableEntitiesToIterableEntitiesJPA(entities);

        repository.deleteAll(entitiesJPA);

    }


    public void deleteAll() {

        repository.deleteAll();

    }



}
