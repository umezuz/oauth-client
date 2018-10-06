package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso
@Configuration
public class SsoSampleApplication extends WebSecurityConfigurerAdapter{
    public static void main(String[] args) {
        SpringApplication.run(SsoSampleApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.httpBasic().disable()
        		.csrf().disable() // CSRF対策を無効化
                .authorizeRequests()
                .antMatchers("/", "/hello/**").permitAll() // /api以下のパスを認証なしで利用できるようにする
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/").permitAll(); // ログアウト機能の設定
    }

}

