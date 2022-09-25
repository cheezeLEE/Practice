<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>메일 발송</title>
</head>
<body>
    <h1>메일 발송</h1>

    <form id="mailTestF" action="/mailTest" method="post">
    	<p>테스트 이메일</p>
        <input name="address" placeholder="이메일 주소"> <br>
        <input name="title" placeholder="제목"> <br>
        <textarea name="message" placeholder="메일 내용을 입력해주세요." cols="60" rows="20"></textarea>
        <button type="button" id="submitTest">발송</button>
    </form>
    <form id="mailJoinF" action="/mailJoin" method="post">
    	<p>회원가입 이메일</p>
        <input name="address" id="address" placeholder="이메일 주소"> <br>
        <button type="button" id="submitJoin">발송</button>
    </form>
    <form id="mailValidF" action="/mailValid" method="post">
    	<p>인증번호 이메일</p>
        <input name="address" placeholder="이메일 주소"> <br>
        <button type="button" id="submitValid">발송</button>
        <input type="text" id="validNum" name="validNum" placeholder="ㅇㅇㅇㅇㅇㅇ" style="display:none;">
    </form>
    <script src="static/js/jquery3.6.1.js"></script>
    <script>
    	$("#submitTest").on("click", function(e){
    		e.preventDefault();
    		$.ajax({
    			url: "/mailTest",
    			method: "POST",
    			data: $("#mailTestF").serialize(),
    			success: function(data){
    				alert("테스트 이메일 전송 완료");
    			},
    			error : function(xhr, status) {
   	                alert(xhr + " : " + status);
   	            }
    		});
    	});
    	
    	$("#submitJoin").on("click", function(e){
    		e.preventDefault();
    		$.ajax({
    			url: "/mailJoin",
    			method: "POST",
    			data: $("#mailJoinF").serialize(),
    			success: function(data){
    				alert("회원가입 이메일 전송 완료");
    			},
    			error : function(xhr, status) {
   	                alert(xhr + " : " + status);
   	            }
    		});
    	});
    	
    	$("#submitValid").on("click", function(e){
    		e.preventDefault();
    		$.ajax({
    			url: "/mailValid",
    			method: "POST",
    			data: $("#mailValidF").serialize(),
    			success: function(data){
    				alert("인증번호 이메일 전송 완료");
    				$("#validNum").css("display", "block");
    			},
    			error : function(xhr, status) {
   	                alert(xhr + " : " + status);
   	            }
    		});
    	});
    </script>
</body>
</html>