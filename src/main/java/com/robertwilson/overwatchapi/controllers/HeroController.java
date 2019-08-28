package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.dtos.AbilityResponseDto;
import com.robertwilson.overwatchapi.dtos.HeroResponseDto;
import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.services.AbilityService;
import com.robertwilson.overwatchapi.services.HeroService;
import com.robertwilson.overwatchapi.services.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.controllers
 * Class: HeroController
 */
@RestController
@CrossOrigin( origins = "*" )
@RequestMapping( value = "/api/v1/hero")
@Api(value = "Hero Controller", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Hero"})
public class HeroController
{
    private Mapper mapper;

    private HeroService heroService;

    private AbilityService abilityService;

    @Lazy
    @Autowired
    public HeroController( Mapper mapper, HeroService heroService, AbilityService abilityService )
    {
        this.mapper = mapper;
        this.heroService = heroService;
        this.abilityService = abilityService;
    }

    @ApiOperation( value = "Get All Heroes", produces = "application/json", tags = "Hero" )
    @GetMapping( value = "")
    public ResponseEntity<?> findAll( )
    {
        final Iterable<Hero> heroes = heroService.all();

        Type setType = new TypeToken<List<HeroResponseDto>>() {}.getType();

        final List<HeroResponseDto> response = mapper.map(heroes, setType);

        return ResponseEntity.ok(response);
    }

    @ApiOperation( value = "Get Hero By ID", produces = "application/json", tags = "Hero" )
    @GetMapping( value = "/{heroId}")
    public ResponseEntity<?> getHeroById( @PathVariable("heroId") long heroId )
    {
        final Hero hero = heroService.single(heroId);

        if ( hero == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(hero);
    }

    @ApiOperation( value = "Get Hero Abilities", produces = "application/json", tags = "Hero" )
    @GetMapping( value = "/{heroId}/abilities")
    public ResponseEntity<?> getHeroAbilities( @PathVariable("heroId") long heroId )
    {
        final List<Ability> abilities = abilityService.allByHeroId(heroId);

        Type setType = new TypeToken<List<AbilityResponseDto>>() {}.getType();

        final List<AbilityResponseDto> response = mapper.map(abilities, setType);

        return ResponseEntity.ok(response);
    }
}
