package com.riwi.riwiproject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditWareImpl(); // Registrar el proveedor del auditor actual
    }

}
