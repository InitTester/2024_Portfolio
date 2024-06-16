<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!--================================
	    START FOOTER AREA
	=================================-->
    <footer class="footer-area">
        <div class="footer-big section--padding">
            <!-- start .container -->
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="info-footer">
                            <div class="info__logo">
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
                            <h4 class="footer-widget-title text--white">페이지</h4>
                            <ul>
                                <li>
                                    <a href="<c:url value='index.do'/>">홈</a>
                                </li>
                                <li>
                                    <a href="#">프로필</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=1'/>">게시판-공지사항</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=2'/>">게시판-자유게시판</a>
                                </li>
                            </ul>
                        </div>
                        <!-- end /.footer-menu -->

                    </div>
                    <!-- end /.col-md-5 -->

<!--                     <div class="col-lg-4 col-md-12">
                        <div class="newsletter">
                            <h4 class="footer-widget-title text--white">Newsletter</h4>
                            <p>Subscribe to get the latest news, update and offer information. Don't worry, we won't send spam!</p>
                            <div class="newsletter__form">
                                <form action="#">
                                    <div class="field-wrapper">
                                        <input class="relative-field rounded" type="text" placeholder="Enter email">
                                        <button class="btn btn--round" type="submit">Submit</button>
                                    </div>
                                </form>
                            </div>

                            start .social
                            <div class="social social--color--filled">
                                <ul>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-facebook"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-twitter"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-google-plus"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-pinterest"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-linkedin"></span>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <span class="fa fa-dribbble"></span>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            end /.social
                        </div>
                        end /.newsletter
                    </div> -->
                    <!-- end /.col-md-4 -->
                </div>
                <!-- end /.row -->
            </div>
            <!-- end /.container -->
        </div>
        <!-- end /.footer-big -->

        <div class="mini-footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="copyright-text">
                            <p>&copy; 2024
                                <a href="#">PortfolioHome</a>. All rights reserved. Created by
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