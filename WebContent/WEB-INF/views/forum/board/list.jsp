<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String ctx = request.getContextPath();
%>
    <section class="section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="modules__content">
                        <div class="withdraw_module withdraw_history">
	                         <div class="withdraw_table_header">
	                             <h3>${boardTypeNm}</h3>
	                         </div>
                             <div class="table-responsive">
                                 <table class="table withdraw__table">
                                     <thead>
                                         <tr>
                                         	<th>번호</th>
                                             <th>제목</th>
                                             <th>등록일</th>
                                             <th>글쓴이</th>
                                             <th>조회</th>
                                         </tr>
                                     </thead>

									<!-- 게시글 리스트 -->
                                     <tbody>
                                     	<c:forEach items="${board}" var ="board" >
                                     		<tr>
                                     			<td>${board.boardSeq}</td>
                                     			<td>
                                     				<c:if test="${board.attachCnt > 0}"> <span class="lnr lnr-paperclip"></span></c:if>
                                     				<a href="<c:url value= '/forum/board/readPage.do?boardTypeSeq=${board.boardTypeSeq}&boardSeq=${board.boardSeq}'/>">${board.title}</a>

                                     				<c:if test="${board.commentCnt > 0}"> <a style="color: orange">${board.commentCnt}</a> </c:if>	
                                     			</td>
                                     			
                                     			<td><fmt:formatDate value="${board.formatRegDtm}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                     			<td>${board.regMemberNm}</td>      
                                     			<td>${board.hit}</td>                                  	
                                     	</c:forEach> 
                                     </tbody>                                        
                                 </table>
                               	 <div style="display: inline-block; margin: 0 5px; float: right; padding-right:10px;">
                               		<a href="<c:url value='/forum/board/newPage.do?boardTypeSeq=${boardTypeSeq}'/>">
                               			<button class="btn btn--round btn--bordered btn-sm btn-secondary">작성</button>
                               		</a>
                           		</div>
                              	<div class="pagination-area" style="padding-top: 45px;">
			                        <nav class="navigation pagination" role="navigation">
			                            <div class="nav-links">
			                            	<!-- prev page -->
			                            	<c:if test="${pageHandler.prev ne 1 }"> <!-- ne : not equal -->
												<a class="prev page-numbers" href="<c:url value='/forum/board/listPage.do?boardTypeSeq=${board.boardTypeSeq}&page=${pageHandler.begin-1}&size=${pageHandler.size}'/>">
				                                    <span class="lnr lnr-arrow-left"></span>
				                                </a>
											</c:if>
		
			                                <!-- 1~10 page -->
			                                <c:forEach var="i" begin="${pageHandler.begin}" end="${pageHandler.end}">
			                                	<a class="page-numbers" href="<c:url value='/forum/board/listPage.do?boardTypeSeq=${board.boardTypeSeq}&page=${i}&size=${pageHandler.size}'/>">${i}</a>
			                                </c:forEach>
			                                
			                                <!-- next page -->
		                                             <c:if test="${pageHandler.next ne pageHandler.totalPageSize}">
												<a class="next page-numbers" href="<c:url value='/forum/board/listPage.do?boardTypeSeq=${board.boardTypeSeq}&page=${pageHandler.end+1}&size=${pageHandler.size}'/>">
				                                    <span class="lnr lnr-arrow-right"></span>
				                                </a>
											</c:if>
			                            </div>
			                        </nav>
			                    </div>
                             </div>
                         </div>
                     </div>
                </div>
                <!-- end .col-md-6 -->
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
<!--     <script>
       window.addEventListener('popstate', function(event) {
            window.location.reload();
        });	    
    </script> -->
