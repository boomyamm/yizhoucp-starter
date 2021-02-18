> 通用且非必需的组件, 尽量抽象成可插拔式的starter, 提供给业务方使用
> 1. 降低类似功能每个微服务都写一份的维护成本
> 2. 降低micro-service-core的复杂度, 减轻各微服务对其的依赖

### yizhoucp-starter 自定义spring-boot-starter合集
- [tinyid-spring-boot-starter](https://git.yizhoucp.cn/microservices/yizhoucp-starter/-/tree/master/tinyid-spring-boot-starter) 基于[Tinyid](https://git.yizhoucp.cn/microservices/tinyid/-/tree/dev) ([官方版本](https://github.com/didi/tinyid)) 分布式全局唯一ID生成器 