package com.ab.gray.rule;

import com.ab.gray.trace.ITraceRepository;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: AbstractRule
 */
public abstract class AbstractRule implements IRule {
    private final ITraceRepository traceRepository;

    public AbstractRule(ITraceRepository traceRepository) {
        this.traceRepository = traceRepository;
    }

    public boolean check(Object value) {
        boolean result = doCheck(value);
        beforeGrayCheck(value);
        try {
            if (result) {
                execResultPass();
            } else {
                execResultNoPass();
            }
            afterGrayCheck(value, result);
        } catch (Exception ex) {
            execGrayErrorAfter(ex, value);
        }

        return result;
    }

    protected void beforeGrayCheck(Object value) {
        System.out.println(this.getClass() + ";beforeGrayCheck:" + value);
    }

    protected void afterGrayCheck(Object value, boolean result) {
        System.out.println(this.getClass() + ";afterGrayCheck:" + value);

    }

    protected void execGrayErrorAfter(Exception ex, Object value) {
        //todo log
        System.out.println(this.getClass() + ";execGrayErrorAfter:" + value + "ex:" + ex);

    }

    private void execResultPass() {
        traceRepository.pass(this.getKey());
    }

    private void execResultNoPass() {
        traceRepository.noPass(this.getKey());
    }
    public abstract boolean doCheck(Object targetValue);
}
