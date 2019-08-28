package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public HeroController( HeroService heroService )
    {
        this.heroService = heroService;
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
}
