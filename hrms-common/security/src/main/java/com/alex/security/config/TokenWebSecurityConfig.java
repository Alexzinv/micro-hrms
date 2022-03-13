package com.alex.security.config;

import com.alex.security.filter.TokenAuthenticationFilter;
import com.alex.security.filter.TokenLoginFilter;
import com.alex.security.security.CustomExpiredSessionStrategy;
import com.alex.security.security.TokenLogoutHandler;
import com.alex.security.security.TokenManager;
import com.alex.security.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author _
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenManager tokenManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate<String, Object> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 开启注解方式认证 配合全局注解
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置设置
     * @param http http请求
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())
                .and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/admin/acl/info/logout")
                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate)).and()
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(),
                        tokenManager, redisTemplate)).httpBasic();

        // 一个用户只能创建一个Session，后登录挤掉先登录用户
        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/admin/acl/login")
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(new CustomExpiredSessionStrategy());
    }

    /**
     * 密码处理
     * @param auth 认证
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /**
     * 配置哪些请求不拦截
     * @param web 请求
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",
                "/admin/acl/kaptcha/**", "/druid/**"
        );
    }
}