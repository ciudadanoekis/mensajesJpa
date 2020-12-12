package es.ua.jtech.repository;
import com.paradigma.intranet.microservices.architecture.respository.CustomJpaRepository;

import es.ua.jtech.jpa.entities.Autor;

public interface AutoresRepository extends CustomJpaRepository<Autor, Integer> {
}