package com.ab.gray.datasource;

import com.ab.gray.converter.RuleSourceConverter;
import com.ab.gray.property.DynamicGrayProperty;
import com.ab.gray.property.GrayProperty;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: AbstractDataSource
 */
public abstract class AbstractDataSource<T,S> implements IGrayDataSource<T,S> {
    protected final RuleSourceConverter<S, T> converter;
    protected final GrayProperty<T> grayProperty;

    protected AbstractDataSource(RuleSourceConverter<S, T> converter) {
        this.converter = converter;
        this.grayProperty = new DynamicGrayProperty<>();
    }

    public T loadConfig() throws Exception {
        return this.loadConfig(this.readSource());
    }

    public T loadConfig(S source) throws Exception {
        T value = this.converter.convert(source);
        return value;
    }


    public GrayProperty<T> getProperty() {
        return this.grayProperty;
    }


    public void close() throws Exception {

    }
}
