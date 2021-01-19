package markt_study.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import markt_study.login.LoginImpl;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	 private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
	public WebSecurityConfig(CustomAuthenticationSuccessHandler authenticationSuccessHandler) {
	  this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	    
    @Bean
    public UserDetailsService userDetailsService() {
        return new LoginImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
 
       
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   
    	http
        .csrf().disable();
    	
    	http.authorizeRequests() 
    		.antMatchers("/resources/*","/static/**").permitAll()
            .antMatchers("/direction/**").hasAnyAuthority("admin", "direction")
            .antMatchers("/supervision/**").hasAnyAuthority("admin", "supervisor")
            .antMatchers("/management/**").hasAnyAuthority("admin", "management")
            .antMatchers("/all/**").hasAnyAuthority("admin")
            //.anyRequest().authenticated()  
            .and()  
            .formLogin()
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler)
            .permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            ;
    }
}