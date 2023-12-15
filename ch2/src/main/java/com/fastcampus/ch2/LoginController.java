package com.fastcampus.ch2;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) { // 매개변수에서 HttpSession 클래스로 세션 객체를 바로 얻어옴.
		session.invalidate(); // 세션을 즉시 종료시킴.
		return "redirect:/"; 
	}
	
	
	
	@PostMapping("/login")
	public String login(@CookieValue("myID") String ckValue, String id, String pwd, String toURL, boolean rememberId, HttpServletRequest req, HttpServletResponse res) throws Exception {

		System.out.println(ckValue); // ksz1860
		
		if(!loginCheck(id, pwd)) { // 아이디 혹은 패스워드가 틀린 경우 (로그인 실패 시)
			String errMsg = "id or pwd is not correct";
			
			return "redirect:/login/login?msg=" + errMsg;
		}
		
		// 아이디와 패스워드가 일치하는 경우 (로그인 성공 시)

		HttpSession session = req.getSession(); // 세션 객체를 얻어옴.
		session.setAttribute("myId", id); // 세션 객체에 속성을 저장함.
		
		
		////////////////////////////
		
		if(rememberId) { 
			Cookie ck = new Cookie("myID", id); // "myID"를 식별명(name)으로, 입력받은 id를 식별값(value)으로 하여 쿠키를 생성함.
			res.addCookie(ck); // 생성한 쿠키를 응답에 추가함.
		} else {
			Cookie ck = new Cookie("myID", id);
			ck.setMaxAge(0); // 쿠키를 삭제함.
			res.addCookie(ck);
		}
		
		String toUrl = toURL==null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:" + toUrl;
	}

	
	private boolean loginCheck(String id, String pwd) {
		return "ksz1860".equals(id) && "40204".equals(pwd);
	}
}
