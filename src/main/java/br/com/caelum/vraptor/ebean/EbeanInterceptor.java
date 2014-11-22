package br.com.caelum.vraptor.ebean;

import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

public interface EbeanInterceptor {

	void intercept(SimpleInterceptorStack stack);
	
}
