package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz); //��ü�� User �Ǵ� �� �ڼ��� ��� true ��ȯ
	}

	@Override
	public void validate(Object target, Errors errors) {
		 User user = (User)target;
		  String id = user.getId();
		  String pwd = user.getPwd();

		  if(id==null || "".equals(id.trim())) { 
		    errors.rejectValue("id", "required"); // id �ʵ忡 required ���� �ڵ带 ������.
		  }
		  if(pwd==null || "".equals(pwd.trim())) { 
			    errors.rejectValue("pwd", "required"); // pwd �ʵ忡 required ���� �ڵ带 ������.
			  }
		  
		  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required"); �� ����.
		  // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required"); �� ����.


		  if(id.length() < 5 || id.length() > 12) {
		    errors.rejectValue("id", "invalidLength"); // id �ʵ忡 invalidLength ���� �ڵ带 ������.
		  }
	}

}
