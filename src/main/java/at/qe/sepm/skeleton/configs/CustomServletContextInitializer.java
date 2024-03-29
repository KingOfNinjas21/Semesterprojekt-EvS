package at.qe.sepm.skeleton.configs;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration for servlet context.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Softwaredevelopment and Project Management" offered by the University
 * of Innsbruck.
 */
@Configuration
public class CustomServletContextInitializer implements ServletContextInitializer
{

	@Override
	public void onStartup(ServletContext sc) throws ServletException
	{
		sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		sc.setInitParameter("primefaces.UPLOADER", "commons");
	}

	@Bean
	public FilterRegistrationBean FileUploadFilter()
	{
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
		registration.setName("PrimeFaces FileUpload Filter");
		return registration;
	}

}
