package com.aungthumoe.configs;

import com.aungthumoe.security.SecureAuthenticationProvider;
import com.aungthumoe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Aung Thu Moe
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    private SecureAuthenticationProvider provider;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, SecureAuthenticationProvider secureAuthenticationProvider) throws Exception {
        auth.authenticationProvider(secureAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TextEncryptor textEncryptor() {
        return Encryptors.noOpText();
    }

    @Bean
    public SecureAuthenticationProvider secureAuthenticationProvider() {
        this.provider = new SecureAuthenticationProvider(userService);
        return this.provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/admin/**").hasAuthority("admin")
            .antMatchers("/user/**").hasAuthority("user")
            .antMatchers("/users/**").hasAuthority("admin")
            .antMatchers("/index").permitAll()
            .antMatchers("/").permitAll()
            .anyRequest().fullyAuthenticated()
        .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
            .permitAll()
        .and()
            .logout()
            .logoutSuccessUrl("/")
            .deleteCookies("remember-me")
            .permitAll()
        .and()
            .rememberMe();
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .antMatcher("/**")
//            .addFilterAfter(authenticationTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
//            .authorizeRequests()
//            .antMatchers("/api/v1/uploads/").authenticated()
//            .antMatchers("/api/v1/uploads/**").authenticated()
//            .antMatchers("/api/v1/items/**").authenticated()
//            .antMatchers("/api/v1/items").authenticated()
//            .antMatchers("/api/v1/collects").authenticated()
//            .antMatchers("/api/v1/collects/**").authenticated()
//            .antMatchers("/api/v1/images/").authenticated()
//            .antMatchers("/api/v1/images/**").authenticated()
//            .antMatchers("/api/v1/metadata").authenticated()
//            .antMatchers("/api/v1/metadata/**").authenticated()
//            .antMatchers("/api/v1/vectors/").authenticated()
//            .antMatchers("/api/v1/vectors/**").authenticated()
//            .antMatchers("/api/v1/types").authenticated()
//            .antMatchers("/api/v1typedefinitions").authenticated()
//            .antMatchers("/api/v1/userlists").authenticated()
//            .antMatchers("/api/v1/userlists/**").authenticated()
//            .and()
//            .logout()
//            .logoutUrl("/api/v1/logout")
//            .and()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }

}
