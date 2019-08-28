package com.robertwilson.overwatchapi.repositories;

import com.robertwilson.overwatchapi.entities.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.repositories
 * Class: HeroRepository
 */
public interface HeroRepository extends JpaRepository<Hero, Long>
{
    Page<Hero> findAll( Pageable pageable );
}
