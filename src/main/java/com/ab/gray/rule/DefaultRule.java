package com.ab.gray.rule;

import com.ab.gray.feature.IFeature;
import com.ab.gray.trace.ITraceRepository;

import java.util.List;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: DefaultRule
 */
public class DefaultRule extends AbstractRule {
    private String key;
    private boolean enable;

    public boolean isUnionAll() {
        return unionAll;
    }

    public void setUnionAll(boolean unionAll) {
        this.unionAll = unionAll;
    }

    private boolean unionAll;

    public DefaultRule(ITraceRepository traceRepository) {
        super(traceRepository);
    }
    public void setKey(String key){
        this.key=key;
    }
    public void setEnable(boolean enable){
        this.enable=enable;
    }
    public boolean enable() {
        return this.enable;
    }
    public String getKey() {
        return this.key;
    }

    private List<IFeature> features;
    public void setFeatures(List<IFeature> features){
        this.features=features;
    }
    public List<IFeature> getFeatures() {
        return this.features;
    }

    public boolean doCheck(Object targetValue) {
        boolean result=false;
        for (IFeature feature:this.features){
            result=feature.check(targetValue);
            if (result&&!unionAll){
                break;
            }
            if (!result&&unionAll){
                break;
            }
        }
        return result;
    }


}
