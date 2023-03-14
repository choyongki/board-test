package study.board.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final WebAccessDeniedHandler webAccessDeniedHandler;
    private final WebAuthenticationEntryPoint webAuthenticationEntryPoint;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        // 정적 자원에 대해서 security를 적용하지 않음
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("filterChain ::: {}",http);
        http
                .httpBasic().disable()
                .csrf().disable()   // 서버에 인증정보를 저장하지 않기에 csrf를 사용하지 않는다.
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/sign/**").permitAll()
                    .antMatchers("/board/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(webAccessDeniedHandler)
//                    .authenticationEntryPoint(webAuthenticationEntryPoint)
                .and()
                .formLogin().disable()
//                    .loginPage("sign/sign-in/view")
//                    .successHandler(customLoginSuccessHandler())
//                    .failureHandler(customLoginFailureHandler())
//                    .permitAll()
//                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")// 스프링 시큐리티에서 제공하는 form 기반의 로그인에 대해 비활성화, 별로로 구성한 필터를 사용
//                .authorizeHttpRequests((authz) -> authz.anyRequest().permitAll()) // 토큰을 활용하는 경우 모든 요청에 대해 '인가'에 대해서 사용
//                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS)) // 세션 기반의 인증을 사용하지 않고 추후 JWT를 이용하여 인증 예정
                .and()
                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * authenticate의 인증 메서드를 제공하는 매니저로 'Provider'의 인터페이스를 의미
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(){
        log.info("authenticationManager ::: ");
        return new ProviderManager(customAuthenticationProvider());
    }

    /**
     * '인증' 제공자로 사용자의 이름과 비밀번가 요구된다.
     * - 과정: CustomAuthenticationFilter → AuthenticationManager(interface) → CustomAuthenticationProvider(implements)
     *      *
     * @return
     */
    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider(){
        log.info("customAuthenticationProvider ::: ");
        return new CustomAuthenticationProvider(encodePassword());
    }

    /**
     * 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화를 수행합니다.
     * @return
     */
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 커스텀을 수행한 '인증' 필터로 접근 URL, 데이터 전달방식(form) 등 인증 과정 및 인증 후 처리에 대한 설정을 구성하는 메서드입니다.
     * @return
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter(){
        log.info("customAuthenticationFilter ::: ");
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/sign/sign-in");     // 접근 URL
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());    // '인증' 성공 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());    // '인증' 실패 시 해당 핸들러로 처리를 전가한다.
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    /**
     *  Spring Security 기반의 사용자의 정보가 맞을 경우 수행이 되며 결과값을 리턴해주는 Handler
     * @return
     */
    @Bean
    public CustomAuthSuccessHandler customLoginSuccessHandler() {
        return new CustomAuthSuccessHandler();
    }

    /**
     * Spring Security 기반의 사용자의 정보가 맞지 않을 경우 수행이 되며 결과값을 리턴해주는 Handler
     * @return
     */
    @Bean
    public CustomAuthFailureHandler customLoginFailureHandler() {
        return new CustomAuthFailureHandler();
    }
//
//    @Bean
//    public WebAccessDeniedHandler webAccessDeniedHandler(){
//        return new WebAccessDeniedHandler();
//    }
//
//    @Bean
//    public WebAuthenticationEntryPoint webAuthenticationEntryPoint(){
//        return new WebAuthenticationEntryPoint();
//    }

}
