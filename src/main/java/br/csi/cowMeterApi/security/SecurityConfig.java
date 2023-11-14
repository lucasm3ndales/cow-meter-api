package br.csi.cowMeterApi.security;

import br.csi.cowMeterApi.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFilter authFilter;

    public SecurityConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/**").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/usuario/**").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/usuario/**").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/raca/saveRaca").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/raca/updateRaca").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/raca/saveRaca").hasAuthority(Usuario.Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/raca/getRaca").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.GET, "/raca/getAllRacas").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.POST, "/bovino/saveBovino").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/bovino/updateBovino").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.DELETE, "/bovino/deleteBovino").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.GET, "/bovino/getBovino").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.GET, "/bovino/getAllBovinos").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.POST, "/saude/saveSaude").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .requestMatchers(HttpMethod.PUT, "/saude/updateSaude").hasAnyAuthority(Usuario.Role.ADMIN.name(), Usuario.Role.CRIADOR.name())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
