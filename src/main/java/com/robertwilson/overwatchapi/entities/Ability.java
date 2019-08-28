package com.robertwilson.overwatchapi.entities;

import lombok.Data;

import javax.persistence.*;

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

    @ManyToOne( targetEntity = Hero.class, cascade = CascadeType.ALL)
    private Hero hero;

    private String name;

    @Column( columnDefinition = "TEXT")
    private String description;

    private boolean isUltimate;
}
