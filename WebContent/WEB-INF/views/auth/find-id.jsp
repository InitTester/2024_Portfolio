<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.portfolio.www.user.message.MemberMessageEnum" %>

<%
String ctx = request.getContextPath();
%>
    <style>
        .result-section {
            display: none;
            margin-top: 20px;
		    border-bottom: 1px solid #ececec;
		    text-align: center;
		    padding: 0 50px 26px;            
        }
        
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
                
                    <!-- 이름,이메일 입력 전  --> 
                     <div class="cardify recover_pass">
                         <div class="login--header">
                             <p>귀하의 계정에 대한 이름, 이메일 주소를 입력하십시오. 귀하의 아이디가 전송됩니다.</p>
                         </div>
                         <!-- end .login_header -->

                         <div class="login--form">
                             <div class="form-group">
                                 <label for="memberNm">이름 </label>
                                 <input id="memberNm" type="text" name="memberNm" class="text_field" placeholder="이름을 입력해주세요">
                                 <div id="msgName" class="msg"></div>
                             </div>
                             <div class="form-group">                                 
                                 <label for="email_ad">이메일 주소</label>
                                 <input id="email_ad" type="text" name="email" class="text_field" placeholder="이메일 주소를 입력해주세요">
                                 <div id="msgEmail" class="msg"></div>
                             </div>

                             <button class="btn btn--md btn--round register_btn" type="button" onclick="findId();">아이디 찾기</button>
                         </div>
                         <!-- end .login--form -->
                     </div>
                     <!-- end .cardify -->
                     
                    <!-- 이름,이메일 입력 후  --> 
                     <div class="cardify result-section">
                         <div class="login--header">
                     		<p id="resultheader"></p>
                         </div>
                         <!-- end .login_header -->

                         <div class="login--form">
                             <div class="form-group">
                       			<ul id="resultList" class="text_field"></ul>
                             </div>

                             	<button class="btn btn--md btn--round register_btn" type="button"onclick="goToLogin()">로그인하러 가기</button>
		                       <button class="btn btn--md btn--round register_btn"  type="button" onclick="resetPassword()">비밀번호 재설정</button>
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

    	const name = /^[a-zA-Z가-힣]{2,50}$/;
		const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		
		const user_name = document.getElementById('memberNm');
		const user_email = document.getElementById('email_ad');

	    validateInput(user_name, name, "msgName", "이름을 입력해주세요");
	    validateInput(user_email,email,"msgEmail","올바른 이메일 주소를 입력해주세요");
    
		function findId() {
			
			if(user_name === null || user_name.value ===''){
	            setMessage('최소 2자 이상 입력해주세요.', "memberNm", "msgName", "red");
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
			
			let url = '<%=ctx%>/auth/findid.do?';
			url += 'memberNm='+user_name.value
			url += '&email='+user_email.value
			
			$.ajax({
				type : 'POST',     
				url : url,
				headers : {
					"Content-Type" : "application/json"
				},
				/* dataType : 'text', */
				// 결과 성공 콜백함수 
				success : function(result) {
				
					displayResult(result);
		            
				},
				// 결과 에러 콜백함수
				error : function(request, status, error) {
		            alert('<%= MemberMessageEnum.USER_NOT_FOUND.getDescription()%>');
		    /*         alert('사용자를 찾을 수 없습니다.');
					console.log(error) */
				}
			});
		}
		
		function displayResult(result){ 
			const recoverPass = document.querySelector('.recover_pass');
		    const resultSection = document.querySelector('.result-section');
		    const resultList = document.getElementById('resultList');
		    const resultHeader = document.getElementById('resultheader');

		    recoverPass.style.display = 'none';
		    resultSection.style.display = 'block';

		    resultList.innerHTML = '';
		    
		    if (result.memberNm) {
		    	
		        resultHeader.innerHTML = `<b>`+result.memberNm+`</b>님의 정보와 일치하는 아이디입니다.`;
		    
		        // 주어진 문자열
		        let dateString = result.joinDtm;

		        console.log(dateString);
		        
		        // 문자열에서 연, 월, 일 추출
		        let year = dateString.substring(0, 4);
		        let month = dateString.substring(4, 6);
		        let day = dateString.substring(6, 8);

	 	        // 원하는 형식으로 날짜 포맷팅
				let formattedDate = year + `년 `+ month + `월 ` +day + `일`;
		        
	            const li = document.createElement('li');
	            li.innerHTML = `<br>아이디: ` + result.memberId + `<br> 가입일: ` + formattedDate + `<br><br>`;
	            resultList.appendChild(li);
	            
		    } else {
		        resultHeader.textContent = '일치하는 사용자를 찾을 수 없습니다.';
		    }
		}
		
		function goToLogin() {
		    window.location.href = '<%=ctx%>/auth/loginPage.do'; // 로그인 페이지로 이동
		}

		function resetPassword() {
		    window.location.href = '<%=ctx%>/auth/recoverPassPage.do'; // 비밀번호 재설정 페이지로 이동
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