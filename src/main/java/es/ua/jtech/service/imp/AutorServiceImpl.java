package es.ua.jtech.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.ua.jtech.jpa.entities.Autor;
import es.ua.jtech.jpa.model.AutorOutput;
import es.ua.jtech.repository.AutoresRepository;
import es.ua.jtech.service.AutorService;

@Service
public class AutorServiceImpl implements AutorService {

	private final AutoresRepository autoresRepository;

	public AutorServiceImpl(AutoresRepository autoresRepository) {
		super();
		this.autoresRepository = autoresRepository;
	}

	@Override
	public AutorOutput findById(Integer id) {
		final Optional<Autor> optionalautor = autoresRepository.findById(id);

		return AutorOutput.of(optionalautor.get());
	}

	@Override
	public List<AutorOutput> findAllAutors() {
		// TODO Auto-generated method stub
		return null;
	}

}
