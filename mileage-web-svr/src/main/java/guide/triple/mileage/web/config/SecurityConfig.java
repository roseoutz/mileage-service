package guide.triple.mileage.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Value(value = "${spring.active.profiles:test}")
    private String springProfile;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return getSecurityConfig(httpSecurity)
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .sessionManagement().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .build();
    }

    private HttpSecurity getSecurityConfig(HttpSecurity httpSecurity) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/events").permitAll()
                .antMatchers(HttpMethod.GET, "/get/*").permitAll();

        if (springProfile.equalsIgnoreCase("test")) {
            urlRegistry
                    .antMatchers("/h2/**").permitAll();
        }

        if (!springProfile.equalsIgnoreCase("prod")) {
            urlRegistry
                    .antMatchers("/swagger.html").permitAll()
                    .antMatchers("/api/docs").permitAll();
        }

        return urlRegistry
                .and();
    }
}
