package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.dtos.AbilityResponseDto;
import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.services.AbilityService;
import com.robertwilson.overwatchapi.services.Mapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Class: AbilityController
 */
@RestController
@CrossOrigin( origins = "*" )
@RequestMapping( value = "/api/v1/ability")
@Api(value = "Ability Controller", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"Ability"})
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

    @ApiOperation( value = "Get All Abilities", produces = "application/json", tags = "Ability" )
    @GetMapping( value = "")
    public ResponseEntity<?> findAll( )
    {
        final Iterable<Ability> abilities = abilityService.all();

        Type setType = new TypeToken<List<AbilityResponseDto>>() {}.getType();

        final List<AbilityResponseDto> response = mapper.map(abilities, setType);

        return ResponseEntity.ok(response);
    }

    @ApiOperation( value = "Get Ability By ID", produces = "application/json", tags = "Ability" )
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
