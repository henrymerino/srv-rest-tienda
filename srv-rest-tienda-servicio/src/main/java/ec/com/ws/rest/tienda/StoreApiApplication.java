package ec.com.ws.rest.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ec.com.ws.rest.tienda.auth.AuthorizationFilter;


//(exclude={DataSourceAutoConfiguration.class}) 
@SpringBootApplication
public class StoreApiApplication extends SpringBootServletInitializer {

	private static final Class<StoreApiApplication> applicationClass = StoreApiApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(StoreApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
/*
	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity security) throws Exception {
			security.csrf().disable().authorizeRequests().anyRequest().permitAll();
		}
	}
*/	
	 @Configuration
	    @EnableWebSecurity
	    @EnableGlobalMethodSecurity(prePostEnabled = true)
	    public class SecurityConfig extends WebSecurityConfigurerAdapter {

	        @Override
	        protected void configure(HttpSecurity security) throws Exception {
	        	security.csrf().disable()
	        	.addFilterAfter(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
	        	.authorizeRequests().antMatchers(HttpMethod.POST, "/api/persona/login").permitAll()
				.anyRequest().authenticated();
	        }
	    }

}
