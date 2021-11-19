# ab-gray
ab-gray
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
