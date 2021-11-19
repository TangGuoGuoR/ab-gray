# ab-gray 
## 1. ab-gray 是什么？
ab-gray 是为A/B testing 或 需要做灰度规则提供了一套非常简介的Java API ，支持动态规则，动态规则数据源支持常用到配置中心，例如apollo。后续将支持nacos、zk、consul、Eureka等数据源。


## 2.ab-gray的优势
2.1 可根据业务需要自定义复杂的规则

2.2 规则的执行结果可跟踪，默认的跟踪存储方式为Memory，后续会支持ES、Redis、DB等存储方式，也可以按需自己实现跟踪结果的存储。

2.3 常见的灰度规则都已内置，例如range、mod、array、固定值等规则

## 3.ab-gray如何使用

       String rules = "{\"features\":[{\"metaData\":{\"range\":\"40-50\",\"hash\":\">30\"},\"unionAll\":true,\"enable\":true,\"key\":\"test0\"},{\"metaData\":{\"range\":\"40-50\",\"hash\":\">30\"},\"unionAll\":true,\"enable\":true,\"key\":\"test1\"}]}";

规则解释：

key：test0，调用规则的唯一标识

enable：true，规则是否启用

unionAll：true，所有feature都返回true，结果才为true

features：规则的所有需求

metaData：{\"range\":\"40-50\",\"hash\":\">30\"}， range：40-50,检查值为40之50之间，hash：>30，检查值和100取余大于30 

       //内存数据源
       IGrayDataSource grayDataSource = new MemoryDataSource<GrayRuleConfig>(rules, source -> JSON.parseObject(source, GrayRuleConfig.class));
       GrayLaunch.registerRules(grayDataSource.getProperty());
       
       //apollo 数据源
       IGrayDataSource grayDataSource=new ApolloDataSource<GrayRuleConfig>("application","gray_key","", source -> JSON.parseObject(source,GrayRuleConfig.class));
       GrayLaunch.registerRules(grayDataSource.getProperty());

       //调用规则执行结果
       boolean result =GrayLaunch.check("test0", 45) 
       
 ![image](https://user-images.githubusercontent.com/4375219/142654176-a301dbc5-dfea-4f61-8374-197f9b738b6b.png)

 ![image](https://user-images.githubusercontent.com/4375219/142653699-fc84c84f-9042-4250-a929-23fe10ace23c.png)


       
