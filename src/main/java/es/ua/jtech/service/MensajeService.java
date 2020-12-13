package es.ua.jtech.service;

import java.util.List;

import es.ua.jtech.jpa.model.MensajeInput;
import es.ua.jtech.jpa.model.MensajeOutput;

public interface MensajeService {

	MensajeOutput findMessageById(Integer id);

	List<MensajeOutput> findAllMensajes();

	MensajeOutput insertMessage(MensajeInput originalMessageInput);

}
