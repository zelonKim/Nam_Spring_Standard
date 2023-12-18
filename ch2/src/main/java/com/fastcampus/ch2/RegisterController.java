package com.fastcampus.ch2;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	
	
	@InitBinder  // WebDataBinder의 기본 타입변환 전략을 변경함. (한 컨트롤러 내에서만 적용)
	public void toUser(WebDataBinder binder) {		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		// 바인더객체.registerCustomEditor(변환할 타입, 변환할 형식)
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false)); // new CustomDateEditor(날짜 형식 객체, 빈값 허용여부)
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#")); // new StringArrayPropertyEditor("구분자")
	
		// 자동 검증
		binder.setValidator(new UserValidator()); 
		
	}
	
	
	
	@PostMapping("/save") 
	public String save(@Valid User user, BindingResult result ,Model mod) throws Exception {
		System.out.println(result); // BindingResult 객체 매개변수를 선언 -> 타입 변환에 의한 에러 메시지를 매개변수에 담아줌. & 에러 화면이 출력되는 것을 막아줌. 
		
//		// 수동 검증
//		UserValidator uv = new UserValidator();
//		uv.validate(user, result); // BindingResult는 Errors인터페이스의 자손임.
//		  
		
		if(result.hasErrors()) { // 에러가 있으면 true를 반환
		    return "registerForm"; // 반환한 뷰에서 <form: errors> 태그를 이용하여 실제 에러를 보여줌. 
	}
		
		
		////////////////////
		
//		if(!isValid(user)) {
//			String errMsg = "The user is not valid";
//			mod.addAttribute("msg", errMsg);
//			
//			  //return "redirect:/register/add"; 
//			 return "forward:/register/add";
//			
//			//return "redirect:/register/add?msg=" + errMsg;  // URL 재작성 (rewrite)
//		}
		
		return "registerInfo";  // WEB-INF/views/registerInfo.jsp
	}

	
	private boolean isValid(User user) {
		return true;
	}
	
}







