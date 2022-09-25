<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 결과</title>
</head>
<body>
	<p>아이디 : ${user.userId}</p>
	<p>비밀번호 : ${user.userPw}</p>
	<p>이름 : ${user.userNm}</p>
	<p>인증번호 : ${user.validCode}</p>
</body>
</html>