package es.ua.jtech.repository;
import com.paradigma.intranet.microservices.architecture.respository.CustomJpaRepository;

import es.ua.jtech.jpa.mensajes.Autor;

public interface AutoresRepository extends CustomJpaRepository<Autor, Integer> {
}