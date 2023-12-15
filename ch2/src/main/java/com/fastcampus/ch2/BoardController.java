package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/list")
	public String list(HttpServletRequest req) {
		if(!loginCheck(req)) // �α����� ������ ���
			return "redirect:/login/login?toURL=" + req.getRequestURL();// http://localhost:8080/ch2/login/login ?toURL= http://localhost:8080/ch2/board/list

		return "boardList"; // �α��� ���� ���
	}

	
	private boolean loginCheck(HttpServletRequest req) {
		HttpSession session = req.getSession();
		return session.getAttribute("myId") != null; // ���� ��ü�� ����� "myId" �Ӽ����� �Ӽ����� ������.
	}

}