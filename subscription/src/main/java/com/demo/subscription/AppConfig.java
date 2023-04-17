package com.demo.subscription;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import java.util.Arrays;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    @ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
    DataSource realDataSource() {
        DataSource dataSource = DataSourceBuilder
                .create(this.dataSourceProperties.getClassLoader())
                .url(this.dataSourceProperties.getUrl())
                .username(this.dataSourceProperties.getUsername())
                .password(this.dataSourceProperties.getPassword())
                .build();
        return dataSource;
    }

    @Bean
    @Primary
    DataSource dataSource() {
        return new DataSourceSpy(realDataSource());
    }
    
//    @Configuration
//    @EnableWebSecurity
//    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.cors().and().csrf().disable();
//        }
//
//        @Bean
//        public CorsConfigurationSource corsConfigurationSource() {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Arrays.asList("*"));
//            configuration.setAllowedMethods(Arrays.asList("*"));
//            configuration.setAllowedHeaders(Arrays.asList("*"));
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            source.registerCorsConfiguration("/**", configuration);
//            return source;
//        }
//    }

}