<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!--================================
	    START FOOTER AREA
	=================================-->
    <footer class="footer-area">
 <%--       <div class="footer-big foot--section--padding">
             <!-- start .container -->
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="info-footer">
                            <div class="info__logo" style="margin-top: 30px;">
                                <img src="<c:url value='/images/logo.png'/> " alt="footer logo" />
                            </div>
                            <p class="info--text">포트폴리오를 위한 개인 홈페이지 입니다.</p>
                            
                        </div>
                        <!-- end /.info-footer -->
                    </div>
                    <!-- end /.col-md-3 -->

                    <div class="col-lg-4 col-md-6">
                        <div class="footer-menu">
							<ul class="info-contact">
                                <li>
                                    <span class="lnr lnr-phone info-icon"></span>
                                    <span class="info">Phone: +82 010-1111-1111</span>
                                </li>
                                <li>
                                    <span class="lnr lnr-envelope info-icon"></span>
                                    <span class="info">initsave@gmail.com</span>
                                </li>
                                <li>
                                    <span class="lnr lnr-map-marker info-icon"></span>
                                    
                                    <span class="info"><a href="https://github.com/InitTester/2024_Portfolio">GitHub 주소</a></span>
                                </li>
                            </ul>                        
                        </div>
                    </div> 
                    
                    <div class="col-lg-5 col-md-6">
                        <div class="footer-menu">
                            <!-- <h4 class="footer-widget-title text--white">페이지</h4> -->
                            
							<ul class="info-contact">
                                <li>
                                    <a href="<c:url value='index.do'/>">홈</a>
                                </li>
                                <li>
                                    <a href="#">프로필</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=1'/>">공지사항</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=2'/>">자유게시판</a>
                                </li>
                            </ul>
                        </div>
                        <!-- end /.footer-menu -->

                    </div>
                    <!-- end /.col-md-5 -->
                </div>
                <!-- end /.row -->
            </div>
            <!-- end /.container -->
        </div> --%>
        <!-- end /.footer-big -->
        <div class="mini-footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="copyright-text">
                            <p>&copy; 2024
                                <a href="<c:url value='/index.do'/>">PortfolioHome</a>. All rights reserved. Created by
                                <a href="#">Initsave</a>
                            </p>
                        </div>

                        <div class="go_top">
                            <span class="lnr lnr-chevron-up"></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!--================================
    END FOOTER AREA
	=================================-->