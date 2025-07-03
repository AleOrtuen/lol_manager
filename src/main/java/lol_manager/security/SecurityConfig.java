package lol_manager.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults()) // ⬅️ CORS abilitato
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers(
                    "/user/**",
                    "/champ/**",
                    "/champ-pool/**",
                    "/team/**",
                    "/team-member/**",
                    "/team-comp/**",
                    "/champ-role/**",
                    "/game/**",
                    "/draft/**",
                    "/ban/**",
                    "/pick/**",
                    "/game-room/**",
                    "/league-role/**",
                    "/ws/**",            
                    "/topic/**",        
                    "/app/**",
                    "/team-analysis/**"
                ).permitAll();
                auth.anyRequest().authenticated();
            })
            .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        // ⬅️ Aggiungi qui i domini autorizzati
        config.setAllowedOrigins(List.of(
            "http://localhost:5173",
            "https://loltm.onrender.com"
        ));
        
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // solo se usi cookie/token

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

