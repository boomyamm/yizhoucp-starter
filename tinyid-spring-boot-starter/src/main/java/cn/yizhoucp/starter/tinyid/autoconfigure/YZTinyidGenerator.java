package cn.yizhoucp.starter.tinyid.autoconfigure;

import com.xiaoju.uemc.tinyid.base.entity.YZTinyidConfigProperties;
import com.xiaoju.uemc.tinyid.client.utils.YZTinyId;

import java.util.List;

/**
 * @author yitong
 * @className TinyidGenerator
 * @description TODO
 * @date 2021/2/14
 **/
public class YZTinyidGenerator {

    public YZTinyidGenerator(YZTinyidGeneratorProperties properties) {
        YZTinyidConfigProperties tinyidConfigProperties = new YZTinyidConfigProperties();
        tinyidConfigProperties.setTinyIdToken(properties.getToken());
        tinyidConfigProperties.setTinyIdServer(properties.getServer());
        tinyidConfigProperties.setReadTimeout(properties.getReadTimeout());
        tinyidConfigProperties.setConnectTimeout(properties.getConnectTimeout());
        YZTinyId.init(tinyidConfigProperties);
    }

    public Long nextId(String bizType) {
        return YZTinyId.nextId(bizType);
    }

    public List<Long> nextId(String bizType, Integer batchSize) {
        return YZTinyId.nextId(bizType, batchSize);
    }
}