/***
 * Copyright (c) 2011 Caelum - www.caelum.com.br/opensource
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.caelum.vraptor.ebean;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.hibernate.EbeanInterceptor;
import br.com.caelum.vraptor.hibernate.EbeanTransactionInterceptor;
import br.com.caelum.vraptor.http.MutableResponse;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.Validator;

public class EbeanTransactionInterceptorTest {

	@Mock private SimpleInterceptorStack stack;
	@Mock private ControllerMethod method;
	@Mock private Validator validator;
	@Mock private MutableResponse response;
	private EbeanInterceptor interceptor;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		interceptor = new EbeanTransactionInterceptor(validator, response);
	}

	@Test
	public void shouldStartAndCommitTransaction() throws Exception {
		EbeanTransactionInterceptor interceptor = new EbeanTransactionInterceptor(validator, response);

		interceptor.intercept(stack);

	}

	@Test
	public void shouldRollbackTransactionIfStillActiveWhenExecutionFinishes() throws Exception {
		doThrow(new RuntimeException()).when(stack).next();

		try {
			interceptor.intercept(stack);
		} catch (Exception e) {
			// nothing
		}

	}

	@Test
	public void shouldRollbackIfValidatorHasErrors() {
		when(validator.hasErrors()).thenReturn(true);

		interceptor.intercept(stack);
	}

	@Test
	public void shouldCommitIfValidatorHasNoErrors() {
		when(validator.hasErrors()).thenReturn(false);

		interceptor.intercept(stack);
	}

	@Test
	public void doNothingIfHasNoActiveTransation() {
		interceptor.intercept(stack);
	}

	@Test
	public void shouldConfigureARedirectListener() {
		interceptor.intercept(stack);

		verify(response).addRedirectListener(any(MutableResponse.RedirectListener.class));
	}
}