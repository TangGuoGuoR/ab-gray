package com.ab.gray.feature.support;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: CompareOperator
 */
public enum CompareOperator {
    LESS("<","LESS"),
    EQUAL("=","EQUAL"),
    MORE(">","MORE");

    private final String rs;
    private final String desc;
    CompareOperator(String rs, String desc) {
        this.rs = rs;
        this.desc = desc;
    }

    public String getRs() {
        return rs;
    }

    public String getDesc() {
        return desc;
    }

    public static CompareOperator valueOfRs(String rs) {
        for (CompareOperator fileTypeEnum : CompareOperator.values()) {
            if (fileTypeEnum.getRs().equals(rs)) {
                return fileTypeEnum;
            }
        }
        return null;
    }
    public static boolean compare(CompareOperator  compareOperator,Long firstValue,Long lastValue){
        if (compareOperator==CompareOperator.LESS){
            return firstValue<lastValue;
        }
        if (compareOperator==CompareOperator.EQUAL){
            return firstValue==lastValue;
        }
        if (compareOperator==CompareOperator.MORE){
            return firstValue>lastValue;
        }
        return false;
    }
}
