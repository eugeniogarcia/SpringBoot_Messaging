package com.apress.messaging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Define una anotacion que se aplicara sobre metodos y que tiene dos parametros
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Log {
	boolean printParamsValues() default false;
	String callMethodWithNoParamsToString() default "toString";
}
