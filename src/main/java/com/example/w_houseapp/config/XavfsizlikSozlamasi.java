package com.example.w_houseapp.config;

import com.example.w_houseapp.security.JwtAuthEntryPoint;
import com.example.w_houseapp.security.JwtFilter;
import com.example.w_houseapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class XavfsizlikSozlamasi extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final JwtFilter jwtFilter;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
//                .antMatchers("/api/**")
                .authenticated();
//                .and()
//                .httpBasic();
        //har bir zaprosdan oldin shu classga kirib chiqadi
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//        @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //bazadan emas vaqtinchalik malumotni qo'shib turish
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//                .and()
//                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
//                .and()
//                .withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");
//    }

}
