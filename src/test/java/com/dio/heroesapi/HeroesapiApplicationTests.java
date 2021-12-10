package com.dio.heroesapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.dio.heroesapi.constants.HeroConstant.HEROES_ENDPOINTS_LOCAL;

import com.dio.heroesapi.repositories.HeroesRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class HeroesapiApplicationTests {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	HeroesRepository repository;

	@Test
	void getOneHeroById() {
		webTestClient.get().uri(HEROES_ENDPOINTS_LOCAL.concat("/{id}"), "1")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}
	
	@Test
	void getOneHeroNotFound() {
		webTestClient.get().uri(HEROES_ENDPOINTS_LOCAL.concat("/{id}"), "30")
		.exchange()
		.expectStatus().isNotFound();
	}
	
	@Test
	void deleteHero() {
		webTestClient.delete().uri(HEROES_ENDPOINTS_LOCAL.concat("/{id}"), "1")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isNotFound()
		.expectBody(Void.class);
	}

}
