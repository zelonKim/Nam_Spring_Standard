package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz); //객체가 User 또는 그 자손일 경우 true 반환
	}

	@Override
	public void validate(Object target, Errors errors) {
		 User user = (User)target;
		  String id = user.getId();

		  if(id==null || "".equals(id.trim())) { 
		    errors.rejectValue("id", "required"); // id 필드에 required 에러 코드를 지정함.
		  }
		  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required"); 와 같음.


		  if(id.length() < 5 || id.length() > 12) {
		    errors.rejectValue("id", "invalidLength"); // id 필드에 invalidLength 에러 코드를 지정함.
		  }
	}

}
