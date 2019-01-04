package com.apress.messaging.aop;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.apress.messaging.annotation.ToUpper;

//Define un concern
@Aspect
@Component
public class CurrencyCodeAudit {

	//define un pointcut
	//https://www.baeldung.com/spring-aop-pointcut-tutorial
	//Sobre metodos incluidos en la clase clase com.apress.messaging.service.*Service* que tengan algun argumento con la anotación com.apress.messaging.annotation.ToUpper
	@Pointcut("execution(* com.apress.messaging.service.*Service.*(.., @com.apress.messaging.annotation.ToUpper (*),..))")
	public void methodPointcut() {}

	//Este metodo se ejecutara antes y despues del pointcut definido anteriormente
	@Around("methodPointcut()")
	public Object codeAudit(ProceedingJoinPoint pjp) throws Throwable{
		final Object[] args = pjp.getArgs();
		final Parameter[]  parameters = ((MethodSignature)pjp.getSignature()).getMethod().getParameters();

		return pjp.proceed(IntStream.range(0, args.length)
				.mapToObj(index -> (parameters[index].isAnnotationPresent(ToUpper.class)) ? (new String(args[index].toString().toUpperCase())) : (args[index]) )
				.toArray());
	}

}
