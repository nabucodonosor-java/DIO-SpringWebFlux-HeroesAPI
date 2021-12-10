package com.dio.heroesapi.controllers;

import static com.dio.heroesapi.constants.HeroConstant.HEROES_ENDPOINTS_LOCAL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dio.heroesapi.document.Heroes;
import com.dio.heroesapi.services.HeroesService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class HeroesController {
	
	@Autowired
	private HeroesService service;
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(HeroesController.class);
	
	@GetMapping(HEROES_ENDPOINTS_LOCAL)
	public Flux<Heroes> getAllItems() {
		log.info("requesting the list of all heroes");
		return service.findAll();
	}
	
	@GetMapping(HEROES_ENDPOINTS_LOCAL+"/id")
	public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id) {
		log.info("requesting hero with id {}", id);
		return service.findById(id).map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping(HEROES_ENDPOINTS_LOCAL)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Heroes> createHero(@RequestBody Heroes entity) {
		log.info("create a new hero");
		return service.save(entity);
		
	}
	
	@DeleteMapping(HEROES_ENDPOINTS_LOCAL+"/id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Mono<HttpStatus> deleteById(@PathVariable String id) {
		service.deleteById(id);
		log.info("deleting hero with id {}", id);
		return Mono.just(HttpStatus.NO_CONTENT);
	}
	
}
