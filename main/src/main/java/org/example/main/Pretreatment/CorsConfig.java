package org.example.main.Pretreatment;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 添加CORS配置类,测试使用
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 合并路径匹配模式
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                // 合并HTTP方法配置
                .allowedMethods("*")
                // 添加以下配置保证功能完整
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(false)
                .maxAge(3600);
    }
}