package com.robertwilson.overwatchapi.services;

import com.robertwilson.overwatchapi.entities.Ability;
import com.robertwilson.overwatchapi.repositories.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    public Ability single( long id )
    {
        return repository.findById(id).orElse(null);
    }

    public List<Ability> allByHeroId( long heroId )
    {
        return repository.findAllByHeroId(heroId);
    }
}
