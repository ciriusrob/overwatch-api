package com.robertwilson.overwatchapi.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.utils
 * Class: ApiResponse
 */
public class ApiResponse<T>
{
    private @Getter @Setter int total;

    private @Getter @Setter String first;

    private @Getter @Setter String next;

    private @Getter @Setter String previous;

    private @Getter @Setter String last;

    private @Getter @Setter T data;

    public ApiResponse( int total, String first, String next, String previous, String last, T data )
    {
        this.total = total;
        this.first = first;
        this.next = next;
        this.previous = previous;
        this.last = last;
        this.data = data;
    }

    public ApiResponse()
    {
    }
}
