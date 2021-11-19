package com.ab.gray.feature;

import com.ab.gray.exception.GrayException;
import com.ab.gray.trace.ITraceRepository;
import com.ab.gray.util.StringUtils;

/**
 * User: tang@gray.com
 * Date: 2021/11/18
 * Description: AbstractFeature
 */
public abstract class AbstractFeature<F> implements IFeature<F>  {
    private ITraceRepository traceRepository;
    private F feature;

    public void setTraceRepository(ITraceRepository traceRepository){
        this.traceRepository=traceRepository;
    }
    public ITraceRepository getTraceRepository(){
        return this.traceRepository;
    }
    protected IFeature buildFeature(String featureDesc){
        return buildFeature(featureDesc,null);
    }
    protected IFeature buildFeature(String featureDesc,ITraceRepository traceRepository){
        this.feature=parseFeature(featureDesc);
        this.traceRepository=traceRepository;
        return this;
    }
    public F getFeature() {
        return this.feature;
    }

    public boolean check(Object targetValue) {
       return doCheck(targetValue);
    }
    private F parseFeature(String featureDesc){
        if (StringUtils.isEmpty(featureDesc)){
            throw new GrayException("featureDesc can not be null");
        }
       return doParseFeature(featureDesc);
    }

    protected abstract F doParseFeature(String featureDesc);
    protected abstract boolean doCheck(Object value);


}
