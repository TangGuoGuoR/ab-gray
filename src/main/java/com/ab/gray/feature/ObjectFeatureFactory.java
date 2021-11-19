package com.ab.gray.feature;

import com.ab.gray.feature.support.NumberHashFeature;
import com.ab.gray.feature.support.NumberRangeFeature;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: ObjectFeatureFactory
 */
public class ObjectFeatureFactory {
    public static IFeature getFeature(String featureName,String featureDesc){
        if (featureName.equals("hash")){
            return new NumberHashFeature().buildFeature(featureDesc);
        }
        if (featureName.equals("range")) {
            return new NumberRangeFeature().buildFeature(featureDesc);
        }
        return null;
    }
}
