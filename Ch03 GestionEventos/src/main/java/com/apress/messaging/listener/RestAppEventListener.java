package com.apress.messaging.listener;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.apress.messaging.annotation.Log;

@Component
public class RestAppEventListener {

	//@EventListener(condition = "#springApp.args.length > 1")
	//@EventListener({CurrencyEvent.class,CurrencyConversionEvent.class})
	//@Order(Ordered.HIGHEST_PRECEDENCE)
	//@Async

	//Implementa un listener de eventos
	@EventListener
	//Anota con esta anotación custom para que se ejecute nuestro concern de logging
	@Log(printParamsValues=true)
	public void restAppHandler(SpringApplicationEvent springApp){
	}
}
