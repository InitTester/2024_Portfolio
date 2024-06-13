<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String ctx = request.getContextPath();
%>
<<style>
	.content{
		min-height: 100px; 
	}
</style>

    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="support_threads_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="forum_detail_area ">
                        <div class="cardify forum--issue">
                        
                            <div class="title_vote clearfix">
                                <h3>${boardDetail.title}</h3>

								<!-- 좋아요/싫어요 -->
                                <div class="vote">
                                    <a href="#" id="voteUp" data-isLike="Y" class="${isLike eq 'Y' ? 'active' : '' }" 
                                       onclick="vote(${boardDetail.boardTypeSeq},${boardDetail.boardSeq},this);" >
                                        <span class="lnr lnr-thumbs-up"></span>
                                    </a>
                                    <a href="#" id="voteDown" data-isLike="N" class="${isLike eq 'N' ? 'active' : '' }" 
                                       onclick="vote(${boardDetail.boardTypeSeq},${boardDetail.boardSeq},this);" >
                                        <span class="lnr lnr-thumbs-down"></span>
                                    </a>
                                </div>                          
                            </div>
                            
                           	<!-- 게시글 정보 -->
                            <div class="suppot_query_tag">
                                <img class="poster_avatar" src="<%=ctx%>/assest/template/images/support_avat1.png" alt="Support Avatar"> ${boardDetail.regMemberNm}
                                &emsp;
                                <span>
                                	<fmt:formatDate value="${boardDetail.formatRegDtm}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </span>
                                &emsp;
                                조회 ${boardDetail.hit}
                                &emsp;&emsp;  

                                <!-- 수정/삭제 버튼, 로그인 회원+등록자가 동일하면 -->
                                <c:if test='${sessionScope.memberSeq eq boardDetail.regMemberSeq }'>
		                            <!-- 수정버튼 -->	                               
		                            <a href="<c:url value='/forum/board/editPage.do?boardTypeSeq=${boardDetail.boardTypeSeq}&boardSeq=${boardDetail.boardSeq}'/>" >수정 </a>	                            
		                    	    <!-- 삭제버튼 -->
		                    	    <a href="#" onClick="deleteClick(${boardDetail.boardSeq}, ${boardDetail.boardTypeSeq});">삭제</a>
	                    	    </c:if>
	                    	    
                            </div>
                            <!-- 게시글 내용 -->
                            <div class="content">
                            	<p style="margin-bottom: 0; margin-top: 19px;">${boardDetail.content}</p>
                            </div>
                            <!-- 첨부파일 다운로드 -->
                            <div class = "downLoad_area"> 	
	                            <!-- 전체 다운로드 -->
	                            <c:if test="${attFiles.size() > 1}">
	                            	<a href="<%=ctx%>/forum/board/downloadAll.do?boardTypeSeq=${boardDetail.boardTypeSeq}&boardSeq=${boardDetail.boardSeq}">파일 전체 다운로드</a>
	                            	<br>
	                            </c:if>
	                                                        	
	  							<c:if test="${attFiles.size() != 0}">
		                            <c:forEach items="${attFiles}" var="attFile">
		                            	<a href="<%=ctx%>/forum/board/download.do?attachSeq=${attFile.attachSeq}"> ${attFile.orgFileNm} (size : ${attFile.fileSize})</a>
		                            	<br>
		                            </c:forEach>
	                            </c:if>    
                            </div>	
                            
                        </div>
                        <!-- end .forum_issue -->

						<!-- 댓글 -->
                        <div class="forum--replays cardify">
                            <div class="area_title">
                                <h4>${fn:length(comments)} Replies</h4>
                            </div>
                            <!-- end .area_title -->
                            
                            <c:if test="${comments.size() > 0}">
								<!-- 댓글 정보 -->
								<c:forEach items="${comments}" var = "comment" varStatus ="status">
		                            <div class="forum_single_reply" data-commentSeq="${comment.commentSeq}">
		                                <div class="reply_content" style="padding-left: ${18 + 30 * comment.lvl}px">
		                                    <div class="name_vote">
		                                        <div class="pull-left">
		                                            <h4><span>${comment.memberNm}</span></h4>
		                                            
		                                            <div style="display: flex; padding-right:20px">
		                                            
		                                            	<p><fmt:formatDate value="${comment.formatRegDtm}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
		                                            	
		                                                 <c:if test='${sessionScope.memberSeq eq boardDetail.regMemberSeq }'>
								                            <!-- 수정버튼 -->	                               
							                        		<a style="padding-left: 6px" href="#" onClick="editCommentBox(this);">수정</a>                          
								                    	    <!-- 삭제버튼 -->
								                    	    <a style="padding-left: 6px" href="#" onClick="deleteComment(${comment.commentSeq}, ${board.boardTypeSeq}, ${board.boardSeq});">삭제</a>
							                    	    </c:if>
							                    	    
							                    	    <a style="padding-left: 6px" href="#" data-sommemtSeq="${comment.commentSeq}" data-commentLvl="${comment.lvl }" onclick="openReplyCommentWindow(this)">답글</a>
							                    	    
		                                            </div>	                                            
		                                        </div>
		                                        <!-- end .pull-left -->
		
												<!-- 댓글 별 좋아요/싫어요 -->
		                                        <div class="vote">
		                                            <a href="#" class="active">
		                                                <span class="lnr lnr-thumbs-up"></span>
		                                            </a>
		                                            <a href="#" class="">
		                                                <span class="lnr lnr-thumbs-down"></span>
		                                            </a>
		                                        </div>
		                                    </div>
		                                    <!-- end .vote -->
	                         		       <div class="commentContent"> ${comment.content}</div>
		                                </div>
		                                <!-- end .reply_content -->
		                            </div>
								</c:forEach>
                            </c:if>
                            
							<!-- 댓글 추가 창 -->
                            <div class="comment-form-area">
                                <h4>댓글을 남겨보세요</h4>
                                
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                       <div id="trumbowyg-demo"></div>
									    <button class="btn btn--sm btn--round submit" 
									            onClick="addComment(${boardDetail.boardTypeSeq}, ${boardDetail.boardSeq}, this);">댓글 등록</button>
                                        <button type="button" class="btn btn--sm btn--round"
                                                 onclick="location.href='<%=ctx %>/forum/board/readPage.do?boardTypeSeq=${boardDetail.boardTypeSeq}&boardSeq=${boardDetail.boardSeq}'">취소</button>
                                    </div>
                                </div>
                                <!-- comment reply -->
                                
                               	<!-- 댓글 수정 창 -->
	                            <div class="comment-form-area edit" style="display:none">
	                                <h4>댓글을 수정하세요</h4>
	                                <!-- comment reply -->
	                                <div class="media comment-form support__comment">
	                                    <div class="media-left">
	                                        <a href="#">
	                                            <img class="media-object" src="<%=ctx%>/resources/template/images/m7.png" alt="Commentator Avatar">
	                                        </a>
	                                    </div>
	                                    
	                                   <div class="media-body">
	                                       <div class="comment-reply-form">
	                                           <div id="comment-edit"></div>
	                                           <button type="button" class="btn btn--sm btn--round edit"
	                                                   onclick="editComment(${boardDetail.boardSeq}, ${boardDetail.boardSeq}, this);">댓글 수정</button>
	                                           <button type="button" class="btn btn--sm btn--round cancel"
	                                           		onclick="location.href='<%=ctx %>/forum/board/readPage.do?boardTypeSeq=${boardDetail.boardTypeSeq}&boardSeq=${boardDetail.boardSeq}'">취소</button>
	                                       </div>
	                                   </div>
	                                </div>
	                                <!-- comment reply -->
	                            </div>  
                            </div>
                        </div>
                        <!-- end .forum_replays -->
                    </div>
                    <!-- end .forum_detail_area -->
                </div>
                <!-- end .col-md-12 -->            
            </div>
            <!-- end .row -->
        </div>
        <!-- end .container -->
    </section>
    <!--================================
            END DASHBOARD AREA
    =================================-->
    
    <script type="text/javascript">
    	/* 에디터 사용 */
	    $('#trumbowyg-demo').trumbowyg({
	        lang: 'kr'
	    });
	    
     	$('#comment-edit').trumbowyg({
     		lang: 'kr'
     	})	    
	   
	    /* 새로고침 */ 
       window.addEventListener('popstate', function(event) {
            window.location.reload();
        });	    
	    
       /* 게시글 (좋아요/싫어요) */
     	function vote(boardTypeSeq, boardSeq, thisElement) {
    	    let url = `<%=ctx%>/forum/board/vote.do`;
    	    
    	    let voteDto = { boardTypeSeq : boardTypeSeq,
   	    		         boardSeq : boardSeq,
   	    		         isLike : thisElement.getAttribute("data-isLike")};
    	    
    	    console.log(voteDto);
    	    
    	    $.ajax({
    	    	type : 'POST',
    	    	url : url,
    	    	headers : { 'Content-Type' : 'application/json'},
    	    	dataType : 'text',
    	    	data: JSON.stringify(voteDto),
    	    	success : function(result){    	    		
	   	    		let thumb = thisElement.getAttribute("data-isLike")==='Y' ? 'Up' : 'Down';
	   	    		
	   	    		/* console.log('a#vote'+thumb + ', result : ' + result); */
	   	    		
	   	    		/* 1.추가 2.수정 3.삭제 */
	    			if (result == 1) {   		
	    				$('a#vote'+thumb).addClass('active');
	    			}else if(result == 2){
	    				$('a#vote'+!thumb).removeClass('active');
	    				$('a#vote'+thumb).addClass('active'); 
	    			} else if(result ==3){
		      			$('a#vote'+thumb).removeClass('active');	
	    			}    	  	
    	    	},
    	    	error : function(request, status, error){
    	    		console.log('vote method error :' +error);
    	    	}
    	    });
    	}

    	/* 게시글 삭제 */
    	function deleteClick(boardTypeSeq, boardSeq) {
    		var result = confirm("정말 현재 게시글을 삭제 하시겠습니까?");

    		let url = '<%=ctx%>/forum/board'
	    		url += '/'+boardTypeSeq
		    	url += '/'+boardSeq
		    	url += '/delete.do';
		    	
    		if(result){    			
    			$.ajax({
    	            type: 'DELETE',
    	            url: url,
    	            headers: {
    	                "Accept": "application/json",  // 요청에 대한 Accept 헤더를 설정
    	                "Content-Type": "application/json"
    	    		},
    	    		// 결과 성공 콜백함수 
    	    		success : function(response) {   
    	    			var page = response.page;
    	    			
    	    			/* alert(page); */
	    				location.href='<%=ctx%>'+page;
        				/* alert(response.msg);  */        	
    	    		},
    	    		// 결과 에러 콜백함수
    	    		error : function(request, status, error) {
    	    			console.log(error)
    	    		}
    	    	});
    		}else{
    			/* alert('cancel'); */
    		}
    	}
    	
    	/* 게시글 댓글 */
    	function addComment(boardTypeSeq, boardSeq, elem){
    		
    		var url = '<%=ctx%>/forum/board/comment.do';
    		
    		$.ajax({
    			type : 'post',
    			url : url,    			
    			headers : {
    				'Content-Type' : 'application/json',
	    			"accept" : "application/json"
    			},
    			dataType : 'JSON',
    			data : JSON.stringify ({
    				boardTypeSeq : boardTypeSeq,
    				boardSeq : boardSeq,
	    			content: $('#trumbowyg-demo').trumbowyg('html'),
	    			parentCommentSeq: elem.getAttribute("data-parentCommentSeq"),
	    			lvl: elem.getAttribute("data-commentLvl") == null ? 0 : elem.getAttribute("data-commentLvl")
    			}),
    			success : function(result){
    				if(result){
    					window.location.reload();
    				} else{
    					alert('실패!');
    				}
    			},
    			error : function(request, status, error){
    				console.log(error);
    			}
    		});
    	}
    
    	/* 댓글 수정창 */
    	function editCommentBox(elem){
	    	let commentContent = document.querySelector('.commentContent').innerText;
	    	$('#comment-edit').trumbowyg('html', commentContent);
	    	
	    	let commentArea = elem.closest('div.forum_single_reply');
	    	let editForm = document.querySelector('div.comment-form-area.edit');
	    	commentArea.append(editForm);
	    	editForm.style.display = "block";
	    	
	    	//2-2. 이전의 다른 댓글의 수정버튼을 누른 상태라면, 그 댓글의 수정창은 닫혀야 한다.
	    /* 	let hiddenElem = document.querySelector('div.contentBtn.hiddenComment');
	    	console.log(hiddenElem);
	    	
	    	if(hiddenElem != null) {
	    		hiddenElem.classList.remove("hiddenComment");
	    	}
	    	contentBox.classList.add("hiddenComment"); */
	    	
	    	let editBtn = editForm.querySelector('button.edit');
	    	editBtn.setAttribute('data-commentSeq', commentArea.getAttribute('data-commentSeq'));    		
    	}
    	
	    /* 대댓글 등록 */
	    function openReplyCommentWindow(elem){
	    	
	    	$('#trumbowyg-demo').trumbowyg('html', '');
	    	
	    	let ReplyArea = elem.closest('div.forum_single_reply');
	    	let commentForm = document.querySelector('div.comment-form-area.reply');
	    	
	    	ReplyArea.append(commentForm);
	    	
	    	let submitBtn = commentForm.querySelector('button.submit');
	    	
	    	console.dir(elem);
	    	
	    	console.log("data :" + elem.getAttribute('data-commentSeq'));
	    	console.log("commentLvl :" + elem.getAttribute('data-commentLvl'));
	    	
	    	submitBtn.setAttribute('data-parentCommentSeq', elem.getAttribute('data-commentSeq'));
	    	submitBtn.setAttribute('data-commentLvl', parseInt(elem.getAttribute('data-commentLvl'))+1);	    	
	    }    	
    	
    	/* 댓글 수정 */
    	function editComment(boardTypeSeq, boardSeq, elem) {

    		let url = '<%=ctx%>/forum/board/modifyComment.do?';
		    	url += 'commentSeq='+ elem.getAttribute("data-commentSeq")
		    	url += '&boardSeq='+boardSeq
		    	url += '&boardTypeSeq='+boardTypeSeq
		    	url += '&content=' + $('#comment-edit').trumbowyg('html');
		    	
   			$.ajax({
   	            type: 'post',
   	            url: url,
   	            headers: {
   	                "Accept": "application/json",  // 요청에 대한 Accept 헤더를 설정
   	                "Content-Type": "application/json"
   	    		},
   	    		// 결과 성공 콜백함수 
   	    		success : function(response) {   
   	    			var page = response.page;
   	    			
   	    			/* alert(page); */
    				location.href='<%=ctx%>'+page;
       				/* alert(response.msg);  */        	
   	    		},
   	    		// 결과 에러 콜백함수
   	    		error : function(request, status, error) {
   	    			console.log(error)
   	    		}
   	    	});
    	}        	
    	
    	/* 댓글 삭제 */
    	function deleteComment(commentSeq, boardTypeSeq, boardSeq) {
    		var result = confirm("정말 현재 댓글을 삭제 하시겠습니까?");

    		let url = '<%=ctx%>/forum/board/deleteComment.do?';
		    	url += 'commentSeq='+commentSeq
		    	url += '&boardSeq='+boardSeq
		    	url += '&boardTypeSeq='+boardTypeSeq;
		    	
    		if(result){    			
    			$.ajax({
    	            type: 'delete',
    	            url: url,
    	            headers: {
    	                "Accept": "application/json",  // 요청에 대한 Accept 헤더를 설정
    	                "Content-Type": "application/json"
    	    		},
    	    		// 결과 성공 콜백함수 
    	    		success : function(response) {   
    	    			var page = response.page;
    	    			
    	    			/* alert(page); */
	    				location.href='<%=ctx%>'+page;
        				/* alert(response.msg);  */        	
    	    		},
    	    		// 결과 에러 콜백함수
    	    		error : function(request, status, error) {
    	    			console.log(error)
    	    		}
    	    	});
    		}else{
    			/* alert('cancel'); */
    		}
    	}    	    	
    	
	</script>
	