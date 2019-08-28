package com.robertwilson.overwatchapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.entities
 * Class: Ability
 */
@Data
@Entity
public class Ability
{
    @Id
    private long id;

    private String name;

    private String description;

    private boolean isUltimate;
}
