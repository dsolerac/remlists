package com.remlists.list.read.infrastructure.mapper;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.read.infrastructure.jpa.entities.RemListJPA;
import com.remlists.list.read.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.mapper.MapperBase;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Component.Mapper.remListMapperRead;



@Component(remListMapperRead)
public class RemListMapper extends MapperBase {


    private ModelMapper mapper;

    public RemListMapper(ModelMapper mapper) {
        super(  mapper,
                RemList.class,
                RemListJPA.class,
                IdJPA.class,
                new TypeToken<List<RemList>>() {}.getType(),
                new TypeToken<List<RemListJPA>>() {}.getType(),
                new TypeToken<List<IdJPA>>() {}.getType()
        );
    }








}

    /*

https://blog.codefx.org/java/casting-in-java-8-and-beyond/

        TypeMap<Installation, DeviceTokenCurator> typeMap = mapper.createTypeMap(Installation.class, DeviceTokenCurator.class)
                .addMappings(mapper -> mapper.skip(DeviceTokenCurator::setId))
                .addMappings(mapper -> mapper.skip(DeviceTokenCurator::setCreatedAt))
                .addMappings(mapper -> mapper.map(x -> {return jobId;}, DeviceTokenCurator::setJobId))
                .addMappings(mapper -> mapper.map(x -> {return "PENDING";}, DeviceTokenCurator::setPushSentStatus));

        Type listType = new TypeToken<List<DeviceTokenCurator>>() {}.getType();
        List<DeviceTokenCurator> devicesCurator = mapper.map( installations, listType );

     */