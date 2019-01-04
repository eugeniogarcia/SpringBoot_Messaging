package com.apress.messaging.event;

import org.springframework.context.ApplicationEvent;

import com.apress.messaging.domain.Rate;

//define un aplication event
public class CurrencyEvent extends ApplicationEvent {

	private static final long serialVersionUID = 889202626288526113L;
	private final Rate rate;

	public CurrencyEvent(Object source,Rate rate) {
		super(source);
		this.rate = rate;
	}

	public Rate getRate(){
		return this.rate;
	}
}
