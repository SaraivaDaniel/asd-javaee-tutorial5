package com.javaee.daniel.springjpamysql.services;

import java.util.List;

import com.javaee.daniel.springjpamysql.api.v1.model.GarageDTO;

public interface GarageService {
	List<GarageDTO> getAll();

	GarageDTO getById(Long id);

	GarageDTO createNew(GarageDTO garageDTO);

	GarageDTO save(Long id, GarageDTO garageDTO);

	void deleteById(Long id);
}
