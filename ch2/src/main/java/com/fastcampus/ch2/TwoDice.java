package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
	@RequestMapping("/rollDice")
	public void main(HttpServletResponse res) throws IOException {
		
		int idx1 = (int)(Math.random()*6) + 1;
		int idx2 = (int)(Math.random()*6) + 1;
		
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		PrintWriter result = res.getWriter();
	
		result.println("<html>");
		result.println("<head>");
		result.println("</head>");
		result.println("<body>");
		result.println("<img src='resources/img/dice"+idx1+".jpg'>");
		result.println("<img src='resources/img/dice"+idx2+".jpg'>");	
		result.println("</body>");
		result.println("</html>");
		
		
	
	}
}
