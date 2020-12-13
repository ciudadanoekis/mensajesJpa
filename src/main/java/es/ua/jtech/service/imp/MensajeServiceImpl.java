package es.ua.jtech.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.paradigma.intranet.microservices.architecture.error.IntranetException;

import es.ua.jtech.jpa.entities.Mensaje;
import es.ua.jtech.jpa.error.MensajesError;
import es.ua.jtech.jpa.model.MensajeInput;
import es.ua.jtech.jpa.model.MensajeOutput;
import es.ua.jtech.repository.MensajesRepository;
import es.ua.jtech.service.MensajeService;

@Service
public class MensajeServiceImpl implements MensajeService{

	private final MensajesRepository mensajesRepository;



	public MensajeServiceImpl(MensajesRepository mensajesRepository) {
		super();
		this.mensajesRepository = mensajesRepository;
	}

	@Override
	public List<MensajeOutput> findAllMensajes() {
		List<MensajeOutput> result = new ArrayList<>();

		final List<Mensaje> mensajes = mensajesRepository.findAll();

		if(!CollectionUtils.isEmpty(mensajes)) {
			result = mensajes.stream()
					.map(MensajeOutput::of)
					.collect(Collectors.toList());
		}
		return result;
	}

	@Override
	public MensajeOutput findMessageById(Integer id) {
		final Optional<Mensaje> optionalMensaje = mensajesRepository.findById(id);

		if(!optionalMensaje.isPresent()) {
			throw new IntranetException(MensajesError.MESSAGE_NOT_EXITS);
		}

		return MensajeOutput.of(optionalMensaje.get());
	}


	@Override
	 @Transactional(isolation = Isolation.SERIALIZABLE,
 	rollbackFor = {RuntimeException.class, MethodArgumentNotValidException.class})
	public MensajeOutput insertMessage(MensajeInput originalMessageInput) {
		Mensaje mensaje = new Mensaje();
		mensaje.setNum(originalMessageInput.getNum());
		mensaje.setTexto(originalMessageInput.getTexto());
		mensaje.setFecha(originalMessageInput.getFecha());
		mensaje.setAutorId(originalMessageInput.getAutorId());

		MensajeOutput result = MensajeOutput.of(mensaje);

		return result;
	}

}
