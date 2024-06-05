<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.portfolio.www.user.message.MemberMessageEnum" %>
<%
String ctx = request.getContextPath();
%>
    <style>
        .msg {
        	color: red;
        }
    </style>
    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="pass_recover_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                        <div class="cardify recover_pass">
                            <div class="login--header">
                                <p>귀하의 계정에 대한 ID, 이메일 주소를 입력하십시오. 인증 코드가 전송됩니다. 인증 코드를 받으면 계정에 대한 새 비밀번호를 선택할 수 있습니다.</p>
                            </div>
                            <!-- end .login_header -->

                            <div class="login--form">
                                <div class="form-group">
                                    <label for="memberId">아이디</label>
                                    <input id="memberId" type="text" name="memberId" class="text_field" placeholder="아이디를 입력해주세요">  
                                    <div id="msgId" class="msg"></div>
                                </div>    
                                
                                <div class="form-group">                                                     
                                    <label for="email_ad">이메일 주소</label>
                                    <input id="email_ad" type="text" name="email" class="text_field" placeholder="이메일 주소를 입력해주세요">
                                    <div id="msgEmail" class="msg"></div>
                                </div>

                                <button class="btn btn--md btn--round register_btn" type="submit" onclick="findPass();">비밀번호 찾기</button>
                            </div>
                            <!-- end .login--form -->
                        </div>
                        <!-- end .cardify -->
                </div>
                <!-- end .col-md-6 -->
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
    <!--================================
            END DASHBOARD AREA
    =================================-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
	
		const id = /^(?=.*[a-z0-9])[a-z0-9]{4,20}$/;
		const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	
	    const user_id = document.getElementById("memberId");
		const user_email = document.getElementById('email_ad');

	    validateInput(user_id,id,"msgId","아이디를 확인해주세요");
	    validateInput(user_email,email,"msgEmail","올바른 이메일 주소를 입력해주세요");
	    
		function findPass() {
	        
	        if(user_id === null || user_id.value === ''){
	            setMessage("아이디를 입력해주세요","memberId", "msgId", "red");
	            return false;
	        }
	        
			if(user_email === null || user_email.value ===''){
	            setMessage("이메일 주소를 입력해주세요.","email_ad", "msgEmail", "red");
	            return false;
			}
			
	        if(!(email.test(user_email.value))) {
	            setMessage("유효하지 않은 이메일 형식입니다.","user_email", "msgEmail", "red");
	            return false;
	        }	        
	        
			let url = '<%=ctx%>/auth/recoverPassSend.do?';
			url += 'email='+user_email.value
			url += '&memberId='+user_id.value
			
			$.ajax({
				type : 'POST',     
				url : url,
				headers : {
					"Content-Type" : "application/json"
				},
				/* dataType : 'text', */
				// 결과 성공 콜백함수 
				success : function(result) {				
					console.dir(result);		            
				},
				// 결과 에러 콜백함수
				error : function(request, status, error) {
		            alert('<%= MemberMessageEnum.INVALID_ID_OR_EMAIL.getDescription()%>');
					console.log(error)
				}
			});
		}
		
	    function validateInput(inputElement, regex, msgElement, errorMessage) {
	        inputElement?.addEventListener("keyup", () => {
	            if (!regex.test(inputElement.value) && inputElement.value.length !== 0) {
	                setMessage(errorMessage, inputElement.id, msgElement, "red");
	            } else {
	                setMessage('', inputElement.id, msgElement, "black");
	            }
	        });
	    }
	    
	    /* 유효성(입력된 값에 따른 변화 style,value) */
	    function setMessage(msg, elementId, msgId, color){
	        document.getElementById(msgId).innerHTML = msg;
	        document.getElementById(elementId).style.border = "1px solid " + color;
	    }
	</script>