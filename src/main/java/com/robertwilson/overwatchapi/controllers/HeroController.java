package com.robertwilson.overwatchapi.controllers;

import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.services.HeroService;
import com.robertwilson.overwatchapi.utils.Paginater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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
    public ResponseEntity<?> findAll( Integer page, Integer pageSize, String direction, String... properties )
    {

        Paginater paginationParams = new Paginater(page, pageSize, direction).invoke();
        page = paginationParams.getPage();
        pageSize = paginationParams.getPageSize();
        Sort.Direction sortDirection = paginationParams.getSortDirection();

        properties = properties == null ? new String[]{"id"} : properties;

        PageRequest pageRequest = PageRequest.of(page, pageSize, sortDirection, properties);

        final Page<Hero> heroes = heroService.all(pageRequest);

        return ResponseEntity.ok(heroes.get().collect(Collectors.toList()));
    }
}
