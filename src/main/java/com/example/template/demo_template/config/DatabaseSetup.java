package com.example.template.demo_template.config;

import com.example.template.demo_template.model.User;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Configuration testins release-please
// @EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
// @EntityScan(basePackageClasses = {User.class, Jsr310JpaConverters.class})
// @EnableJpaAuditing(auditorAwareRef = "auditor")
public class DatabaseSetup {

    // @Bean
    // public AuditorAware<User> auditor() {
    //     return () -> SecurityUtil.currentUser();
    // }

}
