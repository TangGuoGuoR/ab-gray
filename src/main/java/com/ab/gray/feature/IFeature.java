package com.ab.gray.feature;

/**
 * User: tang@gray.com
 * Date: 2021/11/18
 * Description: IFeature
 */
public interface IFeature<F> {
    F getFeature();
    boolean check(Object targetValue);
}
