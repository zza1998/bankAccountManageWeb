package com.zza.jpaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private CorsConfiguration buildConfig(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader(CorsConfiguration.ALL); // 允许所以请求头
        configuration.addAllowedMethod(CorsConfiguration.ALL); // 允许所有方法
        configuration.addAllowedOrigin(CorsConfiguration.ALL); // 允许所有源
        return configuration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
