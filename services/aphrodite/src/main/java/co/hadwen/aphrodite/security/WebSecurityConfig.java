package co.hadwen.aphrodite.security;

import co.hadwen.aphrodite.auth.AuthenticationProvider;
import co.hadwen.aphrodite.auth.BearerAuthenticationTokenFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

/**
 * Web security configuration
 */
@Import(SecurityConfig.class)
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setAllowedMethods(
                Arrays.asList(
                        RequestMethod.GET.name(),
                        RequestMethod.POST.name(),
                        RequestMethod.DELETE.name(),
                        RequestMethod.OPTIONS.name(),
                        RequestMethod.PATCH.name()));
        corsConfig.addAllowedHeader("*");
        corsConfig.addExposedHeader(ExposedHeaders.LOCATION.getFormattedName());
        corsConfig.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        http.cors().configurationSource(source)
                .and()
                //todo: figure out what this is.
                .csrf()
                .disable();
        http.requiresChannel()
                .and()
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/platforms*").permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling();
        http.addFilterBefore(tokenFilter(), AnonymousAuthenticationFilter.class);
    }

    @Bean
    BearerAuthenticationTokenFilter tokenFilter() {
        BearerAuthenticationTokenFilter filter = new BearerAuthenticationTokenFilter(new NegatedRequestMatcher(
                request -> new AntPathRequestMatcher("/v1/platforms*").matches(request)
        ));
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationSuccessHandler(successHandler());
        return filter;
    }

    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler() {
        final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setRedirectStrategy(new NoRedirectStrategy());
        return successHandler;
    }

    @AllArgsConstructor
    @Getter
    enum ExposedHeaders {
        LOCATION("location");
        private final String formattedName;
    }
}
