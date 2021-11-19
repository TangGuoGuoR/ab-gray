package com.ab.gray.datasource.support;

import com.ab.gray.converter.RuleSourceConverter;
import com.ab.gray.datasource.AbstractDataSource;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: MemoryDataSource
 */
public class MemoryDataSource<T> extends AbstractDataSource<T,String> {

    private final String rules;
    public MemoryDataSource(String rules, RuleSourceConverter<String, T> converter) {
        super(converter);
        this.rules=rules;
        initialize();
    }

    @Override
    public String readSource() throws Exception {
        return rules;
    }
    private void initialize() {
        this.loadAndUpdateRules();
    }

    private void loadAndUpdateRules() {
        try {
            T newValue = this.loadConfig();
            this.getProperty().updateValue(newValue);
        } catch (Throwable var2) {

        }

    }
}
