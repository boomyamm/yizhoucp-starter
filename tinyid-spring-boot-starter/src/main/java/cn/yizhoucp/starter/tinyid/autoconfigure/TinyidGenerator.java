package cn.yizhoucp.starter.tinyid.autoconfigure;

import com.xiaoju.uemc.tinyid.base.entity.YZTinyidConfigProperties;
import com.xiaoju.uemc.tinyid.base.generator.IdGenerator;
import com.xiaoju.uemc.tinyid.client.utils.YZTinyId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yitong
 * @className TinyidGenerator
 * @description TODO
 * @date 2021/2/14
 **/
public class TinyidGenerator {

    public TinyidGenerator(TinyidGeneratorProperties properties) {
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