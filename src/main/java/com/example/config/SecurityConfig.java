package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Настройка безопасности для HTTP запросов
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // Разрешить доступ без аутентификации к страницам / и /home
                .anyRequest().authenticated() // Для всех остальных путей требуется аутентификация
                .and()
                .formLogin()
                .loginPage("/login") // Страница для входа
                .permitAll() // Разрешаем доступ всем к странице логина
                .and()
                .logout()
                .permitAll(); // Разрешаем всем выйти из системы
    }

    // Настройка пользователей в памяти с ролями и паролями
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER") // Простой пользователь
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN"); // Администратор
    }

    // Шифрование паролей
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
