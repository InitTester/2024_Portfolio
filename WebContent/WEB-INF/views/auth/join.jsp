<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.user.message.MemberMessageEnum" %>
    <!--================================
            START SIGNUP AREA
    =================================-->
    <section class="signup_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <form id="joinForm">
                        <div class="cardify signup_form">
                            <div class="login--header">
                                <h3>Create Your Account</h3>
                                <p>Please fill the following fields with appropriate information to register a new MartPlace
                                    account.
                                </p>
                            </div>
                            <!-- end .login_header -->

                            <div class="login--form">

                                <div class="form-group">
                                    <label for="memberId">Your Id</label>
                                    <input id="memberId" name= "memberId" type="text" class="text_field" placeholder="Enter your Id">
                                    <div id="msgId" class="msg"></div>
                                </div>

                                <div class="form-group">
                                    <label for="email_ad">Email Address</label>
                                    <input id="email_ad" name="email" type="text" class="text_field" placeholder="Enter your email address">
                                    <div id="msgEmail" class="msg"></div>
                                </div>

                                <div class="form-group">
                                    <label for="user_name">Username</label>
                                    <input id="user_name" name= "memberNm" type="text" class="text_field" placeholder="Enter your username...">
                                    <div id="msgName" class="msg"></div>
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input id="password" name="passwd" type="password" class="text_field" placeholder="Enter your password...">
                                    <div id="msgPwd" class="msg"></div>
                                </div>

                                <div class="form-group">
                                    <label for="con_pass">Confirm Password</label>
                                    <input id="con_pass" type="password" name="confirmPasswd" class="text_field" placeholder="Confirm password">
                                    <div id="msgPwdConfirm" class="msg"></div>
                                </div>

                                <button class="btn btn--md btn--round register_btn" >Register Now</button>

                                <div class="login_assist">
                                    <p>Already have an account?
                                        <a href="<c:url value='/auth/loginPage.do'/>">Login</a>
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
            END SIGNUP AREA
    =================================-->

	<script>
		/* JS 유효성 검사, 정규식 */
	    /* input */
	    const user_id = document.getElementById("memberId");
	    const user_email = document.getElementById("email_ad");
	    const user_name = document.getElementById("user_name");
	    const user_pwd = document.getElementById("password");
	    const user_pwdConfirm = document.getElementById("con_pass");
	    
	    /* 이메일, 비밀번호에 대한 정규식 */
	    const id = /^(?=.*[a-z0-9])[a-z0-9]{4,20}$/;
	    const name = /^[a-zA-Z가-힣]{2,50}$/;
	    const pwd = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=\-.])(?=.*[0-9]).{8,20}$/;
	    const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	    validateInput(user_id,id,"msgId","유효하지 않은 이메일 형식입니다.");
	    validateInput(user_email,email,"msgEmail","유효하지 않은 이메일 형식입니다.");
	    validateInput(user_name, name, "msgName", "최소 2자 이상 입력, 숫자는 입력할 수 없습니다.");
	    validateInput(user_pwd, pwd, "msgPwd", "비밀번호는 8~20자, 영문, 숫자, 특수문자를 혼합하여 입력해주세요");
	    validateInput(user_pwdConfirm, pwd, "msgPwdConfirm", "비밀번호는 8~20자, 영문, 숫자, 특수문자를 혼합하여 입력해주세요");
	    
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
	    
	    /* form */
	    let joinForm = document.querySelector("#joinForm");

	    joinForm?.addEventListener("submit",function(e) {
	        e.preventDefault();
	        
	        if(!(user_id.test(user_email.value))) {
	            setMessage("유효하지 않은 아이디 형식입니다.","memberId", "msgId", "red");
	            return false;
	        }
	        if(!(email.test(user_email.value))) {
	            setMessage("유효하지 않은 이메일 형식입니다.","user_email", "msgEmail", "red");
	            return false;
	        }

	        if (!(name.test(user_name))) {
	            setMessage('최소 2자 이상 입력해주세요.', "user_name", "msgName", "red");
	            return false;
	        }

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

	        joinForm.action = "<c:url value='/auth/join.do'/>";
	        joinForm.method = 'POST';
	        joinForm.submit();
	    })
	
	</script>


</html>