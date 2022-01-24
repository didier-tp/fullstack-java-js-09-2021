package tp.appliJee;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	public SecurityConfig() {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//config pour Spring-mvc avec WS-REST et tokens (ex : jwt) :
			http.authorizeRequests()
			//.antMatchers("/rest/**").permitAll()
			.anyRequest().permitAll()
			//.anyRequest().authenticated()
			//.anyRequest().hasRole("ADMIN")
			.and().cors()
			.and().csrf().disable();
	}
	
	

}
