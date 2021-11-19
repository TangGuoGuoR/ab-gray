package com.ab.gray.feature.support;

import com.ab.gray.exception.GrayException;
import com.ab.gray.feature.AbstractFeature;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: NumberHashFeature
 */
public class NumberHashFeature extends AbstractFeature<Long> {

    private static final long  hashValue= 100;
    private CompareOperator compareOperator;


    protected Long doParseFeature(String featureDesc) {
        String operator=featureDesc.substring(0,1);
        compareOperator=CompareOperator.valueOfRs(operator);
        if (null==compareOperator){
            throw new GrayException("compareOperator parse error");
        }
        return Long.parseLong(featureDesc.substring(1,featureDesc.length()-1));
    }

    @Override
    protected boolean doCheck(Object value) {
        Long firstValue=Long.parseLong(value.toString())%hashValue;
        return CompareOperator.compare(compareOperator,firstValue,this.getFeature());
    }




}
