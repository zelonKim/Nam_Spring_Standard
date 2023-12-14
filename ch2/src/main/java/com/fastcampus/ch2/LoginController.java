package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, String rememberId) throws Exception {
		
		if(!loginCheck(id, pwd)) {
			String errMsg = "id or pwd is not correct";
			
			return "redirect:/login/login?msg=" + errMsg;
		}
		
		return "redirect:/";
	}

	
	private boolean loginCheck(String id, String pwd) {
		return "ksz1860".equals(id) && "40204".equals(pwd);
	}
}