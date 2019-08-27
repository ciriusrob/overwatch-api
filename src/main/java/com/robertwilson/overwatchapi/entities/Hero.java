package com.robertwilson.overwatchapi.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.entities
 * Class: Hero
 */
@Data
@Entity
public class Hero
{
    @Id
    private long id;

    private String name;

    private String realName;

    private int health;

    private int armour;

    private int shield;
}
