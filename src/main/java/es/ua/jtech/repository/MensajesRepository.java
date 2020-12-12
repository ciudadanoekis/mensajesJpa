package es.ua.jtech.repository;

import com.paradigma.intranet.microservices.architecture.respository.CustomJpaRepository;

import es.ua.jtech.jpa.entities.Mensaje;

public interface MensajesRepository extends CustomJpaRepository<Mensaje, Integer> {
}
