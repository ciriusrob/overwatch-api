package com.robertwilson.overwatchapi.utils;

import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

/**
 * Created by: Robert Wilson
 * Date:  2019-08-28
 * Project: overwatch-api
 * Package: com.robertwilson.overwatchapi.utils
 * Class: Paginater
 */
public class Paginater
{
    private Integer page;
    private Integer pageSize;
    private String direction;
    private Sort.Direction sortDirection;

    public Paginater( Integer page, Integer pageSize, String direction )
    {
        this.page = page;
        this.pageSize = pageSize;
        this.direction = direction;
    }

    public Integer getPage()
    {
        return page;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public Sort.Direction getSortDirection()
    {
        return sortDirection;
    }

    public Paginater invoke()
    {
        page = page != null && page > 0 ? --page : 0;
        pageSize = pageSize == null ? 10 : pageSize;

        sortDirection = Sort.Direction.DESC;

        if ( !StringUtils.isEmpty(direction) ) {
            if ( direction.toUpperCase().equals("ASC") ) {
                sortDirection = Sort.Direction.ASC;
            }
        }
        return this;
    }
}
