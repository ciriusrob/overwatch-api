package com.robertwilson.overwatchapi.services;

import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.repositories.AbilityRepository;
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
 * Class: AbilityService
 */
@Service
public class AbilityService
{
    private AbilityRepository repository;

    @Autowired
    public AbilityService( AbilityRepository repository )
    {
        this.repository = repository;
    }

    public Iterable<Ability> all()
    {
        return repository.findAll();
    }

    public Page<Ability> all( Pageable pageable )
    {
        return repository.findAll(pageable);
    }

    public Ability single( long id )
    {
        return repository.findById(id).orElse(null);
    }

    public List<Ability> save( List<Ability> entities )
    {
        return repository.saveAll(entities);
    }

    public List<Ability> allByHeroId( long heroId )
    {
        return repository.findAllByHeroId(heroId);
    }
}
