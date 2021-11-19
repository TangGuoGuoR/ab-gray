package com.ab.gray.converter;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: RuleSourceConverter
 */
public interface RuleSourceConverter<S,T> {
    T convert(S source);
}