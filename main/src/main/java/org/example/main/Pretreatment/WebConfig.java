package org.example.main.Pretreatment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Arrays;
import java.util.List;

//拦截器配置类
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${security.allowed-ips}")
    private String[] allowedIps;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> ipList = Arrays.asList(allowedIps);
        registry.addInterceptor(new IpAuthInterceptor(ipList))
                .addPathPatterns("/api/**");      // 拦截所有API路径
    }
}
