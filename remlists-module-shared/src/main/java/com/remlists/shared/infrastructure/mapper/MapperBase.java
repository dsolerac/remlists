package com.remlists.shared.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class MapperBase<E, E_JPA, ID, ID_JPA>{

    private Logger LOG = LoggerFactory.getLogger(MapperBase.class);


    private ModelMapper mapper;

    private Class<E> typeEntityClass;
    private Class<E_JPA> typeEntityJpaClass;
    private Class<ID_JPA> typeIdJpaClass;
    private Type typeIterEntity;
    private Type typeIterEntityJPA;
    private Type typeIterIdJPA;

    public MapperBase(ModelMapper mapper,
                      Class<E> typeEntityClass,
                      Class<E_JPA> typeEntityJpaClass,
                      Class<ID_JPA> typeIdJpaClass,
                      Type typeIterEntity,
                      Type typeIterEntityJPA,
                      Type typeIterIdJPA ) {

        this.mapper = mapper;
        this.typeEntityClass = typeEntityClass;
        this.typeEntityJpaClass = typeEntityJpaClass;
        this.typeIdJpaClass = typeIdJpaClass;
        this.typeIterEntity = typeIterEntity;
        this.typeIterEntityJPA = typeIterEntityJPA;
        this.typeIterIdJPA = typeIterIdJPA;
    }



    public E entityJPAToEntity(E_JPA entityJPA){

        return (E) mapper.map(entityJPA, typeEntityClass);
    }

    public <E2_JPA, E2> E2 entityJPAToEntity2(E2_JPA entityJPA, Class typeEntityClass2){

        return (E2) mapper.<E2>map(entityJPA, typeEntityClass2);
    }



    public E_JPA entityToEntityJPA(E entity){

        return (E_JPA) mapper.map(entity, typeEntityJpaClass);

    }

    public <E2_JPA, E2> E2_JPA entityToEntityJPA2(E2 entity, Class typeEntityJpaClass2, String typeMapName ){

        return (E2_JPA) mapper.<E2_JPA>map(entity, typeEntityJpaClass2, typeMapName);

    }

    public <E2_JPA, E2> E2_JPA entityToEntityJPA2(E2 entity, Class typeEntityJpaClass2 ){

        return (E2_JPA) mapper.<E2_JPA>map(entity, typeEntityJpaClass2);

    }



    public List<E_JPA> iterableEntitiesToIterableEntitiesJPA(Iterable<E> entities){

        return mapper.map(entities, typeIterEntityJPA);

    }

    public <E2_JPA, E2> Set<E2_JPA> iterableEntitiesToIterableEntitiesJPA2(Iterable<E2> entities, Type typeIterEntityJPA2){

        return  mapper.map(entities, typeIterEntityJPA2);

    }



    public List<E> iterableEntitiesJPAToIterableEntities(Iterable<E_JPA> entities){

        return mapper.map(entities, typeIterEntity);

    }

    public <E2, E2_JPA> Set<E2> iterableEntitiesJPAToIterableEntities2(Iterable<E2_JPA> entities, Type typeIterEntity2){

        return mapper.map(entities, typeIterEntity2);

    }



    public  ID_JPA idToIdJPA(ID id){

        return (ID_JPA) mapper.map(id, typeIdJpaClass);

    }

    public  List<ID_JPA> iterableIdToIterableIdJPA(Iterable<ID> ids){

        return mapper.map(ids, typeIterIdJPA);

    }


    public  Optional<E> entityJPAToOptionalEntity(Optional<E_JPA> entityJPA){

        return Optional.of( mapper.map( entityJPA.orElseThrow(), typeEntityClass ) );

    }

    public <E2_JPA, E2> Optional<E2> entityJPAToOptionalEntity2(Optional<E2_JPA> entityJPA, Class typeEntityClass2 ){

        return Optional.<E2>of((E2) mapper.map( entityJPA.orElseThrow(), typeEntityClass2 ));

    }



    public ModelMapper getMapper() {
        return mapper;
    }


}
