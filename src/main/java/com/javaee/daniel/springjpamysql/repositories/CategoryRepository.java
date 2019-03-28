package com.javaee.daniel.springjpamysql.repositories;

import org.springframework.data.repository.CrudRepository;
import com.javaee.daniel.springjpamysql.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
