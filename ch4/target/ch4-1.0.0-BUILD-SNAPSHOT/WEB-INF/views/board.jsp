<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script> <%-- Jquery를 가져옴.--%>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h2>게시물 읽기</h2>
    <form action="" id="form">
        <input type="text" name="bno" value="${boardDto.bno}" readonly="readonly">
        <input type="text" name="title" value="${boardDto.title}" readonly="readonly">
        <textarea name="content" id="" cols="30" rows="10"></textarea>
        <button type="button" id="writeBtn" class="btn"> 등록 </button>
        <button type="button" id="modifyBtn" class="btn"> 수정 </button>
        <button type="button" id="removeBtn" class="btn"> 삭제 </button>
        <button type="button" id="listBtn" class="btn"> 목록 </button>
    </form>
</div>
</div>
<script>
    $(document).ready(function(){ // 브라우저가 HTML 문서를 다 읽으면 실행됨.
        $('#listBtn').on("click", function() { // id가 "listBtn"인 요소를 클릭하면 실행됨.
           location.href="<c:url value= '/board/list'/>?page=${page}&pageSize=${pageSize}"; // GET요청을 통해 해당 URL페이지로 이동함.
        });

        $('#removeBtn').on("click", function() {
            if(!confirm("정말 삭제하시겠습니까?")) return;
            let form = $('#form');
            form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
            form.attr("method", "post");
            form.submit();
        });
    });
</script>
</body>
</html>