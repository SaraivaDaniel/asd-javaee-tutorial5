package com.javaee.daniel.springjpamysql.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.javaee.daniel.springjpamysql.domain.Garage;
import com.javaee.daniel.springjpamysql.domain.GasStation;

public interface GasStationRepository extends CrudRepository<GasStation, Long> {

	List<GasStation> findByGarage(Garage garage);
}