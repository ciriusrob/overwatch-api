package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.services.AbilityService;
import com.robertwilson.overwatchapi.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class HeroController
{
    private HeroService heroService;

    private AbilityService abilityService;

    @Lazy
    @Autowired
    public HeroController( HeroService heroService, AbilityService abilityService )
    {
        this.heroService = heroService;
        this.abilityService = abilityService;
    }

    @GetMapping( value = {"", "/"})
    public ResponseEntity<?> findAll( )
    {
        final Iterable<Hero> heroes = heroService.all();

        return ResponseEntity.ok(heroes);
    }

    @GetMapping( value = "/{heroId}")
    public ResponseEntity<?> getHeroById( @PathVariable("heroId") long heroId )
    {
        final Hero hero = heroService.single(heroId);

        if ( hero == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(hero);
    }

    @GetMapping( value = "/{heroId}/abilities")
    public ResponseEntity<?> getHeroAbilities( @PathVariable("heroId") long heroId )
    {
        final List<Ability> abilities = abilityService.allByHeroId(heroId);

        return ResponseEntity.ok(abilities);
    }
}
