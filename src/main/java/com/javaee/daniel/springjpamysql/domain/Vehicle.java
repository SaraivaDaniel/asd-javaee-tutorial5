package com.javaee.daniel.springjpamysql.domain;

import com.javaee.daniel.springjpamysql.domain.Garage;
import com.javaee.daniel.springjpamysql.domain.FuelType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer year;

	@Enumerated(value = EnumType.STRING)
	private FuelType fuelType;

	@ManyToOne
	private Garage garage;
}
