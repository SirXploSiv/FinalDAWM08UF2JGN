package daw.m08.uf2.jgn;

//CONFIG SERVLET

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//THIRD CLASS
@Configuration
@ComponentScan
@PropertySources({
	@PropertySource(value = {"classpath:application.properties"})
})
@EnableWebMvc // <annotation-drive /> 
public class SpringDispatcherServletConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;
	public String getProperty(String propertyName) {
		return env.getProperty(propertyName);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*super.addResourceHandlers(registry);*/
		/*-> http://joaoduraes.github.io/2015/02/07/spring-bootstrap-thymeleaf-example.html */
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
}
