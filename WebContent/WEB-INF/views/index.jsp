<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
	
	<!--================================
	    START FEATURE AREA
	=================================-->
    <section class="features section--padding">
	    
        <!-- start container -->
		<div class="container mt-5">
		    <div class="row">		
			    <div class="col-lg-12 text-center mb-4">
		            <p class="h2 font-weight-bold">게시글 조회수 TOP 3</p> 
		        </div>		            
			     <c:forEach items="${boardList}" var="board" varStatus="i">		     	
			        <div class="col-lg-4 col-md-6 mb-4">
				        <a href="<c:url value='/forum/board/readPage.do?boardTypeSeq=${board.boardTypeSeq}&boardSeq=${board.boardSeq}'/>">
				            <div class="feature card h-100">
				                <div class="feature__img card-img-top">			                
						            <div class="feature card h-100">
						                <div class="card-body">
						                    <h3 class="feature__title card-title">${board.title}</h3>
						                    <p class="feature__desc card-text">${board.content}</p>
						                </div>
						            </div>		
				            	</div>
				            </div>
		       			</a>         
			        </div>     
			     </c:forEach>
		   </div>
		</div>
        <!-- end /.container -->
            
    	<!--================================
            START testimonial AREA
    	=================================-->
    	
		<div class="container mt-5">
		    <div class="row">
		        <div class="col-lg-12 text-center mb-4">
		            <p class="h2 font-weight-bold">다운로드 이미지 조회수 TOP 10</p> 
		        </div>
		        <div class="testimonial-slider_">
		            <c:forEach items="${attachList}" var="attach" varStatus="i">
		                <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
		                    <div class="testimonial_ card h-100">
		                        <div class="card-body text-center">
		                            <p class="testimonial__text display-4 text-primary font-weight-bold">${i.index+1}위</p>
		                            <div class="testimonial__about">
		                                <a href="<c:url value='/forum/board/readPage.do?boardTypeSeq=${attach.boardTypeSeq}&boardSeq=${attach.boardSeq}'/>">
		                                    <img src="${attach.accessUri}" alt="Testimonial Avatar" class="img-fluid rounded shadow">
		                                </a>
		                            </div>
		                        </div>
		                    </div>
		                </div>
		            </c:forEach>
		        </div>
		    </div>
		</div>
        <!-- end .container -->
        
	    <!--================================
	            END testimonial AREA
	    =================================-->

    </section>
    <!--================================
	    END FEATURE AREA
	=================================-->