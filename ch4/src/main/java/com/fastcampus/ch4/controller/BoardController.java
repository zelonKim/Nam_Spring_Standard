package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.PageHandler;
import com.fastcampus.ch4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);

            if (rowCnt != 1) throw new Exception("board remove error");

            m.addAttribute("msg", "DEL_OK");

        } catch(Exception e) {
               e.printStackTrace();
               m.addAttribute("msg", "DEL_ERR");
        }

        return "redirect:/board/list";
    }




    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }


    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;

        try {
            Map map = new HashMap();
            map.put("offset", (page-1) * pageSize);
            map.put("pageSize", pageSize);
            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list);



            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList";
    }




    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;
    }

}
