package com.fixit.config;
import com.fixit.areas.users.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UsersService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UsersService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(this.bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/appointments", "/examinations/**", "/results/**").authenticated()
                .antMatchers(HttpMethod.POST, "/examinations/**", "/results/**").hasAnyAuthority("ADMIN", "DOCTOR")
                .antMatchers("/wards/create", "/logs/**", "/manage/**","/lock/**","/unlock/**").hasAnyAuthority("ADMIN")
                .antMatchers("/users/createadmin", "/users/createpatient", "/users/createdoctor").hasAnyAuthority("ADMIN")
                .antMatchers("/users/**","/wards/**").authenticated()
                .antMatchers("/login", "/register").anonymous()
                .antMatchers("/", "/css/**", "/img/**", "/contact", "/about", "/product/**").permitAll()
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                .csrf()
                .csrfTokenRepository(getCsrfTokenRepository())
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me-new")
                .key("b3b8ac11-ed4f-4f2e-89cf-79aa4369d0fc")
                .userDetailsService(this.userService)
                .rememberMeCookieName("SPRING_SECURITY_REMEMBER_ME_COOKIE")
                .tokenValiditySeconds(2400);
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}