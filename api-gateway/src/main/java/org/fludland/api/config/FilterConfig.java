package org.fludland.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    public FilterConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilterRegistrationBean() {
        FilterRegistrationBean<JwtAuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(jwtAuthFilter);
        filterRegistrationBean.setOrder(1);

        return filterRegistrationBean;
    }
}
