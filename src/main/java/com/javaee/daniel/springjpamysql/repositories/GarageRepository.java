package com.javaee.daniel.springjpamysql.repositories;

import org.springframework.data.repository.CrudRepository;

import com.javaee.daniel.springjpamysql.domain.Garage;

public interface GarageRepository extends CrudRepository<Garage, Long> {

}