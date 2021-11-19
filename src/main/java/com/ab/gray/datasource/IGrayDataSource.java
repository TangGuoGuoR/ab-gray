package com.ab.gray.datasource;

import com.ab.gray.property.GrayProperty;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: IGrayDataSource
 */
public interface IGrayDataSource<T,S> {
    T loadConfig() throws Exception;

    S readSource() throws Exception;

    GrayProperty<T> getProperty();

    void close() throws Exception;
}
