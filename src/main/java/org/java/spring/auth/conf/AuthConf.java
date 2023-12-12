package org.java.spring.auth.conf;

import org.java.spring.auth.db.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {
	
	// Configura la catena di filtri di sicurezza HTTP.
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.csrf().disable(); // Opzionale: disabilita CSRF
        http.authorizeHttpRequests()
            .requestMatchers("/pizza/create/**").hasAuthority("admin")
            .requestMatchers("/pizza/edit/**").hasAuthority("admin")
            .requestMatchers("/pizza/delete/**").hasAuthority("admin")
            .requestMatchers("/pizzas/{id}/discount**").hasAuthority("admin")
            .requestMatchers("/pizzas/{pizzaId}/discount/edit/{discountId}**").hasAuthority("admin")
            .requestMatchers("/ingredient/create/**").hasAuthority("admin")
            .requestMatchers("/ingredients**").hasAuthority("admin")
            .requestMatchers("/ingredient/new**").hasAuthority("admin")
            .requestMatchers("/ingredient/delete/{id}**").hasAuthority("admin")
            .requestMatchers("/**").permitAll()
            .and().formLogin()
            .and().logout();
        
        return http.build();
    }
	
    // Crea un bean per il servizio di gestione degli utenti.
    @Bean
    UserDetailsService userDetailsService() {
	    return new UserService();
	}
	
    // Crea un bean per l'encoder delle password (utilizza BCrypt).
    @Bean
	public static PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    // Crea un bean per il provider di autenticazione DAO.
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	authProvider.setUserDetailsService(userDetailsService());
    	authProvider.setPasswordEncoder(passwordEncoder());
    	return authProvider;
    }
    
    // Un "bean" è un oggetto gestito dal framework e configurato per svolgere una specifica funzionalità nell'applicazione.
    // Questi bean vengono creati e gestiti dal container di inversione di controllo (IoC) di Spring.
}
