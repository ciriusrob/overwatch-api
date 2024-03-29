package com.robertwilson.overwatchapi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @JsonBackReference
    @OneToMany( mappedBy = "hero")
    private List<Ability> abilities = new ArrayList<>();

    private String name;

    @JsonProperty( value = "real_name")
    private String realName;

    private int health;

    private int armour;

    private int shield;
}
