<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>commentTest</h2>
comment: <input type="text" name="comment"><br>

<button id="sendBtn" type="button">댓글 작성</button>
<button id="modBtn" type="button">댓글 변경</button>

<div id="commentList"></div>

<div id="replyForm" style="display:none">
    <input type="text" name="replyComment">
    <button id="wrtRepBtn" type="button"> 답글 등록 </button>
</div>

<script>
    let bno = 2;

    let showList = function(bno) {
            $.ajax({
                type:'GET',
                url: '/ch4/comments?bno='+bno,
                success : function(result) {   // 서버가 응답한 데이터가 매개변수에 담김.
                  $("#commentList").html(toHtml(result));
                },
                error : function(){ alert("error happened") }
            });
        }



    let toHtml = function(comments) {
            let tmp = "<ul>";

            comments.forEach(function(comment) {
                tmp += '<li data-cno=' + comment.cno
                tmp += ' data-pcno=' + comment.pcno
                tmp += ' data-bno=' + comment.bno + '>'
                if(comment.cno!=comment.pcno) tmp += ' ㄴ' // 답글일 경우
                tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
                tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
                tmp += ' up_date=' + comment.up_date
                tmp += '<button class="delBtn">삭제</button>'
                tmp += '<button class="modBtn">수정</button>'
                tmp += '<button class="replyBtn">답글</button>'
                tmp += '</li>'
        })
        return tmp + "</ul>"
    }

/*
    <div id="commentList">
        <ul>
            <li data-cno="11" data-pcno="null" data-bno="2">
                commenter=
                    <span class="commenter">asdf2</span>
                comment=
                    <span class="comment">hi</span>
                up_date=
                    1703830998000
                    <button class="delBtn">삭제</button>
                    <button class="modBtn">수정</button>
                    <button class="replyBtn">답글</button>
            </li>

            <li data-cno="12" data-pcno="null" data-bno="2">
                commenter=
                    <span class="commenter">asdf</span>
                comment=
                    <span class="comment">How are you</span>
                up_date=
                    1703842142000
                    <button class="delBtn">삭제</button>
                    <button class="modBtn">수정</button>
                    <button class="replyBtn">답글</button>
            </li>

            <li data-cno="14" data-pcno="0" data-bno="2">
                commenter=
                    <span class="commenter">asdf</span>
                comment=
                    <span class="comment">nice to meet you</span>
                up_date=
                    1703839992000
                    <button class="delBtn">삭제</button>
                    <button class="modBtn">수정</button>
                    <button class="replyBtn">답글</button>
            </li>
        </ul>
    </div>
*/



    $(document).ready(function(){
        showList(bno);

        $("#sendBtn").click(function(){
            let comment = $("input[name=comment]").val();

            if(comment.trim()=='') {
                alert("댓글을 입력하세요")
                $("input[name=comment]").focus()
                return;
            }

            $.ajax({
                type:'POST',
                url: '/ch4/comments?bno='+bno,
                headers: {"content-type": "application/json"},
                data: JSON.stringify({bno: bno, comment: comment}),
                success: function(result) {
                    alert(result);
                    showList(bno);
                },
                error: function() { alert("error") }
            })
        });




        $("#wrtRepBtn").click(function(){
            let comment = $("input[name=replyComment]").val();
            let pcno = $("#replyForm").parent().attr("data-pcno");

            if(comment.trim()=='') {
                alert("답글을 입력하세요")
                $("input[name=replyComment]").focus()
                return;
            }

            $.ajax({
                type:'POST',
                url: '/ch4/comments?bno='+bno,
                headers: {"content-type": "application/json"},
                data: JSON.stringify({pcno: pcno, bno: bno, comment: comment}),
                success: function(result) {
                    alert(result);
                    showList(bno);
                },
                error: function() { alert("error") }
            })
            $("#replyForm").css("display", "none")
            $("input[name=replyComment]").val('')
            $("#replyForm").appendTo("body");
        });





        $("#modBtn").click(function(){
            let cno = $(this).attr("data-cno");
            let comment = $("input[name=comment]").val();

            if(comment.trim()=='') {
                alert("댓글을 입력하세요")
                $("input[name=comment]").focus()
                return;
            }

            $.ajax({
                type:'PATCH',
                url: '/ch4/comments/'+cno,
                headers: {"content-type": "application/json"},
                data: JSON.stringify({cno: cno, comment: comment}),
                success: function(result) {
                    alert(result);
                    showList(bno);
                },
                error: function() { alert("error") }
            })
        });




        $("#commentList").on("click", ".delBtn", function() { // <div id="commentList"></div> 내의 '<button class="delBtn">삭제</button>'가 클릭되었을 때 함수를 실행함.
           let cno = $(this).parent().attr("data-cno")
           let bno = $(this).parent().attr("data-bno")

            $.ajax({
               type:'DELETE',
               url: '/ch4/comments/'+cno+'?bno='+bno,
               success: function(result) {
                   alert(result)
                   showList(bno);
               },
               error: function() { alert("error")}
           })
        })




        $("#commentList").on("click", ".modBtn", function() {
            let cno = $(this).parent().attr("data-cno")
            let bno = $(this).parent().attr("data-bno")
            let comment = $("span.comment", $(this).parent()).text(); // '수정' 버튼을 클릭한 해당 요소의 부모 요소내에서 <span class="comment"></span>의 컨텐츠를 가져옴.

            $("input[name=comment]").val(comment); // 수정할 코멘트를 인풋창에 value로 넣어줌.

            $("#modBtn").attr("data-cno", cno);
        })




        $("#commentList").on("click", ".replyBtn", function() {
            $("#replyForm").appendTo($(this).parent());
            $("#replyForm").css("display", "block");
        })


    });





</script>
</body>
</html>