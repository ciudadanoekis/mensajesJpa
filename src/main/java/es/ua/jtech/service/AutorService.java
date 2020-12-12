package es.ua.jtech.service;

import java.util.List;

import es.ua.jtech.jpa.model.AutorOutput;

public interface AutorService {

	AutorOutput findById(Integer id);

	List<AutorOutput> findAllAutors();
}
