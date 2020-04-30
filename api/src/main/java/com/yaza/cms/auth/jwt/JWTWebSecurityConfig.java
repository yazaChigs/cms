package com.yaza.cms.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

    @Value("${jwt.get.token.uri}")
    private String authenticationPath;

    private static final String[] AUTH_WHITELIST = {
        // -- swagger ui
//        "/swagger-resources/**",
//        "/swagger-ui.html",
//        "/v2/api-docs",
//        "/api/token/*",
//        "/webjars/**",
//        "/api/admin/company-profile/profile-pic/**",
//            "/api/admin/physician/e-signature/**",
//        "/api/clinical/patient/image",
//        "/api/clinical/patient/get-image",
//        "/api/clinical/patient/pdfreport",
//        "/api/clinical/letter/pre-op-ass",
//        "/api/clinical/quote/print/**",
//        "api/clinical/patient/get-scoliosis-score/**",
//        "/api/clinical/patient/update-data",
//        "/api/clinical/bill/print/**",
//        "/api/clinical/bill/print-statement/**",
//        "/api/user/password/reset/**",
//        "/api/clinical/letter/to-other-pract",
//        "/api/clinical/letter/discharge-summary-general",
//        "/api/clinical/letter/discharge-summary",
//        "/api/clinical/letter/letter-ref-doc",
//        "/api/clinical/letter/word",
//        "/api/admin/medicine/token",
//        "/api/clinical/orders/lab/file",
//        "/api/clinical/obs/hae/file",
//        "/api/admin/icd10/diagnosis/load"
            "/api/task/pdf"
    };

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/rest/**").authenticated().and().httpBasic();

        httpSecurity.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().
                antMatchers("/api/**")
                //.anyRequest()
                .authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.headers().frameOptions().sameOrigin() // H2 Console Needs this setting
                .cacheControl(); // disable caching
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
                .antMatchers("/authentication")
                .and().ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")//Angular Token Authentication
                .and().ignoring()
                .antMatchers(AUTH_WHITELIST // Other Stuff You want to Ignore
                ).and().ignoring()
                .antMatchers("/h2-console/**/**");// Should not be done in Production!
    }
}
