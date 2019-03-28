package com.javaee.daniel.springjpamysql.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaee.daniel.springjpamysql.domain.Garage;
import com.javaee.daniel.springjpamysql.domain.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

	List<Vehicle> findByGarage(Garage garage);
}