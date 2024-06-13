package com.jsp.shopping_cart.helper;



import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer  extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		Class [] config = {Classconfig.class};
		return config;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		String [] url = {"/"};
		return url;
	}

}

