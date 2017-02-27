package daw.m08.uf2.jgn;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//REPLACE THE WEB.XML

public class SpringWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// FIRST TO DO , THIS CLASS
	public SpringWebInitializer() {}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		//SECOND CLASS TO DO
		return new Class [] {SpringContextConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		//THIRD CLASS TO DO
		return new Class [] {SpringDispatcherServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] {"/"};
	}

}
