package com.javaee.daniel.springjpamysql.controllers.v1;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaee.daniel.springjpamysql.api.v1.model.GarageDTO;
import com.javaee.daniel.springjpamysql.services.GarageService;

@RestController
@RequestMapping(GarageController.BASE_URL)
public class GarageController {
	
	public static final String BASE_URL = "/api/v1/garages";
	
	private final GarageService garageService;
	
	public GarageController(GarageService garageService) {
		this.garageService = garageService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<GarageDTO> getAll() {
		return garageService.getAll();
	}
	
	@GetMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public GarageDTO getById(@PathVariable Long id) {
		return garageService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GarageDTO createNew(@RequestBody GarageDTO garageDTO) {
		return garageService.createNew(garageDTO);
	}
	
	
	
	

}
