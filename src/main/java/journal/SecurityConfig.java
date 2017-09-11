package journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserLookupService userLookupService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(userLookupService);

        http.authorizeRequests()
                .antMatchers("/", "/about", "/contact", "/user/new", "/login?registered").permitAll()
                .antMatchers("/user/journal/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .formLogin().successForwardUrl("/")
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.GET.toString()))
                .invalidateHttpSession(true);
    }
}
