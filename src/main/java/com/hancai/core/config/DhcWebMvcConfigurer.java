package com.hancai.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author diaohancai
 */
@Configuration
public class DhcWebMvcConfigurer extends WebMvcConfigurerAdapter {

    private DhcSecurityCoreProperties dhcSecurityCoreProperties;

    @Autowired
    public DhcWebMvcConfigurer(DhcSecurityCoreProperties dhcSecurityCoreProperties) {
        this.dhcSecurityCoreProperties = dhcSecurityCoreProperties;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 自定义欢迎页
        registry.addViewController("/").setViewName("forward:" + dhcSecurityCoreProperties.getWelcomePage() + "");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        super.addViewControllers(registry);
    }
}
