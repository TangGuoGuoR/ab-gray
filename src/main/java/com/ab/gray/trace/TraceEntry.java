package com.ab.gray.trace;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: TraceEntry
 */
public class TraceEntry {

    public String getKey() {
        return key;
    }

    private final String key;

    public long getGrayPassCount() {
        return grayPassCount;
    }

    private long grayPassCount;

    public long getGrayNoPassCount() {
        return grayNoPassCount;
    }

    private long grayNoPassCount;

     TraceEntry(String key){
        this.key=key;
    }

    public void incPassCount(){
        this.grayPassCount+=1;
    }
    public void incNoPassCount(){
        this.grayNoPassCount+=1;
    }

}
