package com.robertwilson.overwatchapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.dtos
 * Class: AbilityResponseDto
 */
@Data
public class AbilityResponseDto
{
    private long id;

    private String name;

    private String description;

    @JsonProperty( value = "is_ultimate")
    private boolean isUltimate;
}
