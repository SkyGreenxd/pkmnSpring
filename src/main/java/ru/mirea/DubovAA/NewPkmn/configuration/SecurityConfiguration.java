package ru.mirea.DubovAA.NewPkmn.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mirea.DubovAA.NewPkmn.security.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService jdbcUserDetailsManager;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                customizer -> customizer
                        .requestMatchers(HttpMethod.GET,
                                "/api/v1/cards",
                                "/api/v1/cards/{name}",
                                "/api/v1/cards/owner")
                        .permitAll()

                        .requestMatchers(HttpMethod.POST,
                                "/api/v1/cards",
                                "/api/v1/users"
                                ).hasRole("ADMIN")
                        .requestMatchers("/api/v1/auth/register").permitAll()
                        .requestMatchers("/api/v1/auth/**").authenticated()
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated()
        );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(form -> form.successForwardUrl("/api/v1/auth/success"));
        http.userDetailsService(jdbcUserDetailsManager);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
