package at.qe.sepm.skeleton.configs;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.headers().frameOptions().disable(); // needed for H2 console

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login.xhtml");

        http.authorizeRequests()
                //Permit access to the H2 console
                .antMatchers("/h2-console/**").permitAll()
                //Permit access for all to error pages
                .antMatchers("/error/**")
                .permitAll()
                // Only access with admin role
                .antMatchers("/admin/**")
                .hasAnyAuthority("ADMIN")
                //Permit access only for some roles
                .antMatchers("/secured/**")
                .hasAnyAuthority("ADMIN", "MANAGER", "EMPLOYEE")
                //If user doesn't have permission, forward him to login page
                .and()
                .formLogin()
                .loginPage("/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/secured/welcome.xhtml")
                .and().exceptionHandling().accessDeniedPage("/error/denied.xhtml");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Configure roles and passwords as in-memory authentication
        /*auth.inMemoryAuthentication()
         .withUser("administrator")
         .password("pass")
         .roles("ADMIN");
         auth.inMemoryAuthentication()
         .withUser("user")
         .password("pass")
         .roles("EMPLOYEE");*/

        //Configure roles and passwords via datasource
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select user_username, roles from user_user_role where user_username=?");
    }

}
