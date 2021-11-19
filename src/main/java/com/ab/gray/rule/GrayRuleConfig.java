package com.ab.gray.rule;

import java.util.List;
import java.util.Map;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: GrayRuleConfig
 */
public class GrayRuleConfig
{
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public static class  Feature{
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        private String key;
        private boolean enable;
        private boolean unionAll;

        public Map<String, String> getMetaData() {
            return metaData;
        }

        public void setMetaData(Map<String, String> metaData) {
            this.metaData = metaData;
        }

        private Map<String,String> metaData;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public boolean isUnionAll() {
            return unionAll;
        }

        public void setUnionAll(boolean unionAll) {
            this.unionAll = unionAll;
        }

    }
}
