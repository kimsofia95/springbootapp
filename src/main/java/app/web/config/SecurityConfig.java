package app.web.config;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по
    private final UserService userService;
    private boolean defaultRowsIsCreated = false;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler successUserHandler, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //Web resources
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/scripts/**");
        web.ignoring().antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!defaultRowsIsCreated) {
            userService.createDefaultRows();
            defaultRowsIsCreated = true;
        }
        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll()
                .successHandler(successUserHandler);

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')").anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
