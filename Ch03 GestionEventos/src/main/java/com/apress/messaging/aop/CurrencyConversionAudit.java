package com.apress.messaging.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.apress.messaging.event.CurrencyConversionEvent;
import com.apress.messaging.exception.BadCodeRuntimeException;

//Define un concern
@Aspect
@Component
public class CurrencyConversionAudit {


	private final ApplicationEventPublisher publisher;

	@Autowired // @Autowired is optional here. Spring will know what to do if you omit it.
	public CurrencyConversionAudit(ApplicationEventPublisher publisher){
		this.publisher = publisher;
	}

	//define un pointcut
	//https://www.baeldung.com/spring-aop-pointcut-tutorial
	//Sobre metodos incluidos en la clase clase com.apress.messaging.service.*Service*
	@Pointcut("execution(* com.apress.messaging.service.*Service.*(..))")
	public void exceptionPointcut() {}

	//Este metodo se ejecutara despues de que se lance una excepcion BadCodeRuntimeException
	@AfterThrowing(pointcut="exceptionPointcut()", throwing="ex")
	public void badCodeException(JoinPoint jp, BadCodeRuntimeException ex){

		if(ex.getConversion()!=null){
			publisher.publishEvent(new CurrencyConversionEvent(jp.getTarget(), ex.getMessage(), ex.getConversion()));
		}
	}

}
