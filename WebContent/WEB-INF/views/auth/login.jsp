<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="com.portfolio.www.user.message.MemberMessageEnum" %>
<%
String ctx = request.getContextPath();

%>

<c:set var="memberId" value="${empty dto.memberId ? cookie.rememberId.value : dto.memberId}"/>

    <!--================================
            START LOGIN AREA
    =================================-->
    <section class="login_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <form id="formLogin" action="<c:url value='/auth/login.do'/>" method="post">   
                        <div class="cardify login">
                            <div class="login--header">
                                <h3>아이디로 로그인</h3>                                
                            </div>
                            <!-- end .login_header -->
                            <div class="login--form">
                                <div class="form-group">
                                    <label for="user_name">아이디</label>
                                    <input id="user_name" type="text" name="memberId" value="${memberId}" class="text_field" placeholder="아이디를 입력해주세요">
                                </div>

                                <div class="form-group">
                                    <label for="pass">비밀번호</label>
                                    <input id="pass" type="password" name="passwd" value="${dto.passwd}" class="text_field" placeholder="비밀번호를 입력해주세요">
                                </div>
                                
                                <div class="form-group">
                                    <div class="custom_checkbox">
                                        <input type="checkbox" id="ch2" name="passwdrememberId" value="on" ${empty cookie.rememberId.value ? "" : "checked"}>
                                        <label for="ch2">
                                            <span class="shadow_checkbox"></span>
                                            <span class="label_text">아이디 저장</span>
                                        </label>
                                    </div>
                                </div>

                                <button class="btn btn--md btn--round" type="submit">로그인</button>

                                <div class="login_assist">
                                    <p class="recover">잊어버리셨나요 ? 
                                        <a href="pass-recovery.html">아이디 찾기</a>or<a href="pass-recovery.html">비밀번호 재설정</a>
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
		
	</script>
	