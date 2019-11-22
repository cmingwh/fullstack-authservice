package com.ibm.fullstack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ibm.fullstack.filter.CustomAuthenticationFilter;
import com.ibm.fullstack.filter.JwtAuthenticationFilter;
import com.ibm.fullstack.service.UserService;

@EnableWebSecurity
//@EnableRedisHttpSession
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("authenticationSuccessHandler")
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    @Qualifier("authenticationFailHandler")
    private AuthenticationFailHandler failHandler;

    @Autowired
    @Qualifier("authenticationEntryPointImpl")
    private AuthenticationEntryPoint entryPoint;

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
	        .cors().and()
	        .addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .authorizeRequests()
	        .antMatchers("/", "/api/login**", "/api/signup**", "/training/**", "/api/hello**").permitAll()
	        .antMatchers(HttpMethod.GET,"/resource/**").permitAll()
	        .antMatchers(HttpMethod.POST,"/resource/**").hasRole("admin")
	        .antMatchers(HttpMethod.DELETE,"/resource/**").hasRole("admin")
	        .anyRequest().authenticated()
            .and().formLogin()
            .and().csrf().disable()
        	.httpBasic();
//            .and().exceptionHandling().authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(successHandler);
        filter.setAuthenticationFailureHandler(failHandler);
        filter.setFilterProcessesUrl("/api/login");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
    
    @Override
    @Bean // share AuthenticationManager for web and oauth
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
