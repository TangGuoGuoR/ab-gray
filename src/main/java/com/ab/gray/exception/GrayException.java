package com.ab.gray.exception;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: GrayException
 */
public class GrayException extends RuntimeException {

    protected String codeStr;
    protected Integer Code;

    public GrayException(String msg) {
        super(msg);
        this.Code = 500;
    }

    public GrayException(String msg, Integer bizCode) {
        super(msg);
        this.Code = bizCode;
    }

    public String getCodeStr() {
        return this.codeStr;
    }

    public Integer getCode() {
        return this.Code;
    }
}
