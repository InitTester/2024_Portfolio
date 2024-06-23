<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
	
		
	<!--================================
            START SIGNUP AREA
    =================================-->
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                	<H2>인기 다운로드 이미지</H2>
                    <div class="testimonial-slider">
                    
                    <c:forEach items="${attachList}" var = "attach">
                        <div class="testimonial" width ="150px" heigth ="150px">
                            <div class="testimonial__about">
                             	<a href="<c:url value='/forum/board/readPage.do?boardTypeSeq=${attach.boardTypeSeq}&boardSeq=${attach.boardSeq}'/>"><img src="${attach.accessUri}" alt="Testimonial Avatar" ></a>
                             	
                                <%-- <div class="avatar v_middle">
                                    <img src="${attach.accessUri}" alt="Testimonial Avatar">
                                </div> --%>
            <!--                     <div class="name-designation v_middle">
                                    <h4 class="name">Tubeda Pagla</h4>
                                    <span class="desig">Product Designer</span>
                                </div>
                                <span class="lnr lnr-bubble quote-icon"></span> -->
                            </div>
                           <!--  <div class="testimonial__text">
                                <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                    leo quam aliquet diam congue is the laoreet elit metus. Nunc placerat mi is id nisi interdum
                                    is mollis. Praesent the pharetra, justo ut scelerisque.</p>
                            </div> -->
                        </div>
                        <!-- end /.testimonial -->
                    </c:forEach>


                      <!--   <div class="testimonial">
                            <div class="testimonial__about">
                                <div class="avatar v_middle">
                                    <img src="images/test2.jpg" alt="Testimonial Avatar">
                                </div>
                                <div class="name-designation v_middle">
                                    <h4 class="name">Tarashi Hamada</h4>
                                    <span class="desig">Quality Ninja</span>
                                </div>
                                <span class="lnr lnr-bubble quote-icon"></span>
                            </div>
                            <div class="testimonial__text">
                                <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                    leo quam aliquet diam congue is the laoreet elit metus. Nunc placerat mi is id nisi interdum
                                    is mollis. Praesent the pharetra, justo ut scelerisque.</p>
                            </div>
                        </div>
                        end /.testimonial

                        <div class="testimonial">
                            <div class="testimonial__about">
                                <div class="avatar v_middle">
                                    <img src="images/test1.jpg" alt="Testimonial Avatar">
                                </div>
                                <div class="name-designation v_middle">
                                    <h4 class="name">Tubeda Pagla</h4>
                                    <span class="desig">Product Designer</span>
                                </div>
                                <span class="lnr lnr-bubble quote-icon"></span>
                            </div>
                            <div class="testimonial__text">
                                <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                    leo quam aliquet diam congue is the laoreet elit metus. Nunc placerat mi is id nisi interdum
                                    is mollis. Praesent the pharetra, justo ut scelerisque.</p>
                            </div>
                        </div>
                        end /.testimonial

                        <div class="testimonial">
                            <div class="testimonial__about">
                                <div class="avatar v_middle">
                                    <img src="images/test2.jpg" alt="Testimonial Avatar">
                                </div>
                                <div class="name-designation v_middle">
                                    <h4 class="name">Tarashi Hamada</h4>
                                    <span class="desig">Quality Ninja</span>
                                </div>
                                <span class="lnr lnr-bubble quote-icon"></span>
                            </div>
                            <div class="testimonial__text">
                                <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                    leo quam aliquet diam congue is the laoreet elit metus. Nunc placerat mi is id nisi interdum
                                    is mollis. Praesent the pharetra, justo ut scelerisque.</p>
                            </div>
                        </div>
                        end /.testimonial -->
                    </div>
                    <!-- end /.testimonial_slider -->
                </div>
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    <!--================================
            END SIGNUP AREA
    =================================-->
    
	<!--================================
	    START FEATURE AREA
	=================================-->
    <section class="features section--padding">
        <!-- start container -->
        <div class="container">
            <!-- start row -->
            <div class="row">
                <!-- start search-area -->
                <div class="col-lg-4 col-md-6">
                    <div class="feature">
                        <div class="feature__img">
                            <img src="<c:url value='/assest/template/images/feature1.png'/>" alt="feature" />
                        </div>
                        <div class="feature__title">
                            <h3>Best UX Research</h3>
                        </div>
                        <div class="feature__desc">
                            <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                leo quam aliquet diam congue is laoreet elit metus.</p>
                        </div>
                    </div>
                    <!-- end /.feature -->
                </div>
                <!-- end /.col-lg-4 col-md-6 -->

                <!-- start search-area -->
                <div class="col-lg-4 col-md-6">
                    <div class="feature">
                        <div class="feature__img">
                            <img src="<c:url value='/assest/template/images/feature2.png'/>" alt="feature" />
                        </div>
                        <div class="feature__title">
                            <h3>Fully Responsive</h3>
                        </div>
                        <div class="feature__desc">
                            <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                leo quam aliquet diam congue is laoreet elit metus.</p>
                        </div>
                    </div>
                    <!-- end /.feature -->
                </div>
                <!-- end /.col-lg-4 col-md-6 -->

                <!-- start search-area -->
                <div class="col-lg-4 col-md-6">
                    <div class="feature">
                        <div class="feature__img">                        
                            <img src="<c:url value='/assest/template/images/feature3.png'/>" alt="feature" />
                        </div>
                        <div class="feature__title">
                            <h3>Buy & Sell Easily</h3>
                        </div>
                        <div class="feature__desc">
                            <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut scelerisque the mattis,
                                leo quam aliquet diam congue is laoreet elit metus.</p>
                        </div>
                    </div>
                    <!-- end /.feature -->
                </div>
                <!-- end /.col-lg-4 col-md-6 -->
            </div>
            <!-- end /.row -->
        </div>
        <!-- end /.container -->
    </section>
    <!--================================
	    END FEATURE AREA
	=================================-->