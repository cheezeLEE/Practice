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
	<script>
	    let timer, address;
	    let certificationYN = "N";
	
	    /**
	     * @param {number} second 실행간격
	     * @param {function(callback, number):void} startInterval
	     */
	    function startInterval(seconds, callback) {
	      callback();
	      return setInterval(callback, seconds * 1000);
	    }
	
	    /**
	     * JavaScript Timer : 매 초마다 시간을 변경하고, 초기화시 처음부터 다시 시작하는 함수
	     * @param {number} time 기준시간 (초)
	     * @param {number} idx 몇번째 타이머 (0부터)  
	     */ 
	    function Timer(time){
	      let timeSet = $(".set-time");
	      time--;
	
	      timer = startInterval(1,function(){
	        min = parseInt(time/60);
	        sec = time%60;
	        if(sec >= 0 && sec < 10){
	          timeSet.text(min + ":0" + sec);
	        }else{
	          timeSet.text(min + ":" + sec);
	        }
	        time--;
	
	        if(time<0){
	          clearInterval(timer);
	          alert("시간초과");
	        }
	      });
	    }
	
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
			  $.ajax({
				 url: "/mailJoin2",
				 type: "post",
				 data: {"address": address},
				 success: function(data){
					 alert("인증번호를 전송했습니다.");
					 console.log("data : " + data);
					  // 이메일 전송 성공 시 타이머 동작
				      Timer(300);
					  $("#validBtn").css("display", "none");
					  $("#validDiv").css("display", "block");
				 }
			  });
			} else{
				console.log("이메일을 입력해주세요::::::::"+address);
			}
		});
				
		$("#resendBtn").on("click", function(e){
		    clearInterval(timer);
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
						console.log("result ::::::" + result);
						if(result == true){
							alert("인증완료");
						    clearInterval(timer);
							certificationYN = "Y";
						}else{
							alert("실패");
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
				alert("회원가입완료!!")
				$("#joinF").submit();				
			}else{
				alert("인증을 진행해주세요")
			}			
		});
	</script>
</body>
</html>