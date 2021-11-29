package com.example.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.entity.Order;
import com.example.demo.entity.Purchase;

@Component
public class DateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if(Purchase.class.isAssignableFrom(clazz)) return true;
		return Order.class.isAssignableFrom(clazz);			
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof Order) {
			Order order = (Order)target;
			if(order.getDate().after(order.getShipDate())) {
				errors.rejectValue("date", "order.date.confirm", "出貨日期早於訂單日期，請從新確認");
			}			
		}else {
			Purchase purchase = (Purchase)target;
			if(purchase.getDate().after(purchase.getArrival())) {
				errors.rejectValue("date", "purchase.date.confirm", "到貨日期早於採購日期，請從新確認");
			}
		}
		
	}
}
