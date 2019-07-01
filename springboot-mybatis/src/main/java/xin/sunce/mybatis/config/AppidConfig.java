package xin.sunce.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author lowrie
 * @date 2019-03-25
 */
@Component
@ConfigurationProperties(prefix = "test")
public class AppidConfig {

    private Map<String, String> map = new HashMap<>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", AppidConfig.class.getSimpleName() + "[", "]")
                .add("map=" + map)
                .toString();
    }
}
