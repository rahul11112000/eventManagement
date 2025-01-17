package com.example.assignment.configration;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import jakarta.servlet.DispatcherType;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RestConfig {

   @Value("${jwt.public.key}")
   private RSAPublicKey key;

   @Value("${jwt.private.key}")
   private RSAPrivateKey priv;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      // @formatter:off
      http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((authorize) -> authorize
               .requestMatchers("/admin/**").hasAuthority("ADMIN")
               .requestMatchers("/venue/**").hasAuthority("Venue")
               .requestMatchers("/organizer/**").hasAuthority("Organizer")
               .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
               .requestMatchers("/signup").permitAll()
               .requestMatchers(
                    "/v3/api-docs/**",   // OpenAPI documentation
                    "/swagger-ui/**",    // Swagger UI static resources
                    "/swagger-ui.html"   // Swagger UI HTML page
                ).permitAll()
               .anyRequest().authenticated()
            )
            .csrf((csrf) -> csrf.ignoringRequestMatchers("/token"))
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((exceptions) -> exceptions
                  .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                  .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
            );
      // @formatter:on
      return http.build();
   }

   @Bean
   JwtDecoder jwtDecoder() {
      return NimbusJwtDecoder.withPublicKey(this.key).build();
   }

   @Bean
   JwtEncoder jwtEncoder() {
      JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
      JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
      return new NimbusJwtEncoder(jwks);
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   private JwtAuthenticationConverter jwtAuthenticationConverter() {
      JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

      converter.setJwtGrantedAuthoritiesConverter(jwt -> {
         JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
         authoritiesConverter.setAuthoritiesClaimName("scope"); // Use "role" claim in JWT
         authoritiesConverter.setAuthorityPrefix(""); // No prefix added
         return authoritiesConverter.convert(jwt);
      });

      return converter;
   }

}