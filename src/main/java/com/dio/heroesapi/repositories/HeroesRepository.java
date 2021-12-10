package com.dio.heroesapi.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.dio.heroesapi.document.Heroes;

@EnableScan
public interface HeroesRepository extends CrudRepository<Heroes, String> {

}
