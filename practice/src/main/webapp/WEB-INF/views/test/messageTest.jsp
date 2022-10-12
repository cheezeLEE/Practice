<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
<head>
    <title><spring:message code="member.register" /></title>
</head>
<body>
    <h2><spring:message code="term" /></h2>
    <div>
	    <button type="button"><spring:message code="term.agree" /></button>
	    <button type="button"><spring:message code="next.btn" /></button>    
    </div>
    
    <br/>
    
   	<h2><spring:message code="member.info" /></h2>
    <div>
    	<div>
	    	<label for="email"><spring:message code="email" /></label>
	    	<input type="email" name="email" id="email" value="">
    	</div>
    	<div>
	    	<label for="email"><spring:message code="name" /></label>
	    	<input type="text" name="name" id="name" value="">    		
    	</div>
    	<div>
	    	<label for="password"><spring:message code="password" /></label>
	    	<input type="password" name="password" id="password" value="">    		    	
    	</div>
    	<div>
	    	<label for="passwordConfirm"><spring:message code="password.confirm" /></label>
	    	<input type="password" name="passwordConfirm" id="passwordConfirm" value="">    	
    	</div>
    	<button type="button" id="registerBtn"><spring:message code="register.btn" /></button>
    	<br/>
    	<div id="join">
    	
    	</div>
    </div>
	<script src="static/js/jquery3.6.1.js"></script>
    <script>
    	$("#registerBtn").on("click", function(){
    		var name = $("#name").val();
    		// javascript로 {0}의 변수 넣는방법 찾아보기
    		$("#join").text(name+'<spring:message code="register.done" />');
    	});
    </script>
</body>