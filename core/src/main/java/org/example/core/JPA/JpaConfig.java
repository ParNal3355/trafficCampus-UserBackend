package org.example.core.JPA;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "org.example.core.JPA.entities")
@EnableJpaRepositories(
    basePackages = "org.example.core.JPA.repositories")
public class JpaConfig {
    // 可选：配置Hibernate属性
    // @Bean
    // public JpaProperties jpaProperties() {...}
}
