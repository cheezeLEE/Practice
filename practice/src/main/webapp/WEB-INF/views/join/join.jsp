<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="join" /></title>
</head>
<body>
	<div>
		<select name="lang" id="langSel">
			<option value="ko" <c:if test="${language eq 'ko'}">selected</c:if>><spring:message code="join.ko" /></option>
			<option value="en" <c:if test="${language eq 'en'}">selected</c:if>><spring:message code="join.en" /></option>
		</select>
		<h1><spring:message code="join" /></h1>
		<form action="/join" id="joinF" method="post">
			<div>
				<label for="userId"><spring:message code="join.id" /></label>
				<input type="text" name="userId" id="userId" value="" required/>
			</div>
			<div>
				<label for="userPw"><spring:message code="join.password" /></label>
				<input type="password" id="password" value="" required/>
				<input type="hidden" name="userPw" id="userPw" value="" required/>
			</div>
			<div>
				<label for="userName"><spring:message code="join.name" /></label>
				<input type="text" name="userName" id="userName" value="" oninput="this.value = this.value.replace(/[^ㄱ-ㅎ|가-힣]/g, '').replace(/(\..*)\./g, '$1');" required/>
			</div>
			<div>
				<label for="userEmail"><spring:message code="join.email" /></label>
				<input type="email" name="userEmail" id="userEmail" value="" required/>			
				<button type="button" id="validBtn"><spring:message code="join.certification" /></button>
			</div>
			<div id="validDiv" style="display:none;">
				<label for="validCode"><spring:message code="join.certificationNumber" /></label>
				<input type="text" name="validCode" id="validCode" value="" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" maxlength="6" required/>
				<span class="set-time">5:00</span>
				<button type="button" id="resendBtn"><spring:message code="join.resend" /></button>
			</div>
			<input type="hidden" name="auth" id="auth" value="USER"/>
			<div>
				<button type="submit" id="submitBtn"><spring:message code="join.join" /></button>
				<button type="reset" id="resetBtn"><spring:message code="join.reset" /></button>
				<button type="button" id="cancelBtn"><spring:message code="join.cancel" /></button>
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
/* 	Spring Security에서 BCryptPasswordEncoder를 사용하기 때문에 인코딩을 Java 단에서 수행함			
 				// [aes256 인코딩 함수 호출 실시]
	    		aes256Encode(aes256SecretKey, "", $('#password').val());
	    		console.log("인코딩 : " + aes256EncodeData);
				$("#userPw").val(aes256EncodeData);
				
	    		// [aes256 디코딩 함수 호출 실시]
//	    		aes256Decode(aes256SecretKey, "", aes256EncodeData);
//	    		console.log("디코딩 : " + aes256DecodeData); */
				
				$("#joinF").submit();				
			}else{
				alert("인증을 진행해주세요")
			}			
		});
		
		$("#langSel").on("change", function(){
			location.href="/join?language="+$("#langSel option:selected").val();
		});
	</script>
</body>
</html>