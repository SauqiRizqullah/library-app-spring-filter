    package com.example.spring_filter.security;

    import jakarta.servlet.DispatcherType;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.ReflectiveScan;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.Customizer;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    @RequiredArgsConstructor
    public class SecurityConfig {

        private final AuthenticationFilter authenticationFilter;

        @Bean
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((requests) -> requests
                    .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/books", "/api/v1/books/*").permitAll()
                            .requestMatchers("/api/v1/auth/**").permitAll().anyRequest().authenticated());
            httpSecurity.sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            httpSecurity.httpBasic(Customizer.withDefaults());
            httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }
    }
