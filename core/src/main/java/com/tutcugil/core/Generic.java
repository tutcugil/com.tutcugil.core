package com.tutcugil.core;

/**
 * Created by Muhammet TUTCUGIL on 26.09.2017.
 * http://www.tutcugil.com
 */

public class Generic<T>
{
    private Class<T> clazz;

    public Generic(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    public T build() throws InstantiationException, IllegalAccessException
    {
        return clazz.newInstance();
    }
}
