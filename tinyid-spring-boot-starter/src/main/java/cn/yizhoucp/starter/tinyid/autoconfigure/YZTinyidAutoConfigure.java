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
@ConditionalOnClass(YZTinyidGenerator.class)
@EnableConfigurationProperties(YZTinyidGeneratorProperties.class)
public class YZTinyidAutoConfigure {

    private final YZTinyidGeneratorProperties properties;
    public YZTinyidAutoConfigure(YZTinyidGeneratorProperties properties) {
        this.properties = properties;
    }

    private volatile YZTinyidGenerator tinyidGenerator = null;

    @PostConstruct
    private void init(){
//        System.out.println("============== TinyidAutoConfigure init ===============");
//        System.out.println("token:" + properties.getToken() + ", server:" + properties.getServer() + ", readTimeout:" + properties.getReadTimeout() + ", connectTimeout:" + properties.getConnectTimeout() + ", enabled:" + properties.isEnabled());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "tinyid", name = "enabled", havingValue = "true", matchIfMissing = false)
    public YZTinyidGenerator getTinyidGenerator() {
        if(tinyidGenerator == null){
            synchronized (YZTinyidAutoConfigure.class){
                if(tinyidGenerator != null){
                    return tinyidGenerator;
                }
                System.out.println("============== YZTinyidAutoConfigure init Bean ===============");
                System.out.println("token:" + properties.getToken() + ", server:" + properties.getServer() + ", readTimeout:" + properties.getReadTimeout() + ", connectTimeout:" + properties.getConnectTimeout() + ", enabled:" + properties.isEnabled());
                tinyidGenerator =  new YZTinyidGenerator(properties);

                YZTinyidGeneratorJPA.yzTinyidGenerator = tinyidGenerator;
            }
        }
        return tinyidGenerator;
    }

}

