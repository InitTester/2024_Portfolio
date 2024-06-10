<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.user.message.MemberMessageEnum" %>
<%
String ctx = request.getContextPath();

%>
	<style>
	
        .msg {
        	color: red;
        }
	</style>
	
<c:set var="memberId" value="${empty dto.memberId ? memberId : dto.memberId}"/>

    <!--================================
            START LOGIN AREA
    =================================-->
    <section class="login_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <form id="formLogin">   
                        <div class="cardify login">
                            <div class="login--header">
                                <h3>아이디로 로그인</h3>                                
                            </div>
                            <!-- end .login_header -->
                            <div class="login--form">
                                <div class="form-group">
                                    <label for="memberId">아이디</label>
                                    <%-- <input id="memberId" type="text" name="memberId" value="${memberId}" class="text_field" placeholder="아이디를 입력해주세요"> --%>
                                    <input id="memberId" type="text" name="memberId" value="test" class="text_field" placeholder="아이디를 입력해주세요">
                                    <div id="msgId" class="msg"></div>
                                </div>

                                <div class="form-group">
                                    <label for="password">비밀번호</label>
                                    <%-- <input id="password" type="password" name="passwd" value="${dto.passwd}" class="text_field" placeholder="비밀번호를 입력해주세요"> --%>
                                    <input id="password" type="password" name="passwd" value="test1234!" class="text_field" placeholder="비밀번호를 입력해주세요">
                                    <div id="msgPwd" class="msg"></div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="custom_checkbox">
                                        <input type="checkbox" id="ch2" name="rememberId" value="on" ${empty cookie.rememberSeq.value ? "" : "checked"}>
                                        <label for="ch2">
                                            <span class="shadow_checkbox"></span>
                                            <span class="label_text">아이디 저장</span>
                                        </label>
                                    </div>
                                </div>

                                <button class="btn btn--md btn--round" type="submit">로그인</button>

                                <div class="login_assist">
                                    <p class="recover">잊어버리셨나요 ? 
                                        <a href="<c:url value='/auth/findIdPage.do'/>">아이디 찾기</a>or<a href="<c:url value='/auth/recoverPassPage.do'/>">비밀번호 재설정</a>
                                        </br>
                                        회원이 아니신가요? <a href="<c:url value='/auth/joinPage.do'/>">회원가입</a>
                                    </p>
                            
                                </div>
                            </div>
                            <!-- end .login--form -->
                        </div>
                        <!-- end .cardify -->
                    </form>
                </div>
                <!-- end .col-md-6 -->
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
    <!--================================
            END LOGIN AREA
    =================================-->
    <script type="text/javascript">

		window.onload=function(){
			
			var code = '${code}';
			var msg = '${msg}'.replace(/\\n/g,'\n');

			if(code !== '' && code !== '<%= MemberMessageEnum.SUCCESS.getCode() %>' && code !== null){

	            alert(msg);
			}
		} 
		
	    const user_id = document.getElementById("memberId");
	    const user_pwd = document.getElementById("password");

	    const id = /^(?=.*[a-z0-9])[a-z0-9]{4,20}$/;
	    const pwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=\-.])(?=.*[0-9]).{8,20}$/;

	    validateInput(user_id,id,"msgId","아이디를 입력해주세요");
	    validateInput(user_pwd, pwd, "msgPwd", "비밀번호를 입력해주세요.");
	    
	    function validateInput(inputElement, regex, msgElement, errorMessage) {
	        inputElement?.addEventListener("keyup", () => {
	            if (!regex.test(inputElement.value) && inputElement.value.length !== 0) {
	                setMessage(errorMessage, inputElement.id, msgElement, "red");
	            } else {
	                setMessage('', inputElement.id, msgElement, "black");
	            }
	        });
	    }
	    
	    
	    /* form */
	    let formLogin = document.querySelector("#formLogin");

	    formLogin?.addEventListener("submit",function(e) {
	        e.preventDefault();
	        
	        if(user_id === null || user_id.value === ''){
	            setMessage("아이디를 입력해주세요","memberId", "msgId", "red");
	            return false;
	        }
	        
	        if(!(id.test(user_id.value))) {
	            setMessage("유효하지 않은 아이디 형식입니다.","memberId", "msgId", "red");
	            return false;
	        }
	        
	        if(user_pwd === null || user_pwd.value === ''){
	            setMessage("비밀번호를 입력해주세요.","password", "msgPwd", "red");
	            return false;
	        }	        

        	formLogin.action = "<c:url value='/auth/login.do'/>";
        	formLogin.method = 'POST';
        	formLogin.submit();
	    })		
	    
	    function setMessage(msg, elementId, msgId, color){
	        document.getElementById(msgId).innerHTML = msg;
	        document.getElementById(elementId).style.border = "1px solid " + color;
	    }
	</script>
	