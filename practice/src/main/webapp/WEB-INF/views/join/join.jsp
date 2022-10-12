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
				<input type="password" id="password" value="" required/>
				<input type="hidden" name="userPw" id="userPw" value="" required/>
			</div>
			<div>
				<label for="userName">이름</label>
				<input type="text" name="userName" id="userName" value="" oninput="this.value = this.value.replace(/[^ㄱ-ㅎ|가-힣]/g, '').replace(/(\..*)\./g, '$1');" required/>
			</div>
			<div>
				<label for="userEmail">이메일</label>
				<input type="email" name="userEmail" id="userEmail" value="" required/>			
				<button type="button" id="validBtn">인증</button>
			</div>
			<div id="validDiv" style="display:none;">
				<label for="validCode">인증번호</label>
				<input type="text" name="validCode" id="validCode" value="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="6" required/>
				<span class="set-time">5:00</span>
				<button type="button" id="resendBtn">재전송</button>
			</div>
			<div>
				<button type="submit" id="submitBtn">가입</button>
				<button type="reset" id="resetBtn">초기화</button>
				<button type="button" id="cancelBtn">취소</button>
			</div>
		</form>
	</div>
	<script src="static/js/jquery3.6.1.js"></script>
    <script src="static/js/Crypto.js"></script>
    <script src="static/js/join.js"></script>
    <script>
	    let certificationYN = "N";	    
	
	    $("#resetBtn").on("click", function(){
	      clearInterval(timer);
	      console.log("타이머 초기화");
	      Timer(300);
	    });
	
		$("#cancelBtn").on("click", function(){
			location.href = "/";
		});
		
		$("#validBtn").on("click", function(e){
			address = $("#userEmail").val();
			if(address != null && address != 'undefined'){
			  // 이메일 전송
				emailSend(address);
			} else{
				console.log("이메일을 입력해주세요::::::::"+address);
			}
		});
				
		$("#resendBtn").on("click", function(e){
			address = $("#userEmail").val();
			//타이머 초기화
			clearInterval(timer);
		    emailSend(address);
			alert("인증번호 재발송");
			Timer(300);
		});
		
		// 인증번호 validation check : 6자리를 입력했을때만 처리
		$("#validCode").on("change", function(e){
			if($("#validCode").val().length == 6){
				address = $("#userEmail").val();
				inputCode = $("#validCode").val();
				$.ajax({
					type: "post",
					url: "/certification",
					data:{"address": address, "inputCode":inputCode},
					success: function(result){
						alert(result);
						    clearInterval(timer);
							certificationYN = "Y";
					},
				    error : function(request,status){
				    	certificationYN = "N";
				    	if(request.status === 400){
				    		alert("인증번호를 확인해주세요.");
				    	}else if(request.status === 401){
				    		alert("세션이 만료되었습니다. 재전송 후 다시 입력해주세요.");
				    	}else{
				    		alert("서버오류입니다.");
				    	}
					}
				});
			}else{
				certificationYN = "N";
			}
		});
		
		$("#submitBtn").on("click", function(e){
			e.preventDefault();
						
			if(certificationYN == 'Y'){
				
				// [aes256 인코딩 함수 호출 실시]
	    		aes256Encode(aes256SecretKey, "", $('#password').val());
	    		console.log("인코딩 : " + aes256EncodeData);
				$("#userPw").val(aes256EncodeData);
				
	    		// [aes256 디코딩 함수 호출 실시]
//	    		aes256Decode(aes256SecretKey, "", aes256EncodeData);
//	    		console.log("디코딩 : " + aes256DecodeData);
				
				$("#joinF").submit();				
			}else{
				alert("인증을 진행해주세요")
			}			
		});
	</script>
</body>
</html>