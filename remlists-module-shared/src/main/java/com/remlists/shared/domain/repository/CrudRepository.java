package com.remlists.shared.domain.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


/**
 * Esta interfaz tiene el objetivo de no acoplar el código a ninguna implementación de un framework concreto, como
 * puede ser Spring Data JPA, JOOQ, MyBatis, etc.
 * <p>
 * Los métodos están copiados de Sping Data JPA, ya que son adecuados, y servirán como base para ser implementados por
 * cualquier otro framework de persistencia.
 * <p>
 * Cada uno de estos frameworks tienen una gran variedad de funcionalidades, si se viese la necesidad de utilizar alguna
 * de ellas para facilitar el desarrollo, ese método deberá ser incluido aquí. De esta forma podrá ser implmentado
 * posteriormente conforme requiera dicho framework.
 * <p>
 * Ejemplos:
 * · Spring Data JPA provee del siguiente método, que como se puede ver está acoplado al framework:
 * List<E> findAll(org.springframework.data.domain.Sort sort)
 * En este caso se podría definir aquí este método del siguiente modo:
 * List<E> findAllSorted()
 * Así de este modo cuando se realice la implementación del método se podrá utilizar el provisto por el framework,
 * del siguiente modo:
 * public List<E> findAllSorted() {
 * //Instancia y configuración de la clase Sort...
 * return findAll(sort);
 * }
 * <p>
 * <p>
 * Created by dsolerac on 10/11/17.
 */
public interface CrudRepository<E, ID> {

    static final Logger LOG = LoggerFactory.getLogger(CrudRepository.class);


    <S extends E> S save(S entity);

    <S extends E> Iterable<S> saveAll(Iterable<S> entities);

    Optional<E> findById(ID id);

    boolean existsById(ID id);

    Iterable<E> findAll();

    Iterable<E> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);

    void delete(E entity);

    void deleteAll(Iterable<? extends E> entities);

    void deleteAll();

}
