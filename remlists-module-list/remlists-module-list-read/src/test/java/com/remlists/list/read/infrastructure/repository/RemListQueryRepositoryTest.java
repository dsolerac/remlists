package com.remlists.list.read.infrastructure.repository;

import com.remlists.list.domain.entities.RemList;
import com.remlists.list.domain.repository.RemListRepository;
import com.remlists.list.domain.valueObjects.RemListName;
import com.remlists.shared.domain.valueObjects.Id;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.remlists.list.read.infrastructure.spring.BeanNames.Infrastructure.Spring.Repository.listReadQueryRepositoryJPA;

@Tag("IntegrationTest")
@DisplayName("Integration for remlist repository")
@SpringBootTest
public class RemListQueryRepositoryTest {

    private Logger LOG = LoggerFactory.getLogger(RemListQueryRepositoryTest.class);


    @Autowired
    @Qualifier(listReadQueryRepositoryJPA)
    private RemListRepository<RemList, Id> repo;


    @DisplayName("VALID TESTS")
    @Nested
    class PersisNewRemlistTest_ValidTests {

        @Test
        @DisplayName("valid find Remlist by name")
        void valid_findRemlistByName() {

            //Given
            RemListName name = new RemListName("xxx");
            RemList list = new RemList( new Id(UUID.randomUUID()), name );

            RemList persistedList = repo.save(list);

            //When
            Optional<RemList> found = repo.<RemListName>findByName(name);

            //Then
            Assertions.assertThat(list).isEqualToComparingFieldByFieldRecursively(persistedList);
            Assertions.assertThat(persistedList).isInstanceOf(RemList.class);

        }

        @Test
        @DisplayName("valid save Remlist")
        void valid_safeRemlist() {

            //Given
            RemList list = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras"));

            //When
            RemList persistedList = repo.save(list);

            //Then
            Assertions.assertThat(list).isEqualToComparingFieldByFieldRecursively(persistedList);
            Assertions.assertThat(persistedList).isInstanceOf(RemList.class);

        }

        @Test
        @DisplayName("valid save all Remlist")
        void valid_safeAllRemlist() {

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras1"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas1"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas1"));

            List<RemList> lists = List.of(list1,list2,list3);

            List<UUID> uuids = lists.stream().map(RemList::getId).map(Id::getUuid).collect(Collectors.toList());

            //When
            repo.saveAll(lists);
            List<RemList> persistedLists = (List<RemList>)  repo.findAll();
            List<UUID> persistedUuids = persistedLists.stream().map(RemList::getId).map(Id::getUuid).collect(Collectors.toList());


            //Then
            Assertions.assertThat(persistedUuids).containsAll(uuids);
            Assertions.assertThat( persistedLists.stream().findFirst().get() ).isInstanceOf(RemList.class);

        }

        @Test
        @DisplayName("valid find by id")
        void valid_findById(){

            //Given
            RemList list = new RemList(new Id(UUID.randomUUID()), new RemListName("grocery"));
            RemList persistedList = repo.save(list);

            //When
            Optional<RemList> listFound = repo.findById(persistedList.getId());

            //Then
            Assertions.assertThat(persistedList.getId()).isEqualTo(listFound.get().getId());
            Assertions.assertThat(persistedList.getName()).isEqualTo(listFound.get().getName());
            Assertions.assertThat(listFound.get()).isInstanceOf(RemList.class);


        }

        @Test
        @DisplayName("valid exists by id")
        void valid_existsById(){

            //Given
            RemList list = new RemList(new Id(UUID.randomUUID()), new RemListName("to do list"));
            RemList persistedList = repo.save(list);

            //When
            boolean exists = repo.existsById(persistedList.getId());

            //Then
            Assertions.assertThat(exists).isTrue();

        }

        @Test
        @DisplayName("valid find all")
        void valid_findAll(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras2"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas2"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas2"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            Iterable<RemList> all = repo.findAll();

            //Then
            Assertions.assertThat(all.spliterator().estimateSize() ).isGreaterThanOrEqualTo(3);
            AssertionsForClassTypes.assertThat(all.iterator().next()).isInstanceOf(RemList.class);

        }

        @Test
        @DisplayName("valid find all by id")
        void valid_findAllById(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras3"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas3"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas3"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            List<Id> ids = persistedLists.stream().map(RemList::getId).collect(Collectors.toList());

            //When
            Iterable<RemList> all = repo.findAllById(ids);

            //Then
            Assertions.assertThat(all.spliterator().estimateSize() ).isGreaterThanOrEqualTo(3);
            AssertionsForClassTypes.assertThat(all.iterator().next()).isInstanceOf(RemList.class);

        }

        @Test
        @DisplayName("valid count")
        void valid_count(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras4"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas4"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas4"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            long count = repo.count();

            //Then
            Assertions.assertThat(count).isGreaterThanOrEqualTo(3);

        }

        @Test
        @DisplayName("valid delete by id")
        void valid_deleteById(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras5"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas5"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas5"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            repo.deleteById(list1.getId());
            List<RemList> all = (List<RemList>) repo.findAll();

            //Then
            Assertions.assertThat(list1.getId())
                    .isNotIn( all.stream().map(RemList::getId).collect(Collectors.toList()) );

        }

        @Test
        @DisplayName("valid delete by id")
        void valid_delete(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras6"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas6"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas6"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            repo.delete(list1);
            List<RemList> all = (List<RemList>) repo.findAll();

            //Then
            Assertions.assertThat(list1.getId())
                    .isNotIn( all.stream().map(RemList::getId).collect(Collectors.toList()) );

        }

        @Test
        @DisplayName("valid delete all iterable")
        void valid_deleteAllIterable(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras7"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas7"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas7"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            repo.deleteAll(lists);
            List<RemList> all = (List<RemList>) repo.findAll();

            //Then
            Assertions.assertThat(list1.getId())
                    .isNotIn( all.stream().map(RemList::getId).collect(Collectors.toList()) );

            Assertions.assertThat(list2.getId())
                    .isNotIn( all.stream().map(RemList::getId).collect(Collectors.toList()) );

            Assertions.assertThat(list3.getId())
                    .isNotIn( all.stream().map(RemList::getId).collect(Collectors.toList()) );


        }

        @Test
        @DisplayName("valid delete all")
        void valid_deleteAll(){

            //Given
            RemList list1 = new RemList(new Id(UUID.randomUUID()), new RemListName("verduras8"));
            RemList list2 = new RemList(new Id(UUID.randomUUID()), new RemListName("frutas8"));
            RemList list3 = new RemList(new Id(UUID.randomUUID()), new RemListName("hortalizas8"));

            List<RemList> lists = List.of(list1,list2,list3);
            List<RemList> persistedLists = (List<RemList>) repo.saveAll(lists);

            //When
            repo.deleteAll();
            long count = repo.count();

            //Then
            Assertions.assertThat(count).isZero();


        }



    }

    @DisplayName("FAILED TESTS")
    @Nested
    class PersisNewRemlistTest_FailTests {

        @Test
        @DisplayName("fail test")
        void fail() {

            //Given

            //When

            //Then
//            assertThat();
        }
    }

}
