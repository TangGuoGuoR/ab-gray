package com.ab.gray;

import com.ab.gray.datasource.IGrayDataSource;
import com.ab.gray.datasource.support.ApolloDataSource;
import com.ab.gray.datasource.support.MemoryDataSource;
import com.ab.gray.property.GrayProperty;
import com.ab.gray.rule.GrayRuleConfig;
import com.ab.gray.trace.TraceEntry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: tang@gray.com
 * Date: 2021/11/19
 * Description: AppMain
 */
public class AppMain {
    public static void main(String[] args) {

        String rules = "{\"features\":[{\"metaData\":{\"range\":\"40-50\",\"hash\":\">30\"},\"unionAll\":true,\"enable\":true,\"key\":\"test0\"},{\"metaData\":{\"range\":\"40-50\",\"hash\":\">30\"},\"unionAll\":true,\"enable\":true,\"key\":\"test1\"}]}";

        //apollo
        //IGrayDataSource grayDataSource=new ApolloDataSource<GrayRuleConfig>("application","gray_key","", source -> JSON.parseObject(source,GrayRuleConfig.class));
        //GrayLaunch.registerRules(grayDataSource.getProperty());

        //memory
        IGrayDataSource grayDataSource = new MemoryDataSource<GrayRuleConfig>(rules, source -> JSON.parseObject(source, GrayRuleConfig.class));
        GrayLaunch.registerRules(grayDataSource.getProperty());

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                TraceEntry traceEntry = GrayLaunch.getOneTrace("test0");
                System.out.println("trace,key:" + traceEntry.getKey() + ";passCount:" + traceEntry.getGrayPassCount() + ";noPassCount:" + traceEntry.getGrayNoPassCount());
            }
        }, 3, 3, TimeUnit.SECONDS);

        while (true) {
            boolean result = GrayLaunch.check("test0", (int) (Math.random() * 100));
            System.out.println(result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }

    }

}
