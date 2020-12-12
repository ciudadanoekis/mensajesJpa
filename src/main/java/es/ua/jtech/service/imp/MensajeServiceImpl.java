package es.ua.jtech.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.ua.jtech.jpa.entities.Mensaje;
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
	public MensajeOutput findById(Integer id) {
		final Optional<Mensaje> optionalMensaje = mensajesRepository.findById(id);
		MensajeOutput result = new MensajeOutput();
		result = MensajeOutput.of(optionalMensaje.get());

		return result;
	}

	@Override
	public List<MensajeOutput> findAllMensajes() {
		// TODO Auto-generated method stub
		return null;
	}

}
