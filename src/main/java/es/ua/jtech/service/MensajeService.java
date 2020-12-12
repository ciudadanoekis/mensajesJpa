package es.ua.jtech.service;

import java.util.List;

import es.ua.jtech.jpa.model.MensajeOutput;

public interface MensajeService {

	MensajeOutput findById(Integer id);

	List<MensajeOutput> findAllMensajes();

}
