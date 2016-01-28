package com.mushopl.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = "com.mushopl.entity")
@EnableJpaRepositories(basePackages = "com.mushopl.repo")
@EnableTransactionManagement
public class JpaConfig {
}
