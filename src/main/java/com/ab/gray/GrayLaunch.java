package com.ab.gray;

import com.ab.gray.exception.GrayException;
import com.ab.gray.feature.IFeature;
import com.ab.gray.feature.ObjectFeatureFactory;
import com.ab.gray.property.DynamicGrayProperty;
import com.ab.gray.property.GrayProperty;
import com.ab.gray.property.PropertyListener;
import com.ab.gray.rule.DefaultRule;
import com.ab.gray.rule.GrayRuleConfig;
import com.ab.gray.rule.IRule;
import com.ab.gray.trace.ITraceRepository;
import com.ab.gray.trace.InMemoryTraceRepository;
import com.ab.gray.trace.TraceEntry;
import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: tang@gray.com
 * Date: 2021/11/18
 * Description: GrayLaunch
 */
public class GrayLaunch {
    private static Map<String, IRule> grayMapRules =new ConcurrentHashMap<>();
    private static GrayProperty<GrayRuleConfig> currentProperty = new DynamicGrayProperty<>();
    private static final GrayLaunch.GrayPropertyListener LISTENER = new GrayLaunch.GrayPropertyListener();
    private static ITraceRepository currentTraceRepository=new InMemoryTraceRepository();

    public static void registerRules(GrayProperty<GrayRuleConfig> property,ITraceRepository traceRepository){
        currentProperty.removeListener(LISTENER);
        property.addListener(LISTENER);
        currentProperty = property;
        currentTraceRepository=traceRepository;
    }
    public static void registerRules(GrayProperty<GrayRuleConfig> property){
        currentProperty.removeListener(LISTENER);
        property.addListener(LISTENER);
        currentProperty = property;
    }

    private  static class  GrayPropertyListener implements PropertyListener<GrayRuleConfig> {

        @Override
        public void configUpdate(GrayRuleConfig value) {
            grayMapRules.clear();
            grayMapRules.putAll(parseRule(value));
        }

        @Override
        public void configLoad(GrayRuleConfig value) {
            grayMapRules.clear();
            grayMapRules.putAll(parseRule(value));

        }
        private  static Map<String,IRule>  parseRule(GrayRuleConfig grayRuleConfig){
            Map<String,IRule> grayTempMapRules =new ConcurrentHashMap<>();
            for (GrayRuleConfig.Feature feature:grayRuleConfig.getFeatures()){
                if (feature.isEnable()) {
                    DefaultRule grayRule = new DefaultRule(currentTraceRepository);
                    List<IFeature> features = Lists.newArrayList();
                    Map<String, String> featureMetaData = feature.getMetaData();
                    Iterator<Map.Entry<String, String>> iterator = featureMetaData.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        features.add(ObjectFeatureFactory.getFeature(entry.getKey(), entry.getValue()));
                    }
                    grayRule.setFeatures(features);
                    grayRule.setKey(feature.getKey());
                    grayRule.setEnable(feature.isEnable());
                    grayRule.setUnionAll(feature.isUnionAll());
                    grayTempMapRules.put(grayRule.getKey(), grayRule);
                }
            }
            return grayTempMapRules;

        }
    }

    public static boolean check(String key, Object value){
        IRule rule=grayMapRules.get(key);
        if (null==rule){
            throw new GrayException("rule cannot be found");
        }
       return rule.check(value);
    }
    public static Map<String, TraceEntry> getAllTrace(){
        return currentTraceRepository.selectAll();
    }
    public static TraceEntry getOneTrace(String key){
        return currentTraceRepository.selectOne(key);
    }


}
