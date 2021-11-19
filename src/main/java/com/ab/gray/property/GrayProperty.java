package com.ab.gray.property;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: GrayProperty
 */
public interface GrayProperty<T> {
    void addListener(PropertyListener<T> listener);

    void removeListener(PropertyListener<T> listener);

    boolean updateValue(T newValue);
}
