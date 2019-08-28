package com.robertwilson.overwatchapi.repositories;

import com.robertwilson.overwatchapi.entities.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.repositories
 * Class: AbilityRepository
 */
public interface AbilityRepository extends JpaRepository<Ability, Long>
{
}
