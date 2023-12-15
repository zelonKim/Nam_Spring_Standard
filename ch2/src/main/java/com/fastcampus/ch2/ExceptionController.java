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
	// public void main() throws Exception { // ��ȯ���� ����(void) ���, �ڵ����� "���ε� URL".jsp �並 ��ȯ��. -> ex.jsp
	
	
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(NullPointerException.class)
	public String catcher1(Exception ex, Model mod) {
		mod.addAttribute("Ex", ex);
		return "error";
	}
	
	
	@RequestMapping("/ex1")
	public String main1() throws Exception {
			throw new NullPointerException("���ܰ� �߻��߾��1");
	}

	@RequestMapping("/ex2")
	public String main2() throws Exception {
			throw new Exception("���ܰ� �߻��߾��2");
	}
	
	@RequestMapping("/ex3")
	public String main3() throws Exception {
			throw new ClassCastException("���ܰ� �߻��߾��3");
	}
}