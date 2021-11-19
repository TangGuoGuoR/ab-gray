package com.ab.gray.datasource.support;

import com.ab.gray.converter.RuleSourceConverter;
import com.ab.gray.datasource.AbstractDataSource;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: ApolloDataSource
 */
public class ApolloDataSource<T> extends AbstractDataSource<T,String> {

    private final Config config;
    private final String ruleKey;
    private final String defaultRuleValue;
    private ConfigChangeListener configChangeListener;

    public ApolloDataSource(String nameSpace, String ruleKey, String defaultRuleValue, RuleSourceConverter<String, T> converter) {
        super(converter);
        Preconditions.checkArgument(!Strings.isNullOrEmpty(ruleKey), "RuleKey could not be null or empty!");
        this.ruleKey = ruleKey;
        this.defaultRuleValue = defaultRuleValue;
        this.config = ConfigService.getConfig(nameSpace);
        this.initialize();
    }

    private void initialize() {
        this.initializeConfigChangeListener();
        this.loadAndUpdateRules();
    }

    private void loadAndUpdateRules() {
        try {
            T newValue = this.loadConfig();
            this.getProperty().updateValue(newValue);
        } catch (Throwable var2) {

        }

    }
    private void initializeConfigChangeListener() {
        this.configChangeListener = new ConfigChangeListener() {
            public void onChange(ConfigChangeEvent changeEvent) {
                ConfigChange change = changeEvent.getChange(ApolloDataSource.this.ruleKey);
                if (change != null) {
                }
                ApolloDataSource.this.loadAndUpdateRules();
            }
        };
        this.config.addChangeListener(this.configChangeListener, Sets.newHashSet(new String[]{this.ruleKey}));
    }

    public String readSource() throws Exception {
        return this.config.getProperty(this.ruleKey, this.defaultRuleValue);
    }

    public void close() throws Exception {
        this.config.removeChangeListener(this.configChangeListener);
    }
}