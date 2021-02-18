package cn.yizhoucp.starter.tinyid.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author yitong
 * @className TinyidAutoConfigure
 * @description TODO
 * @date 2021/2/14
 **/
@Configuration
@ConditionalOnClass(TinyidGenerator.class)
@EnableConfigurationProperties(TinyidGeneratorProperties.class)
public class TinyidAutoConfigure {

    private final TinyidGeneratorProperties properties;
    public TinyidAutoConfigure(TinyidGeneratorProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    private void init(){
//        System.out.println("============== TinyidAutoConfigure init ===============");
//        System.out.println("token:" + properties.getToken() + ", server:" + properties.getServer() + ", readTimeout:" + properties.getReadTimeout() + ", connectTimeout:" + properties.getConnectTimeout() + ", enabled:" + properties.isEnabled());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "tinyid", name = "enabled", havingValue = "true", matchIfMissing = false)
    TinyidGenerator getTinyidGenerator() {
        System.out.println("============== TinyidAutoConfigure init Bean ===============");
        System.out.println("token:" + properties.getToken() + ", server:" + properties.getServer() + ", readTimeout:" + properties.getReadTimeout() + ", connectTimeout:" + properties.getConnectTimeout() + ", enabled:" + properties.isEnabled());
        return new TinyidGenerator(properties);
    }

}

