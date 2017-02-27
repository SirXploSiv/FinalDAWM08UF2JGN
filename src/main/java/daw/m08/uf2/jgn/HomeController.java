package daw.m08.uf2.jgn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
@PropertySource("classpath:application.properties")
public class HomeController {
	
	private static final int MILLIS_IN_SECOND = 1000;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MINUTES_IN_HOUR = 60;
	private static final int HOURS_IN_DAY = 24;
	private static final int DAYS_IN_YEAR = 365; //I know this value is more like 365.24...
	private static final long MILLISECONDS_IN_YEAR = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY * DAYS_IN_YEAR;
	
	@Autowired
	private Environment env;
	
	public String getProperty(String propertyName) {
		return env.getProperty(propertyName);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@ModelAttribute("idiomas")
	public List<String> getIdiomas()
	{
		List<String> idiomas = new ArrayList<String>();
		idiomas.add(getProperty("idioma.ca_ES"));
		idiomas.add(getProperty("idioma.es_ES"));
		idiomas.add(getProperty("idioma.en_UK"));
		idiomas.add(getProperty("idioma.fr_FR"));
		return idiomas;
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(headers = "Accept=application/json;charset=utf-8",value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.info("@| REQUEST || [ INDEX.html ] [ '/' ]");
		model.addAttribute("beanPersona", new BeanPersona());
		return "index";
	}

	@PostMapping(headers = "Accept=application/json;charset=utf-8",value = "/readFormPersona") 
	public String resultado( @ModelAttribute("beanPersona") @Valid BeanPersona beanPersona, final BindingResult bResult, final RedirectAttributes attr , Model model) {
	    if (bResult.hasErrors()){
			attr.addFlashAttribute("err",beanPersona);
	    	attr.addFlashAttribute(beanPersona);
	    	return "index";
	    } 	    
	    beanPersona.setEdad(calcularEdad(beanPersona.getFechaNacimiento()));
		model.addAttribute(beanPersona);
		return "resultat";
	}

	
	public Integer calcularEdad(Date fechaNacimiento) {
		long difference = new Date().getTime() - fechaNacimiento.getTime();		
		Integer edad = (int) (difference / MILLISECONDS_IN_YEAR);
		return edad;
	}
}
