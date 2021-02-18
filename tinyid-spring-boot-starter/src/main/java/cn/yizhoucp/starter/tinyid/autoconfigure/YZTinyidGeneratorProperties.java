package cn.yizhoucp.starter.tinyid.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yitong
 * @className TinyidGeneratorProperties
 * @description TODO
 * @date 2021/2/14
 **/
@ConfigurationProperties(prefix = "tinyid", ignoreUnknownFields = false)
public class YZTinyidGeneratorProperties {
    private boolean enabled;
    private String token;
    private String server;
    private String readTimeout;
    private String connectTimeout;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(String readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}

