package xin.sunce.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.sunce.mybatis.config.AppidConfig;

@MapperScan("xin.sunce.mybatis.dao")
@SpringBootApplication
@Controller
public class Application {

    @ResponseBody
    @RequestMapping("/test")
    public String testAop() {
        return "success";
    }


    @Autowired
    private AppidConfig config;

    @ResponseBody
    @RequestMapping("/config/{name}")
    public String testConfig(@PathVariable("name") String name) {
        return config.getMap().get(name);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
