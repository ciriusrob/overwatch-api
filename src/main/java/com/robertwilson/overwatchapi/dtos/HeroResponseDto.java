package com.robertwilson.overwatchapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.dtos
 * Class: HeroResponseDto
 */
@Data
public class HeroResponseDto
{
    private long id;

    private String name;

    @JsonProperty( value = "real_name")
    private String realName;

    private int health;

    private int armour;

    private int shield;
}
