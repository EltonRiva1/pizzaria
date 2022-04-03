package br.com.eltonriva.pizzaria;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Aplicacao implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
		annotationConfigWebApplicationContext.setConfigLocation("br.com.eltonriva.pizzaria.configuracoes");
		Dynamic dynamic = servletContext.addServlet("appServlet",
				new DispatcherServlet(annotationConfigWebApplicationContext));
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/");
		servletContext.addListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
		FilterRegistration.Dynamic filter = servletContext.addFilter("openEntityManagerFilter",
				this.buildOpenEntityManagerFilter());
		filter.addMappingForUrlPatterns(this.getDispatcherTypes(), false, "/*");
	}

	private OpenEntityManagerInViewFilter buildOpenEntityManagerFilter() {
		OpenEntityManagerInViewFilter entityManagerInViewFilter = new OpenEntityManagerInViewFilter();
		entityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
		return entityManagerInViewFilter;
	}

	private EnumSet<DispatcherType> getDispatcherTypes() {
		return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);
	}
}
