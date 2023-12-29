<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>{name:"abc", age:10}</h2>
<button id="sendBtn" type="button">SEND</button>
<h2>Data From Server :</h2>
<div id="data"></div>

<script>
    $(document).ready(function(){
        let person = {name:"abc", age:10};
        let person2 = {};

        $("#sendBtn").click(function(){
            $.ajax({
                type:'POST',
                url: '/ch4/send',
                headers : { "content-type": "application/json"},
                dataType : 'text',
                data : JSON.stringify(person),  // 서버로 요청할 데이터

                success : function(result){   // 서버가 응답한 데이터가 매개변수에 담김.
                    alert(result); // '{"name":"ABC","age":20}'
                    person2 = JSON.parse(result);
                    $("#data").html("name="+person2.name+", age="+person2.age); // name=ABC, age=20
                },

                error   : function(){ alert("error happened") }
            });

            alert("the request is sent")
        });
    });
</script>
</body>
</html>