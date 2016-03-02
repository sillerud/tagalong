package no.westerdals.westbook.config;

import lombok.Setter;
import no.westerdals.westbook.filter.CsrfHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        String superUsername = environment.getProperty("SUPERUSER_NAME");
        String superPassword = environment.getProperty("SUPERUSER_PASS");
        if (superUsername != null && superPassword != null) {
            auth.inMemoryAuthentication().withUser(superUsername).password(superPassword).roles("NEW_USER");
        }
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/rest/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setStatus(200);
                    httpServletResponse.getWriter().print("{\"status\":\"ok\"}");
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setStatus(403);
                    httpServletResponse.getWriter().print("{\"status\":\"failed\"}");
                })
                .permitAll()
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("login.html")
                .logoutRequestMatcher(new AntPathRequestMatcher("/rest/logout"))
                .and()
            .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
        HttpSessionCsrfTokenRepository tokenRepository = new HttpSessionCsrfTokenRepository();
        tokenRepository.setHeaderName("X-XSRF-TOKEN");
        http.csrf().csrfTokenRepository(tokenRepository);
    }
}
