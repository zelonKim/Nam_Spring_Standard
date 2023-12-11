package com.fastcampus.ch2;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RequestInfo {
    @RequestMapping("/requestInfo")
    public void main(HttpServletRequest request) { // 주소창에 http://127.0.0.1:8080/ch2/requestInfo?year=2023&month=12&day=25 입력 시,
        System.out.println("request.getCharacterEncoding()= "+request.getCharacterEncoding()); // 요청 내용의 인코딩 // null
        System.out.println("request.getContentLength()= "+request.getContentLength());  // 요청 내용의 길이. (알수 없을 때는 -1) // -1
        System.out.println("request.getContentType()= "+request.getContentType()); // 요청 내용의 타입. // null

        System.out.println("request.getMethod()= "+request.getMethod());      // 요청 방법 // GET
        System.out.println("request.getProtocol()= "+request.getProtocol());  // 프로토콜의 종류와 버젼  // HTTP/1.1
        System.out.println("request.getScheme()= "+request.getScheme());      // 프로토콜 // http

        System.out.println("request.getServerName()= "+request.getServerName()); // 서버 이름 또는 ip주소  // 127.0.0.1
        System.out.println("request.getServerPort()= "+request.getServerPort()); // 서버 포트 // 8080
        System.out.println("request.getRequestURL()= "+request.getRequestURL()); // 요청 URL // http://127.0.0.1:8080/ch2/requestInfo
        System.out.println("request.getRequestURI()= "+request.getRequestURI()); // 요청 URI // /ch2/requestInfo

        System.out.println("request.getContextPath()= "+request.getContextPath()); // 컨텍스트 패스 // /ch2
        System.out.println("request.getServletPath()= "+request.getServletPath()); // 서블릿 패스 // /requestInfo
        System.out.println("request.getQueryString()= "+request.getQueryString()); // 쿼리 스트링 // year=2023&month=12&day=25
        
        System.out.println("request.getQueryString()= "+request.getParameter("year")); // 쿼리스트링의 "year" 파라미터의 값  // 2023
        System.out.println("request.getQueryString()= "+request.getParameter("month")); // 쿼리스트링의 "month" 파라미터의 값  // 12
        System.out.println("request.getQueryString()= "+request.getParameter("day")); // 쿼리스트링의 "day" 파라미터의 값  // 25
        

        System.out.println("request.getLocalName()= "+request.getLocalName()); // 로컬 이름 // 127.0.0.1
        System.out.println("request.getLocalPort()= "+request.getLocalPort()); // 로컬 포트 // 8080

        System.out.println("request.getRemoteAddr()= "+request.getRemoteAddr()); // 원격 ip주소  // 127.0.0.1
        System.out.println("request.getRemoteHost()= "+request.getRemoteHost()); // 원격 호스트 또는 ip주소  // 127.0.0.1
        System.out.println("request.getRemotePort()= "+request.getRemotePort()); // 원격 포트  // 61724
    }
}


