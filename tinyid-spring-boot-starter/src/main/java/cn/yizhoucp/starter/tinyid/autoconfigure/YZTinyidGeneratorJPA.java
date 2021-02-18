package cn.yizhoucp.starter.tinyid.autoconfigure;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Properties;

/**
 * @author yitong
 * @className YZTinyidGeneratorJPA
 * @description TODO
 * @date 2021/2/18
 **/
public class YZTinyidGeneratorJPA implements IdentifierGenerator, Configurable {
    protected static YZTinyidGenerator yzTinyidGenerator;
    private String bizType;
//    protected static void setYZTinyidGenerator(YZTinyidGenerator yzTinyidGenerator){
//        YZTinyidGeneratorJPA.yzTinyidGenerator = yzTinyidGenerator;
//    }
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        bizType = properties.getProperty("bizType");
//        System.out.println("==========================YZTinyidGeneratorJPA bizType:" + bizType);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
//        System.out.println("==========================YZTinyidGeneratorJPA YZTinyidGenerator:" + yzTinyidGenerator);
        return yzTinyidGenerator.nextId(bizType);
    }
}
