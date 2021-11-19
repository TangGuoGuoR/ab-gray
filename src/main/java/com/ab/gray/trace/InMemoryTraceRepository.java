package com.ab.gray.trace;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: InMemoryTraceRepository
 */
public class InMemoryTraceRepository implements ITraceRepository {

    private static final Map<String,TraceEntry>  traces=new ConcurrentHashMap<>();

    @Override
    public void pass(String key) {
        synchronized (key) {
            TraceEntry traceEntry = null;
            if (traces.containsKey(key)) {
                traceEntry = traces.get(key);
            } else {
                traceEntry = new TraceEntry(key);
            }
            traceEntry.incPassCount();
            traces.put(key, traceEntry);
        }
    }

    @Override
    public void noPass(String key) {
        synchronized (key) {
            TraceEntry traceEntry = null;
            if (traces.containsKey(key)) {
                traceEntry = traces.get(key);
            } else {
                traceEntry = new TraceEntry(key);
            }
            traceEntry.incNoPassCount();
            traces.put(key, traceEntry);
        }
    }

    @Override
    public Map<String, TraceEntry> selectAll() {
        return traces;
    }


    @Override
    public TraceEntry selectOne(String key) {
        return traces.get(key);
    }
}
