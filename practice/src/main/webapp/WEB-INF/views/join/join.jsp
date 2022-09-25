<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div>
		<h1>회원가입</h1>
		<form action="/join" id="joinF" method="post">
			<div>
				<label for="userId">아이디</label>
				<input type="text" name="userId" id="userId" value="" required/>
			</div>
			<div>
				<label for="userPw">비밀번호</label>
				<input type="password" name="userPw" id="userPw" value="" required/>
			</div>
			<div>
				<label for="userNm">이름</label>
				<input type="text" name="userNm" id="userNm" value="" required/>
			</div>
			<div>
				<label for="validCode">인증번호</label>
				<input type="text" name="validCode" id="validCode" value="" required/>
				<button type="button" id="validBtn">인증</button>
			</div>
			<div>
				<button type="submit" id="submitBtn">가입</button>
				<button type="reset" id="resetBtn">초기화</button>
				<button type="button" id="cancelBtn">취소</button>
			</div>
		</form>
	</div>
	<script src="static/js/jquery3.6.1.js"></script>
	<script>
		$("#cancelBtn").on("click", function(){
			location.href = "/";
		});
	</script>
</body>
</html>