package com.robertwilson.overwatchapi.repositories;

import com.robertwilson.overwatchapi.entities.Ability;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.repositories
 * Class: AbilityRepository
 */
public interface AbilityRepository extends JpaRepository<Ability, Long>
{
    Page<Ability> findAll( Pageable pageable );

    List<Ability> findAllByHeroId( long heroId );
}
