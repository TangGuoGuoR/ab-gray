package com.ab.gray.trace;

import java.util.Map;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: ITraceRepository
 */
public interface ITraceRepository {
    void pass(String key);
    void noPass(String key);
    Map<String,TraceEntry> selectAll();
    TraceEntry selectOne(String key);
}
