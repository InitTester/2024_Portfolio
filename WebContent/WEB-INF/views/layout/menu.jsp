<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String ctx = request.getContextPath();
%>
<style>
	.hidden{
		display: none;
	}
    .msg {
    	color: red;
    }	
</style>

<!-- 로그인 정보 -->
<c:set var="loginInfo" value="${not empty sessionScope.memberSeq ? 'hidden' : ''}"/>
<c:set var="userprofileImg" value="${not empty cookie.profileImg.value ? '/images/usr_avatar.png' : '/user/img/'}"/>

    <!-- ================================
	    START MENU AREA
	================================= -->
    <!-- start menu-area -->
    <div class="menu-area">
        <!-- start .top-menu-area -->
        <div class="top-menu-area">
            <!-- start .container -->
            <div class="container">
                <!-- start .row -->
                <div class="row_menu">
                    <!-- start .col-md-3 -->
                    <div class="col-lg-3 col-md-3 col-6 v_middle">
                        <div class="logo">                        
                            <a href="<c:url value='/index.do'/>">                            	
                                <%-- <img src="<c:url value='/assest/template/images/logo.png'/>" alt="logo image" class="img-fluid" /> --%>
                                <img src="<c:url value='/images/logo.png'/>" alt="logo image" class="img-fluid" />
                            </a>
                        </div>
                    </div>
                    <!-- end /.col-md-3 -->

                    <!-- start .menu_middle -->
                    <div class="menu_middle">
                        <nav class="navbar navbar-expand-md navbar-light mainmenu__menu">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav">
                                    <%--<li class ="has_dropdown">
                                        <a href="<c:url value='/index.do'/>">홈</a>
                                    </li> --%>
                                     <li class="has_dropdown">
                                        <a href="<c:url value='/aboutMe.do'/>">About Me</a>
                          				<div class="dropdowns dropdown--menu">
                                            <ul>
                                                <li>
                                                    <a href="<c:url value='/aboutMe.do'/>">AboutMe</a>
                                                </li>
                                                <li>
                                                    <a href="https://github.com/InitTester/2024_Portfolio" target="_blank">Portfolio Source Code</a>
                                                </li>
                                                <li>
                                                    <a href="https://initsave.tistory.com/category/Error" target="_blank">Trouble Shooting's</a>
                                                </li>
                                            </ul>
                                        </div>                                        
                                    </li>
                                                                       
                                    <li class="has_dropdown">
                                        <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=2'/>">게시판</a>
                                        <div class="dropdowns dropdown--menu">
                                            <ul>
                                                <li>
                                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=2'/>">자유게시판</a>
                                                </li>
                                                <li>
                                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=1'/>">공지사항</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>                                    
                                    <li>
                                        <a href="#" data-target="#myModal1" data-toggle="modal">연락하기</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- /.navbar-collapse -->
                        </nav>
                    </div>
                    <!-- end /.menu_middle -->

                    <!-- start .menu_bottom -->
                    <div class="menu_bottom">
                        <!-- start .author-area -->
                        <div class="author-area not_logged_in">
                            <!-- 회원가입/로그인 -->
                            <div class="maxWidth">
	                            <c:if test="${empty sessionScope.memberSeq }">
		                            <div class="pull-right join desktop-size d-md-block d-none ${loginInfo}" >
		                                <%-- <a href="<c:url value='/auth/joinPage.do'/>" class="btn btn--sm btn--round btn-list">회원가입</a> --%>
		                                <a href="<c:url value='/auth/loginPage.do'/>" class="btn--login">로그인</a>
		                            </div>
	                            </c:if>
	                            
	                            <c:if test="${not empty sessionScope.memberSeq}">                          
	                            	<!-- 로그인 정보 -->
		                            <div class="author-author__info inline has_dropdown">
		                                <div class="author__avatar">
		                                    <img src="<c:url value='/images/usr_avatar.png'/>" alt="user avatar">
		                                </div>
		                                <div class="autor__info">
		                                    <p class="name">
		                                         ${cookie.memberNm.value} 
		                                    </p>
		                                </div>
		
		                                <div class="dropdowns dropdown--author">
		                                    <ul>
		                                        <li>
		                                            <a href="#">
		                                                <span class="lnr lnr-user"></span>프로필</a>
		                                        </li>
<!-- 		                                        <li>
		                                            <a href="#">
		                                                <span class="lnr lnr-cog"></span> Setting</a>
		                                        </li> -->
		                                        <li>
		                                            <a href="<c:url value='/auth/logout.do'/>">
		                                                <span class="lnr lnr-exit"></span>로그아웃</a>
		                                        </li>
		                                    </ul>
		                                </div>
		                            </div>
	                            </c:if>
                            </div>
                            
                            <div class="pull-right join mobile-size d-md-none d-flex">	
	                            <c:if test="${empty sessionScope.memberSeq }">
	                                <a href="<c:url value='/auth/loginPage.do'/>" class="btn btn--round btn-secondary ">
	                                	<span class="lnr lnr-user"></span>
	                                </a>
	                            </c:if>
	                            
	                            <c:if test="${not empty sessionScope.memberSeq}">                          
	                            	<!-- 로그인 정보 -->
