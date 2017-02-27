package daw.m08.uf2.jgn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import net.sourceforge.pagesdialect.PagesDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@PropertySource("classpath:thymeleaf.properties")

public class ThymeleafConfig {
	
	@Autowired 
	private Environment env;
	
	public String getProperty(String propertyName) {
		return env.getProperty(propertyName);
	}
	
	@Bean
	public TemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix(getProperty("thymeleaf.prefix"));
		templateResolver.setSuffix(getProperty("thymeleaf.suffix"));	
		templateResolver.setCharacterEncoding(getProperty("thymeleaf.encoding"));
		templateResolver.setTemplateMode(getProperty("thymeleaf.mode"));
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public UrlTemplateResolver urlTemplateResolver() {
		return new UrlTemplateResolver();
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new SpringSecurityDialect());
		templateEngine.addDialect(new PagesDialect());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
	
	@Bean
	public ViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setContentType("text/html; charset=UTF-8");
		viewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return viewResolver;
	}
	
}
