package com.robertwilson.overwatchapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.services.AbilityService;
import com.robertwilson.overwatchapi.services.HeroService;
import com.robertwilson.overwatchapi.services.http_client.BaseClient;
import com.robertwilson.overwatchapi.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class OverWatchApiApplication
{
    private BaseClient client;

    private HeroService heroService;

    private AbilityService abilityService;

    @Autowired
    public OverWatchApiApplication( BaseClient client, HeroService heroService, AbilityService abilityService )
    {
        this.client = client;
        this.heroService = heroService;
        this.abilityService = abilityService;
    }

    public static void main( String[] args )
    {
        SpringApplication.run(OverWatchApiApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrap()
    {
        final Page<Ability> abilities = abilityService.all(PageRequest.of(1, 1));

        if ( abilities != null && !abilities.isEmpty() ) return null;

        return string -> getAbilities();
    }

    private void getAbilities() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, ExecutionException, InterruptedException
    {
        final CompletableFuture<Object> future = client.get("ability");

        final ResponseEntity rawResponse = (ResponseEntity) future.get();

        ObjectMapper mapper = new ObjectMapper();

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        final TypeReference<ApiResponse<List<Ability>>> ref = new TypeReference<ApiResponse<List<Ability>>>() {};

        final ApiResponse<List<Ability>> response = mapper.convertValue(rawResponse.getBody(), ref);

        if ( response != null && response.getData().size() > 0 ) {

            abilityService.save(response.getData());
        }
    }

}
