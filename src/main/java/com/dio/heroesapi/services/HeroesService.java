package com.dio.heroesapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.repositories.HeroesRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroesService {
	
	@Autowired
	private HeroesRepository repository;
	
	public Flux<Heroes> findAll() {
		return Flux.fromIterable(repository.findAll());
	}
	
	public Mono <Heroes> findById(String id) {
		return Mono.justOrEmpty(repository.findById(id));
	}
	
	public Mono <Heroes> save(Heroes entity) {
		return Mono.justOrEmpty(repository.save(entity));
	}
	
	public Mono <Boolean> deleteById(String id) {
	repository.deleteById(id);
	return Mono.just(true);
	}
}
