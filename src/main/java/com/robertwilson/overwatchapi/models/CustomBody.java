package com.robertwilson.overwatchapi.models;

import java.io.Serializable;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.models
 * Class: CustomBody
 */
public class CustomBody<T> implements Serializable
{
    private int total;

    private String first;

    private String next;

    private String previous;

    private String last;

    private T data;

    public CustomBody( int total, String first, String next, String previous, String last, T data )
    {
        this.total = total;
        this.first = first;
        this.next = next;
        this.previous = previous;
        this.last = last;
        this.data = data;
    }
}

// total	24
//first	"http://overwatch-api.net/api/v1/hero?page=1"
//next	null
//previous	null
//last	"http://overwatch-api.net/api/v1/hero?page=1"