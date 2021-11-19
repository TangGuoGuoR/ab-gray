package com.ab.gray.feature.support;

import com.ab.gray.feature.AbstractFeature;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: NumberRangeFeature
 */
public class NumberRangeFeature extends AbstractFeature<RangeSet<Long>> {

    protected RangeSet<Long> doParseFeature(String featureDesc) {
        RangeSet<Long> rangeSet= TreeRangeSet.create();
        Long start=Long.parseLong(featureDesc.split("-")[0]);
        Long end=Long.parseLong(featureDesc.split("-")[1]);
        rangeSet.add(Range.closed(start,end));
        return rangeSet;
    }

    @Override
    protected boolean doCheck(Object value) {
        return this.getFeature().contains(Long.parseLong(value.toString()));
    }

}
