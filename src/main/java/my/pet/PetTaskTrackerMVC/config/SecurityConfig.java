package my.pet.PetTaskTrackerMVC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Защита от межсайтовой подделки запросов
                // Отключено для REST API или если фронтенд отдельно
                // Для традиционных Web приложений с формами - нужно включать
                .csrf(csrf -> csrf.disable())

                // Настройка правил авторизации запросов
                // Проверка происходит сверху вниз - первое совпадение применяется
                .authorizeHttpRequests(authz -> authz
                        // Эти URL доступны всем (даже без логина)
                        .requestMatchers("/public/**", "/auth/login", "/auth/register").permitAll()
                        // Все остальные запросы требуют authentication (любой залогиненный пользователь)
                        .anyRequest().authenticated()
                )

                // Настройка формы логина
                .formLogin(form -> form
                        // Кастомная страница логина (ваша HTML страница)
                        .loginPage("/auth/login")
                        // URL куда отправлять данные формы логина (обрабатывается Spring Security)
                        .loginProcessingUrl("/auth/login")
                        // Куда перенаправлять после успешного логина
                        .defaultSuccessUrl("/tasks")
                        // Куда перенаправлять при ошибке логина
                        .failureUrl("/auth/login?error=true")
                        // Разрешить доступ к странице логина всем
                        .permitAll()
                )

                // Настройка выхода из системы (logout)
                .logout(logout -> logout
                        // URL для выхода из системы
                        .logoutUrl("/auth/logout")
                        // Куда перенаправить после успешного выхода
                        .logoutSuccessUrl("/auth/login?logout=true")
                        // Уничтожить HTTP сессию при выходе
                        .invalidateHttpSession(true)
                        // Удалить куки сессии при выходе
                        .deleteCookies("JSESSIONID")
                        // Разрешить выход всем
                        .permitAll()
                )

                // Управление сессиями
                .sessionManagement(session -> session
                        // Политика создания сессий:
                        // IF_REQUIRED - создавать сессию при необходимости
                        // ALWAYS - всегда создавать сессию
                        // NEVER - не создавать, но использовать если есть
                        // STATELESS - без сессий (для JWT)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // Максимум 1 активная сессия на пользователя
                        .maximumSessions(1)
                        // false - новая сессия выкидывает старую
                        // true - блокировать новую сессию если уже есть активная
                        .maxSessionsPreventsLogin(false)
                        // Куда перенаправлять при просроченной сессии
                        .expiredUrl("/auth/login?expired=true")
                )

                // Функциональность "Запомнить меня"
                .rememberMe(remember -> remember
                        // Секретный ключ для шифрования токена
                        .key("uniqueAndSecret")
                        // Время жизни токена "запомнить меня" в секундах (24 часа)
                        .tokenValiditySeconds(86400)
                );

        return http.build();
    }

    // Бин для кодирования паролей
    // BCrypt безопасно хеширует и солит пароли для хранения в базе
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}