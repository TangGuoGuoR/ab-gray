package com.ab.gray.rule;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: IRule
 */
public interface IRule {
    String getKey();
    boolean enable();
    boolean check(Object value);


}