<!-- 		                            <div class="author-author__info inline">
		                            	<div class="author__avatar">
		                                    <img src="/pf/images/usr_avatar.png" alt="user avatar">
		                                </div>
		                            </div> -->
		                            
	                               <a href="<c:url value='/auth/logout.do'/>" class="btn btn--round">
	                               		<span class="lnr lnr-enter"></span>
	                               </a>     		                            
	                            </c:if>   
                            </div>
                        </div>
                        <!-- end .author-area -->
                    </div>
                    <!-- end /.menu_bottom -->
                </div>
                <!-- end /.row -->
            </div>
            <!-- end /.container -->
        </div>
        <!-- end  -->
    <!--================================
	    END MENU AREA
	=================================-->
	
    <!-- Modals -->
    <div class="modal fade rating_modal" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="rating_modal">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">       
                    <h3 class="modal-title" id="rating_modal">Contact Me</h3>
                    <p>안녕하세요! </p>
                    <p>저에 대해서 좀더 궁금하신 사항이 있으시다면</p>
                    <p>성함, 이메일주소(메세지)를 보내주세요</p>
                    <p>해당 주소로 <img src="https://img.icons8.com/?size=50&id=23877&format=png" width=40px height=40px>
                        이력서를 보내드립니다!  
                    </p>
                </div>
                <!-- end /.modal-header -->

                <div class="modal-body">
                    <form id="modalForm"> <%-- action="<c:url value='/contactMe.do'/>" method="post"> --%> 
                    	<div id="nameInput" class="form-floating mb-2">
                    	<input id="name" type="text" class="form-control" name="name" control-id="ControlID-1" placeholder="성함">
                            <!-- <label for="name">성함</label> -->
                            <div id="msgName" class="msg"></div>
                        </div>
                        <div id="emailInput" class="form-floating mb-2">
                            <input id="email-input" type="text" class="form-control" name="email" control-id="ControlID-2"  placeholder="이메일">
                            <!-- <label for="email-input">이메일</label> -->
                            <div id="msgEmail" class="msg"></div>
                        </div>
                        
                        <div class="rating_field">
                            <label for="rating_field">문의사항 메세지</label>
	                        <div class="inquiryInput">
	                           <div class="select-wrap">
	                               <select name="inquiryList" id="rev" control-id="ControlID-1">
	                                   <option value="resume">이력서문의</option>
	                                   <option value="cte">기타문의</option>
	                               </select>
	                               <span class="lnr lnr-chevron-down"></span>
	                           </div>
	                       	</div>                            
                            <textarea name="rating_field" id="rating_field" class="text_field" placeholder="이력서 문의는 [이력서문의] 선택하시고 메세지 쓰지 않으셔도 됩니다"></textarea>
                            <!-- <p class="notice"> </p> -->
                        </div>
                        <div class ="modalbtn_contactme">                        
	                        <button type="submit" class="btn btn--sm btn--round submit">연락하기</button>
	                        <button class="btn btn--round modal_close" data-dismiss="modal">취소</button>
                        </div>
                    </form>
                    <!-- end /.form -->
                </div>
                <!-- end /.modal-body -->
            </div>
        </div>
    </div>
    <!-- Modals End -->    
    
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>    
    <script>
    

    const user_email = document.getElementById("email-input");
    const user_name = document.getElementById("name");
    const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    
    validateInput(user_email,email,"msgEmail","유효하지 않은 이메일 형식입니다");
    
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
    
    $(document).ready(function(){
        $('#modalForm').on('submit', function(event){
            event.preventDefault(); // 폼의 기본 제출 동작을 방지

		    let url = `<%=ctx%>/contactMe.do`;
		    
	        if (user_name.value.length === 0 ) {
	            setMessage('성함을 입력해주세요.', "name", "msgName", "red");
	            return false;
	        }
	        
	        if(!(email.test(user_email.value))) {
	            setMessage("유효하지 않은 이메일 형식입니다.","email-input", "msgEmail", "red");
	            return false;
	        }

            let contactMeDto = {
                name: $('#name').val(),
                email: $('#email-input').val(),
                inquiry: $('#inquiryList').val(),
                message: $('#rating_field').val()
            };
            
            $.ajax({
                type: 'POST',
                url: url,
                contentType: 'application/json',
                data: JSON.stringify(contactMeDto),
                success: function(response){
                    // 서버 응답 성공 처리
                    console.log('Data submitted successfully');
                    $('#myModal1').modal('hide'); // 모달 창 닫기
                    
                    $('#modalForm')[0].reset();
                },
                error: function(error){
                    // 서버 응답 실패 처리
                    console.error('Error submitting data', error);
                }
            });
        });

        $('.modal_close').on('click', function() {
            setMessage('', "name", "msgName", "#ced4da");
            setMessage('', "email-input", "msgEmail", "#ced4da");
            $('#modalForm')[0].reset();
        });
    });
    </script>