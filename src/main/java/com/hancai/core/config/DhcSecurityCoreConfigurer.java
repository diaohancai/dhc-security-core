package com.hancai.core.config;

import com.hancai.core.module.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author diaohancai
 */
@Configuration
public class DhcSecurityCoreConfigurer {

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
