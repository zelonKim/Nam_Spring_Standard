package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("register")
public class RegisterController {
	// @RequestMapping("/register/add") // GET과 POST 요청방식 모두 허용
	
	@RequestMapping("/add") // GET 요청방식만 허용
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}//
	
	
	
	@PostMapping("/save") // POST 요청방식만 허용
	public String save(User user, Model mod) throws Exception {
		if(!isValid(user)) {
			String errMsg = "The user is not valid";
			mod.addAttribute("msg", errMsg);
			
			  //return "redirect:/register/add"; 
			 return "forward:/register/add";
			
			//return "redirect:/register/add?msg=" + errMsg;  // URL 재작성 (rewrite)
		}
		
		return "registerInfo";  // WEB-INF/views/registerInfo.jsp
	}

	
	private boolean isValid(User user) {
		return false;
	}
	
}







