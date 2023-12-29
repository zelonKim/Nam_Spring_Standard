package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class CommentController {
    @Autowired
    CommentService service;

/*
    // 주소표시줄에 http://localhost/ch4/comments?bno=1 입력
    @GetMapping("/comments")
    public List<CommentDto> list(Integer bno) {
        List<CommentDto> list = null;

        try {
            list = service.getList(bno);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 서비스의 getList()메서드가 commentDao.selectAll(bno);를 반환할 경우
          */
        /*
          // return list;
          {
                "bno": 1,
                "cno": 9,
                "pcno": null,
                "comment": "hello",
                "commenter": "asdf",
                "reg_date": 1703825527000,
                "up_date": 1703825527000
            }
            가 브라우저 화면에 출력됨. & 상태코드 200 OK
            */
        /*


        // 서비스의 getList()메서드가 new Exception("test");를 예외를 발생시킬 경우
         return list;
        // 브라우저 화면에 아무것도 출력되지 않음. & 상태코드 200 OK
    }
}
*/


///////////////////////////////////////////



    // GET http://localhost/ch4/comments?bno=1
    @GetMapping("/comments")
    public ResponseEntity<List<CommentDto>> list(Integer bno) { // 쿼리스트링을 통해 매개변수값을 전달하는 경우 -> 매개변수 타입 앞에 아무것도 붙이지 않음.
        List<CommentDto> list = null;

        try {
            list = service.getList(bno);  // service.getList(1);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
            // return new ResponseEntity(응답 데이터, 응답 상태코드): 응답으로 해당 데이터와 상태코드를 보냄.
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }

        // 서비스의 getList()메서드가 commentDao.selectAll(bno);를 반환할 경우
        /*
        {
            "bno": 1,
                "cno": 9,
                "pcno": null,
                "comment": "hello",
                "commenter": "asdf",
                "reg_date": 1703825527000,
                "up_date": 1703825527000
        }
        가 브라우저 화면에 출력됨. & 상태코드 200 OK
        */


        // 서비스의 getList()메서드가 new Exception("test"); 예외를 발생시킬 경우
        // 브라우저 화면에 아무것도 출력되지 않음. & 상태코드 400 Bad Request

    }







    // DELETE http://localhost/ch4/comments/10?bno=2
    @DeleteMapping("/comments/{cno}") // URI 파라미터는 중괄호{}로 감싸줌.
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) { // URI로 매개변수값을 전달하는 경우 -> 매개변수 타입 앞에 @PathVariable를 붙여줌.
        String commenter = "asdf";

        try {
            int rowCnt = service.remove(cno, bno, commenter); // service.remove(10, 2, asdf);

            if(rowCnt != 1) throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }





    @PostMapping("/comments")  // POST  http://localhost/ch4/comments?bno=2
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session) {
        String commenter = "asdf";

        dto.setCommenter(commenter);
        dto.setBno(bno);
        /*
            // 요청 헤더
            Content-Type: application/json

            // 요청 바디(본문)
            {
                "pcno": 0,
                "comment": "nice to meet you"
            }
        */
        System.out.println(dto); // CommentDto{bno=2, cno=null, pcno=0, comment='nice to meet you', commenter='asdf', reg_date=null, up_date=null}

        try {
            if(service.write(dto)!=1) throw new Exception("Write failed.");

            return new ResponseEntity<>("WRT_OK", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
        }
    }




    @PatchMapping("/comments/{cno}")  // PATCH  http://localhost/ch4/comments/12
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto) {
        dto.setCno(cno);
           /*
                // 요청 헤더
                Content-Type: application/json

                // 요청 바디(본문)
                {
                    "pcno": 0,
                    "comment": "How are you",
                    "commenter": "asdf"
                }
            */

        System.out.println(dto); // CommentDto{bno=null, cno=12, pcno=0, comment='How are you', commenter='asdf', reg_date=null, up_date=null}

        try{
            if(service.modify(dto)!=1) throw new Exception("Write failed.");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);

        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }

    }



}