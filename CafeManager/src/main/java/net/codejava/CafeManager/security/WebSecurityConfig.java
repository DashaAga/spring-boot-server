package net.codejava.CafeManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс конфигураций доступа пользователей.
 * Определение доступа к страницам, опираясь на роли пользователей
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                        .antMatchers("/cafe").permitAll()
                        .antMatchers("/find/**").permitAll()
                        .antMatchers("/delete/**").hasRole("admin")
                        .antMatchers("/coffe-create").hasRole("admin")
                        .antMatchers("/lunch-create").hasRole("admin")
                        .antMatchers("/dessert-create").hasAnyRole("confectioner", "admin")
                        .antMatchers("/breakfast-create").hasRole("admin")
                        .antMatchers("/delivery-create").hasAnyRole("delivery", "admin")
                        .antMatchers("/update/**").hasAnyRole("confectioner", "admin", "delivery")
                )
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    // Создаем объект DatabaseQuery

    @Bean
    public UserDetailsService userDetailsService() {
        DatabaseQuery dbQuery = new DatabaseQuery();

        // Получаем коллекцию данных из базы данных
        List<List> dataCollection = dbQuery.getData();
        List<UserDetails> details = new ArrayList<UserDetails>();

        // Выводим данные
        for (List data: dataCollection) {
            details.add(User.withDefaultPasswordEncoder()
                    .username(data.get(0).toString())
                    .password(data.get(1).toString())
                    .roles(data.get(2).toString())
                    .build());
        }
        return new InMemoryUserDetailsManager(details);
    }
}
