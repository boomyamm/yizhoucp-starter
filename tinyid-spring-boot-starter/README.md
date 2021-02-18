> 通用且非必需的组件, 尽量抽象成可插拔式的starter, 提供给业务方使用
> 1. 降低类似功能每个微服务都写一份的维护成本
> 2. 降低micro-service-core的复杂度, 减轻各微服务对其的依赖

### [tinyid-spring-boot-starter](https://git.yizhoucp.cn/microservices/yizhoucp-starter/-/tree/master/tinyid-spring-boot-starter) 
> [tinyid-spring-boot-starter](https://git.yizhoucp.cn/microservices/yizhoucp-starter/-/tree/master/tinyid-spring-boot-starter) 基于[Tinyid](https://git.yizhoucp.cn/microservices/tinyid/-/tree/dev) ([官方版本](https://github.com/didi/tinyid)) 分布式全局唯一ID生成器
#### 功能点
1. 抽象为spring-boot-starter
2. 提供 REST API / Java Client 两种调用方式
3. 支持JPA调用
4. 支持多环境Profile配置文件

#### 使用方式
##### Java Client
1. 对照官方文档在本地启动tinyid-server [Getting started](https://github.com/didi/tinyid/wiki/Getting-started)
2. 添加maven依赖 
    ```xml
   <dependency>
        <groupId>cn.yizhoucp</groupId>
        <artifactId>tinyid-spring-boot-starter</artifactId>
        <version>1.1.5-SNAPSHOT</version>
   </dependency>
   ```
3. 增加配置信息
    ```yaml
    # application-dev.yml
    tinyid:
      enabled: true # 开关, false或者忽略, 都不会开启tinyid功能
      server: localhost:9999 #多server的配置用","分隔，server部署后如果带后缀，可配置在后端后
      token: 0f673adf80504e2eaa552f5d791b644c
      # 如下两个是连接server的两个可选参数，单位是毫秒，默认是5000
      readTimeout: 3000
      connectTimeout: 3000
    ```
4. 启动并确认配置成功
    ```
    ============== YZTinyidAutoConfigure init Bean ===============
    token:0f673adf80504e2eaa552f5d791b644c, server:localhost:9999, readTimeout:3000, connectTimeout:3000, enabled:true
    ```
5. 调用
    ```java
    // DemoController.java
   @RestController
    @Slf4j
    public class DemoController {
        @Resource
        YZTinyidGenerator tinyidGenerator;
        
        @GetMapping("/api/demo/nextId")
        public Result nextId() {
            return RestBusinessTemplate.executeWithoutTransaction(() -> tinyidGenerator.nextId("test"));
        }

        @GetMapping("/api/demo/nextBatchId")
        public Result nextBatchId() {
            return RestBusinessTemplate.executeWithoutTransaction(() -> tinyidGenerator.nextId("test", 10));
        }
    }
    ```
6. 完整demo [DemoController.java](https://git.yizhoucp.cn/microservices/demo-services/-/tree/feature/20210214-test-starter/src/main/java/cn/yizhoucp/demo/api/project/web/rest/controller)

##### 支持JPA
1. 操作同上, 1, 2, 3, 4
2. 修改DO实体ID的生成规则
    ```java
    @Data
    @Entity
    @Table(name = "tb_user")
    public class UserDO {
    
        @Id
        @GeneratedValue(generator = "user-id-generator")
        @GenericGenerator(
                name = "user-id-generator",
                parameters = @Parameter(name = "bizType", value = "test"),
                strategy = "cn.yizhoucp.starter.tinyid.autoconfigure.YZTinyidGeneratorJPA"
        )
        private Long id;
        private String username;
        private String password;
    }
    ```
3. 完整单元测试demo [YZTinyidJpaDAOTest.java](https://git.yizhoucp.cn/microservices/demo-services/-/blob/feature/20210214-test-starter/src/test/java/cn/yizhoucp/demo/api/project/dal/jpa/dao/YZTinyidJpaDAOTest.java)
    
 