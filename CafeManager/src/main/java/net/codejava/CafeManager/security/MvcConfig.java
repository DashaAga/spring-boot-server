package net.codejava.CafeManager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс конфигураций
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cafe").setViewName("home");
        registry.addViewController("/find").setViewName("find");
        registry.addViewController("/dessert-create").setViewName("create");
        registry.addViewController("/coffe-create").setViewName("create");
        registry.addViewController("/lunch-create").setViewName("create");
        registry.addViewController("/breakfast-create").setViewName("create");
        registry.addViewController("/delivery-create").setViewName("create");
        registry.addViewController("/delete").setViewName("delete");
        registry.addViewController("/update").setViewName("update");
    }

}