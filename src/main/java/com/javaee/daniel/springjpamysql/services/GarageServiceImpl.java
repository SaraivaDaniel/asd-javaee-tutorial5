package com.javaee.daniel.springjpamysql.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.javaee.daniel.springjpamysql.api.v1.mapper.GarageMapper;
import com.javaee.daniel.springjpamysql.api.v1.model.GarageDTO;
import com.javaee.daniel.springjpamysql.domain.Garage;
import com.javaee.daniel.springjpamysql.domain.Vehicle;
import com.javaee.daniel.springjpamysql.repositories.GarageRepository;
import com.javaee.daniel.springjpamysql.repositories.GasStationRepository;
import com.javaee.daniel.springjpamysql.repositories.VehicleRepository;
import com.javaee.daniel.springjpamysql.services.GarageService;

@Service
public class GarageServiceImpl implements GarageService {

	private GarageRepository garageRepository;
	private VehicleRepository vehicleRepository;
	private GasStationRepository gasStationRepository;

	private final GarageMapper garageMapper;

	public GarageServiceImpl(GarageRepository garageRepository, VehicleRepository vehicleRepository,
			GasStationRepository gasStationRepository, GarageMapper garageMapper) {
		this.garageRepository = garageRepository;
		this.garageMapper = garageMapper;
		this.vehicleRepository = vehicleRepository;
		this.gasStationRepository = gasStationRepository;
	}

	@Override
	public List<GarageDTO> getAll() {
		return StreamSupport.stream(this.garageRepository.findAll().spliterator(), false)
				.map(garageMapper::garageToGarageDTO).collect(Collectors.toList());
	}

	@Override
	public GarageDTO getById(Long id) {
		Garage garage = getGarageById(id);
		return garageMapper.garageToGarageDTO(garage);
	}

	private Garage getGarageById(Long id) {
		Optional<Garage> garageOptional = garageRepository.findById(id);

		if (!garageOptional.isPresent()) {
			throw new IllegalArgumentException("Garagem não localizada para ID: " + id.toString());
		}

		return garageOptional.get();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GarageDTO createNew(GarageDTO garageDTO) {
		Garage detachedGarage = garageMapper.garageDTOToGarage(garageDTO);
		Garage garageSaved = garageRepository.save(detachedGarage);
		return garageMapper.garageToGarageDTO(garageSaved);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GarageDTO save(Long id, GarageDTO garageDTO) {
		Garage detachedGarage = garageMapper.garageDTOToGarage(garageDTO);
		detachedGarage.setId(id);
		Garage garage = getGarageById(id);
		adjustGasStation(detachedGarage, garage);
		adjustVehicles(detachedGarage, garage);
		Garage garageSaved = garageRepository.save(detachedGarage);
		return garageMapper.garageToGarageDTO(garageSaved);
	}

	private void adjustVehicles(Garage detachedGarage, Garage garage) {
		List<Vehicle> vehicles = vehicleRepository.findByGarage(garage);
		List<Vehicle> vehiclesToDelete = vehicles.stream()
				.filter(vehicle -> !detachedGarage.getVehicles().contains(vehicle)).collect(Collectors.toList());
		vehicleRepository.deleteAll(vehiclesToDelete);
	}

	private void adjustGasStation(Garage detachedGarage, Garage garage) {
		gasStationRepository.findByGarage(garage).stream().forEach(gasStation -> {
			if (!gasStation.getId().equals(detachedGarage.getGasStation().getId())) {
				gasStation.setGarage(null);
				gasStationRepository.save(gasStation);
			}
		});
	}

	@Override
	public void deleteById(Long id) {
		garageRepository.deleteById(id);
	}

}
