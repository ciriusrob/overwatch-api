package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.services.AbilityService;
import com.robertwilson.overwatchapi.services.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.controllers
 * Class: AbilityController
 */
@RestController
@CrossOrigin( origins = "*" )
@RequestMapping( value = "/api/v1/ability")
public class AbilityController
{
    private Mapper mapper;

    private AbilityService abilityService;

    @Autowired
    public AbilityController( Mapper mapper, AbilityService abilityService )
    {
        this.mapper = mapper;
        this.abilityService = abilityService;
    }

    @GetMapping( value = {"", "/"})
    public ResponseEntity<?> findAll( )
    {
        final Iterable<Ability> heroes = abilityService.all();

        return ResponseEntity.ok(heroes);
    }

    @GetMapping( value = "/{abilityId}")
    public ResponseEntity<?> getAbilityById( @PathVariable("abilityId") long abilityId )
    {
        final Ability ability = abilityService.single(abilityId);

        if ( ability == null ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(ability);
    }
}
