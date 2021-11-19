package com.ab.gray.property;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: PropertyListener
 */
public interface PropertyListener<T> {
    void configUpdate(T value);

    void configLoad(T value);
}
