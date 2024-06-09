<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	.hidden{
		display: none;
	}
</style>

<!-- 로그인 정보 -->
<c:set var="loginInfo" value="${not empty cookie.memberId.value ? 'hidden' : ''}"/>
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
                <div class="row">
                    <!-- start .col-md-3 -->
                    <div class="col-lg-3 col-md-3 col-6 v_middle">
                        <div class="logo">                        
                            <a href="<c:url value='/index.do'/>">                            	
                                <img src="<c:url value='/assest/template/images/logo.png'/>" alt="logo image" class="img-fluid" />
                            </a>
                        </div>
                    </div>
                    <!-- end /.col-md-3 -->

                    <!-- start .col-md-5 -->
                    <div class="col-lg-8 offset-lg-1 col-6 col-md-9 v_middle">
                        <!-- start .author-area -->
                        <div class="author-area not_logged_in">
                            <!-- 회원가입/로그인 -->
                            <c:if test="${empty cookie.memberId.value }">
	                            <div class="pull-right join desktop-size d-md-block d-none ${loginInfo}" >
	                                <a href="<c:url value='/auth/joinPage.do'/>" class="btn btn--round btn-secondary  btn--xs">회원가입</a>
	                                <a href="<c:url value='/auth/loginPage.do'/>" class="btn btn--round btn--xs">로그인</a>
	                            </div>
                            </c:if>
                            
                            <c:if test="${not empty cookie.memberId.value}">                          
                            	<!-- 로그인 정보 -->
	                            <div class="author-author__info inline has_dropdown">
	                                <div class="author__avatar">
	                                    <img src="<c:url value='/images/usr_avatar.png'/>" alt="user avatar">
	
	                                </div>
	                                <div class="autor__info">
	                                    <p class="name">
	                                         ${cookie.memberNm.value} <!-- Jhon Doe -->
	                                    </p>
	                                  <!--   <p class="ammount">$20.45</p> -->
	                                </div>
	
	                                <div class="dropdowns dropdown--author">
	                                    <ul>
	                                        <li>
	                                            <a href="#">
	                                            <!-- <a href="author.html"> -->
	                                                <span class="lnr lnr-user"></span>Profile</a>
	                                        </li>
	                             <!--            <li>
	                                            <a href="dashboard.html">
	                                                <span class="lnr lnr-home"></span> Dashboard</a>
	                                        </li> -->
	                                        <li>
<!-- 	                                            <a href="dashboard-setting.html"> -->
	                                            <a href="#">
	                                                <span class="lnr lnr-cog"></span> Setting</a>
	                                        </li>
	                                       <!--  <li>
	                                            <a href="cart.html">
	                                                <span class="lnr lnr-cart"></span>Purchases</a>
	                                        </li>
	                                        <li>
	                                            <a href="favourites.html">
	                                                <span class="lnr lnr-heart"></span> Favourite</a>
	                                        </li>
	                                        <li>
	                                            <a href="dashboard-add-credit.html">
	                                                <span class="lnr lnr-dice"></span>Add Credits</a>
	                                        </li>
	                                        <li>
	                                            <a href="dashboard-statement.html">
	                                                <span class="lnr lnr-chart-bars"></span>Sale Statement</a>
	                                        </li>
	                                        <li>
	                                            <a href="dashboard-upload.html">
	                                                <span class="lnr lnr-upload"></span>Upload Item</a>
	                                        </li>
	                                        <li>
	                                            <a href="dashboard-manage-item.html">
	                                                <span class="lnr lnr-book"></span>Manage Item</a>
	                                        </li>
	                                        <li>
	                                            <a href="dashboard-withdrawal.html">
	                                                <span class="lnr lnr-briefcase"></span>Withdrawals</a>
	                                        </li> -->
	                                        <li>
	                                            <a href="<c:url value='/auth/logout.do'/>">
	                                                <span class="lnr lnr-exit"></span>Logout</a>
	                                        </li>
	                                    </ul>
	                                </div>
	                            </div>
                            </c:if>
                            
                            <div class="pull-right join mobile-size d-md-none d-flex">
                                <a href="#" class="btn btn--round btn-secondary "><span class="lnr lnr-user"></span></a>
                                <a href="#" class="btn btn--round"><span class="lnr lnr-enter"></span></a>
                            </div>
                        </div>
                        <!-- end .author-area -->
                    </div>
                    <!-- end /.col-md-5 -->
                </div>
                <!-- end /.row -->
            </div>
            <!-- end /.container -->
        </div>
        <!-- end  -->

        <!-- start .mainmenu_area -->
        <div class="mainmenu">
            <!-- start .container -->
            <div class="container">
                <!-- start .row-->
                <div class="row">
                    <!-- start .col-md-12 -->
                    <div class="col-md-12">
                        <nav class="navbar navbar-expand-md navbar-light mainmenu__menu">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="navbarNav">
                                <ul class="navbar-nav">
                                    <li class="has_dropdown">
                                        <a href="<c:url value='/index.do'/>">홈</a>
                                    </li>
                                     <li class="has_dropdown">
                                        <a href="#">프로필</a>
                                    </li>
                                                                       
                                    <li class="has_dropdown">
                                        <a href="#">게시판</a>
                                        <div class="dropdowns dropdown--menu">
                                            <ul>
                                                <li>
                                                    <a href="<c:url value='/forum//notice/listPage.do'/>">공지사항</a>
                                                </li>
                                                <li>
                                                    <a href="all-products.html">자유게시판</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>                                    
                                    <li>
                                        <%-- <a href="<c:url value='/contact.do'/>">문의하기</a> --%>
                                        <a href="<c:url value='#'/>">문의하기</a>
                                    </li>
                                </ul>
                            </div>
                            <!-- /.navbar-collapse -->
                        </nav>
                    </div>
                    <!-- end /.col-md-12 -->
                </div>
                <!-- end /.row-->
            </div>
            <!-- start .container -->
        </div>
        <!-- end /.mainmenu-->
    </div>
    <!-- end /.menu-area -->
    <!--================================
	    END MENU AREA
	=================================-->
	
	 <!--================================
        START BREADCRUMB AREA
    =================================-->
<!--     <section class="breadcrumb-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="breadcrumb">
                        <ul>
                            <li>
                                <a href="index.html">Home</a>
                            </li>
                            <li class="active">
                                <a href="#">Testimonials</a>
                            </li>
                        </ul>
                    </div>
                    <h1 class="page-title">Testimonials</h1>
                </div>
                end /.col-md-12
            </div>
            end /.row
        </div>
        end /.container
    </section> -->
    <!--================================
        END BREADCRUMB AREA
    =================================-->