package com.fastcampus.ch2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	// @RequestMapping("/ex")
	// public void main() throws Exception { // 반환값이 없는(void) 경우, 자동으로 "맵핑된 URL".jsp 뷰를 반환함. -> ex.jsp
	
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(NullPointerException.class)
	public String catcher1(Exception ex, Model mod) {
		//mod.addAttribute("Ex", ex);
		return "error";
	}
	
	
	@RequestMapping("/ex1")
	public String main1() throws Exception {
			throw new NullPointerException("예외가 발생했어요1");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception {
			throw new Exception("예외가 발생했어요2");
	}
	
	@RequestMapping("/ex3")
	public String main3() throws Exception {
			throw new ClassCastException("예외가 발생했어요3");
	}
	
}


