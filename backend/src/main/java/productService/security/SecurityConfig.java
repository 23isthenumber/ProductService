package productService.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
               // .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .hasRole("USER")
                .antMatchers("/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
               // .loginPage("login").permitAll()
                .defaultSuccessUrl("http://localhost:3000/", true )
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .rememberMe()/* for 2 weeks. Needs to  be turn on in frontend, produces a token
                like sesion */
                .rememberMeParameter("remember-me")
               .and()
               .logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");

    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dupaUser= User.builder()
                .username("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(dupaUser);
    }
}
