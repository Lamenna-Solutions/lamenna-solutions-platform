package app.lamenna.commons.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class, app.lamenna.commons.security.SecurityProperties.class})
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer currentUserWebMvcConfigurer(UserContext userContext) {
        return new WebMvcConfigurer() {
            @Override
            public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new CurrentUserArgumentResolver(userContext));
            }
        };
    }

    @Bean
    public JwtVerifier jwtVerifier(SecurityProperties props) {
       return new JwtVerifier(props);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtVerifier verifier) {
        return new JwtAuthenticationFilter(verifier);
    }

    @Bean
    public UserContext userContext() {
        return new SecurityContextUserContext();
    }
}
