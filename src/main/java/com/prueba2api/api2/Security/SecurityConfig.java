package com.prueba2api.api2.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTUtil jwtUtil;

    public SecurityConfig(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Deshabilitar CSRF para pruebas con JWT
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Permitir acceso a endpoints de autenticacion
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            // Agregar el filtro JWT antes del filtro de autenticación
            .addFilterBefore(new JWTAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean // AuthenticationManager bean para la autenticacion
    // Este bean es necesario para la autenticación basada en formularios y otros metodos de autenticación
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
