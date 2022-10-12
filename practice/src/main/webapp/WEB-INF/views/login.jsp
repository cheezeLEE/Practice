<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <style>
        a {
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>
    <h1>로그인</h1>
    <form action="/login" id="loginForm" method="post">
        <div class="form_group">
            <label for="userId">아이디 </label>
            <input type="text" name="userId" id="userId" value="">
        </div>
        <div class="form_group">
            <label for="userId">비밀번호 </label>
            <input type="password" id="password" value="">
            <input type="hidden" name="userPw" id="userPw" value="">
        </div>
        <div class="form_group">
            <input type="submit" id="loginBtn" value="로그인">
        </div>
        <a href="#">아이디/</a><a href="#">비밀번호 찾기</a>
        <a href="/join">회원가입</a>
    </form>
    
	<script src="static/js/jquery3.6.1.js"></script>
    <script src="static/js/Crypto.js"></script>
    <script src="static/js/join.js"></script>
    <script>
    	$("#loginBtn").on("click", function(e){
    		e.preventDefault();

    		// [aes256 인코딩 함수 호출 실시]
    		aes256Encode(aes256SecretKey, "", "$('#password').val()");
    		console.log("인코딩 : " + aes256EncodeData);
			$("#userPw").val(aes256EncodeData);
			
    		// [aes256 디코딩 함수 호출 실시]
    		aes256Decode(aes256SecretKey, "", "jOFGjcAGiZRtePAncHBNBYrOmU/h6Zaw5V6D7jDxRwY=");
    		console.log("디코딩 : " + aes256DecodeData);

			$("#loginForm").submit();
    	});
    </script>
</body>
</html>