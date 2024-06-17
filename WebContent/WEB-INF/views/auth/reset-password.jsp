<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        
        .reset--header{
          border-bottom: 1px solid #ececec;
		  text-align: center;
    	  color: #000;
		  font-size: 26px;
		  }
		  
		  .form-group{
            margin-top: 20px;
		  }
    </style>
    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="pass_recover_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                
                	<!-- 비밀번호 입력전 -->
                     <div class="cardify recover_pass">
                         <div class="reset--header">
                             <p>귀하의 계정에 대한 새 비밀번호를 입력해주세요.</p>
                         </div>
                         <!-- end .login_header -->

                         <div class="login--form">
                             <div class="form-group">
                                   <label for="password">비밀번호</label>
                                   <input id="password" name="passwd" type="password" class="text_field" value="${dto.passwd}" placeholder="변경할 비밀번호를 입력해주세요">
                                   <div id="msgPwd" class="msg"></div>
                               </div>

                               <div class="form-group">
                                   <label for="con_pass">비밀번호 확인</label>
                                   <input id="con_pass" type="password" name="confirmPasswd" class="text_field" value="${dto.confirmPasswd}" placeholder="변경할 비밀번호를 확인합니다">
                                   <div id="msgPwdConfirm" class="msg"></div>
                               </div>     
                             
                             <button class="btn btn--md btn--round" type="submit" onclick="changePasswd();">비밀번호 재설정</button>
                         </div>
                         <!-- end .login--form -->
                     </div>
                     <!-- end .cardify -->
                     
                     <!-- 비밀번호 입력 후 -->
                     <div class="cardify result-section">
                         <div class="reset--header">
                             <p>귀하의 계정에 대한 비밀번호 변경 완료</p>
                         </div>
                         
                         <!-- end .login_header -->

                         <div class="login--form">
                             <div class="form-group">
                             	안녕하세요	<b><strong id='nickname' style="font-size: 35px;"></strong></b>님! 비밀번호가 변경되었습니다.
                             	<p class="text">변경된 비밀번호로 다시 로그인 해주세요.
                             	고객님의 소중한 개인정보 보안을 위해 최선을 다하겠습니다.</p>
                             </div>

                             <button class="btn btn--md btn--round register_btn" type="submit" onclick="goToLogin();">로그인</button>
                         </div>
                         <!-- end .login--form -->
                     </div> 
                <!-- end .col-md-6 -->
                </div>
            <!-- end .row -->
            </div>
        </div>
        <!-- end .container -->
    </section>
    <!--================================
            END DASHBOARD AREA
    =================================-->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>	

    	const pwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=\-.])(?=.*[0-9]).{8,20}$/;

	    const user_pwd = document.getElementById("password");
	    const user_pwdConfirm = document.getElementById("con_pass");
	    
	    validateInput(user_pwd, pwd, "msgPwd", "비밀번호는 8~20자, 영문, 숫자, 특수문자를 혼합하여 입력해주세요");
	    validateInput(user_pwdConfirm, pwd, "msgPwdConfirm", "비밀번호는 8~20자, 영문, 숫자, 특수문자를 혼합하여 입력해주세요");
	    
		function changePasswd() {
			
	        if (user_pwd.value.length < 8 && user_pwd.value.length > 20 ) {
	            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "password", "msgPwd", "red");
	            return false;
	        }

	        if (user_pwdConfirm.value.length < 8 && user_pwdConfirm.value.length > 20 ) {
	            setMessage('비밀번호는 8자 이상, 20자 이하로 입력하세요.', "con_pass", "msgPwd", "red");
	            return false;
	        }

	        if(!(user_pwd.value===user_pwdConfirm.value)){
	            setMessage('비밀번호가 일치하지 않습니다.', "con_pass", "msgPwdConfirm", "red");
	            return false;
	        }
	        
			let url = '<%=ctx%>/auth/resetPass.do';
			url += window.location.search,
			url += '&passwd='+user_pwd.value
			
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
					console.log('request :L' + request);
					console.log('statis ;' +status);
					
		            alert('<%= MemberMessageEnum.FAIL_AUTH_EMAIL_ERR3.getDescription()%>');
					console.log('error ' +error)
				}
			});
		}
		
		function displayResult(result){ 
			const recoverPass = document.querySelector('.recover_pass');
		    const resultSection = document.querySelector('.result-section');
		    const nickname = document.getElementById('nickname');
		    const resultHeader = document.getElementById('resultheader');

		    recoverPass.style.display = 'none';
		    resultSection.style.display = 'block';

		    nickname.textContent  = result.memberNm;
		    
		    
		    /* if (result.memberNm) {
		    	
		    	nickname.value = ``;
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
		    } */
		}
		
		function goToLogin() {
		    window.location.href = '<%=ctx%>/auth/loginPage.do'; // 로그인 페이지로 이동
		}		
		
	    function validateInput(inputElement, regex, msgElement, errorMessage) {
	        inputElement?.addEventListener("keyup", () => {
	            if (!regex.test(inputElement.value) && inputElement.value.length !== 0) {
	                setMessage(errorMessage, inputElement.id, msgElement, "red");
	            } else {
	                setMessage('', inputElement.id, msgElement, "#00000000");
	            }
	        });
	    }
	    
	    /* 유효성(입력된 값에 따른 변화 style,value) */
	    function setMessage(msg, elementId, msgId, color){
	        document.getElementById(msgId).innerHTML = msg;
	        document.getElementById(elementId).style.border = "1px solid " + color;
	    }
	</script>



