<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
	
    <section class="features section--padding">
	    
    	<!-- start container board view top3  -->
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
				                	<h3 class="feature__title card-title">작성자 : ${board.regMemberNm}</h3>			                
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
        <!-- end /.container view top3 -->
            
    	<!-- start container img top3  -->    	
		<div class="container mt-5">
		    <div class="row">
		        <div class="col-lg-12 text-center mb-4">
		            <p class="h2 font-weight-bold">다운로드 이미지 조회수 TOP 3</p> 
		        </div>
	            <c:forEach items="${attachList}" var="attach" varStatus="i">
<!-- 		                <div class="col-lg-3 col-md-4 col-sm-6 mb-4"> -->
	                <div class="col-lg-4 col-md-6 mb-4">
	                    <div class="card h-100">
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
        <!-- end /.container img top3-->
        
    	<!-- start container trouble shooting  -->
		<div class="container mt-5">
		    <div class="row">		
			    <div class="col-lg-12 text-center mb-4">
		            <p class="h2 font-weight-bold">Trouble Shooting's TOP 3</p> 
		        </div>	
		        	                 	
		        <div class="col-lg-4 col-md-6 mb-4">
	        		<a href="https://initsave.tistory.com/454" target="_blank">
	                   	<div class="trouble_info">	
			                <div class="trouble_info_wrapper">
			                	<h3 class="trouble_info_title">[Mybatis] SqlSession was not registered for synchronization because synchronization is not active</h3>			                
					            <div class="trouble_info_img">		
					   				<img class="trouble_img"  alt=""
					   				     src="<c:url value='/images/troubleshooting/ts1.png'/>">
					            </div>		
			            	</div>
		            	</div>
	       			</a>         
		        </div>     

		        <div class="col-lg-4 col-md-6 mb-4">
	        		<a href="https://initsave.tistory.com/425" target="_blank">
	                   	<div class="trouble_info">	
			                <div class="trouble_info_wrapper">
			                	<h3 class="trouble_info_title">[MyBatis] selectKey return 값이 1만 나올때 </h3>			                
					            <div class="trouble_info_img">		
					   				<img class="trouble_img"  alt=""
					   				     src="<c:url value='/images/troubleshooting/ts2.png'/>">
					            </div>		
			            	</div>
		            	</div>
	       			</a>         
		        </div>   
		        
		        <div class="col-lg-4 col-md-6 mb-4">
	        		<a href="https://initsave.tistory.com/452" target="_blank">
	                   	<div class="trouble_info">	
			                <div class="trouble_info_wrapper">
			                	<h3 class="trouble_info_title">[Nginx] could not build server_names_hash, you should increase server_names_hash_bucket_size: 64 (2)</h3>			                
					            <div class="trouble_info_img">		
					   				<img class="trouble_img"  alt=""
					   				     src="<c:url value='/images/troubleshooting/ts3.png'/>">
					            </div>		
			            	</div>
		            	</div>
	       			</a>         
		        </div>   		        		        
		        <%-- 
		        
		        <div class="trouble_list">
			        <a href="https://initsave.tistory.com/425" target="_blank">
			            <div class="trouble">
			                <div class="trouble_info">
			                	<h3 class="trouble_info_title">[MyBatis] selectKey return 값이 1만 나올때 </h3>			                
					            <div class="trouble_info_img">		
					   				<img class="trouble_img"  alt=""
					   				     src="<c:url value='/images/troubleshooting/ts2.png'/>">
					            </div>		
			            	</div>
			            </div>
	       			</a>         
		        </div>  
		        <div class="trouble_list">
			        <a href="https://initsave.tistory.com/452" target="_blank">
			            <div class="trouble">
			                <div class="trouble_info">
			                	<h3 class="trouble_info_title">[Nginx] could not build server_names_hash, you should increase server_names_hash_bucket_size: 64 (2)</h3>			                
					            <div class="trouble_info_img">		
					   				<img class="trouble_img"  alt=""
					   				     src="<c:url value='/images/troubleshooting/ts3.png'/>">
					            </div>		
			            	</div>
			            </div>
	       			</a>         
		        </div>   --%>
		   </div>
		</div>

        <!-- end /.container img top3-->

    </section>
    <!--================================
	    END FEATURE AREA
	=================================-->