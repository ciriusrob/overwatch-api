package com.robertwilson.overwatchapi.services;

import com.robertwilson.overwatchapi.entities.Hero;
import com.robertwilson.overwatchapi.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.services
 * Class: HeroService
 */
@Service
public class HeroService
{
    private HeroRepository repository;

    @Autowired
    public HeroService( HeroRepository repository )
    {
        this.repository = repository;
    }

    public Iterable<Hero> all( )
    {
        return repository.findAll();
    }

    public Page<Hero> all( Pageable pageable )
    {
        return repository.findAll(pageable);
    }

    public Hero single( long id )
    {
        return repository.findById(id).orElse(null);
    }
}
