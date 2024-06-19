<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String ctx = request.getContextPath();
%>
<style>
	.content{
		min-height: 100px; 
	}
	
	.editBtn {
		display: flex;
		justify-content: flex-end;
	}
	
	.vote{
	    float: right;
	    background: #0674ec;
	    padding: 12px 14px;
	    -webkit-border-radius: 4px;
	    border-radius: 4px;
	}
	
	.parentComment{	
		  color: #0674ec;
   		  border: 1px solid #0674ec;
		  font-size: 12px;
		  line-height: 15px;
		  display: inline-block;
		  padding: 0 10px;
		  -webkit-border-radius: 200px;
		          border-radius: 200px;
		  padding: 4px 3px;
	}
	
	.regMember{
		  color: #F53535;  		 
   		  border: 1px solid #F53535;
		  font-weight: 300;
		  font-size: 10px;
		  line-height: 10px;
		  display: inline-block;
		  margin-left: 10px;
		  -webkit-border-radius: 180px;
		          border-radius: 180px;
		  padding: 5px;	
	}
	
	.contentContainer{
		padding-top : 10px;
		display: flex;
		text-align: center;
		  /* height:17px; */
	}
	
	.commentContent{
		margin: auto 0;
		
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
                                <h4>댓글 ${fn:length(comments)} </h4>
                            </div>
                            <!-- end .area_title -->
                            
                            <c:if test="${comments.size() > 0}">
								<!-- 댓글 정보 -->
								<c:forEach items="${comments}" var = "comment" varStatus ="status">
		                            <div class="forum_single_reply" data-commentSeq="${comment.commentSeq}">
		                                <div class="reply_content" style="padding-left: ${18 + 30 * comment.lvl}px">
		                                    <div class="name_vote">
		                                        <div class="pull-left">	
		                                        	<div class="NmcontentContainer">
													    <b style="display: inline-bloack; text-align:center;">${comment.memberNm}</b>  
													    <c:if test="${boardDetail.regMemberSeq eq comment.memberSeq}">
													        <span class="regMember">작성자</span>
													    </c:if>
<!-- 														댓글 별 좋아요/싫어요
				                                        <div class="vote">
				                                            <a href="#" class="active">
				                                                <span class="lnr lnr-thumbs-up"></span>
				                                            </a>
				                                            <a href="#" class="">
				                                                <span class="lnr lnr-thumbs-down"></span>
				                                            </a>
				                                        </div> -->
												    </div>
		                                            &emsp;  
		                                            <div class="contentContainer">
			             <%--                                <c:if test="${comment.pMemberNm ne ''}">
			                                            	<b class="parentComment">@${comment.pMemberNm}</b>
			                                            </c:if>
			                                            &emsp;   --%>
		                         		       			 <div class="commentContent">  ${comment.content}</div>
	                                				</div>
	                                				<%-- ${comment.parentSeq} --%>
	                         		       			&emsp;  
		                                            <div style="display: flex; padding-right:20px">
			                                            <p>${comment.formatRegDtm}</p>
			                                            &emsp;           							                    	    
							                    	    <a style="padding-left: 6px color" href="#" data-commentSeq="${comment.commentSeq}" data-commentLvl="${comment.lvl }" 
							                    	       data-parentSeq="${comment.parentSeq}" onclick="openReplyCommentBox(event, this)">답글</a>	
		                                            	                                            								                    	                                                                               	
		                                                 <c:if test='${sessionScope.memberSeq eq comment.memberSeq }'>
								                            <!-- 수정버튼 -->	                               
							                        		<a style="padding-left: 6px" href="#" onClick="editCommentBox(event,this);">수정</a>                          
								                    	    <!-- 삭제버튼 -->
								                    	    <a style="padding-left: 6px" href="#" 
								                    	       onClick="deleteComment(${comment.commentSeq}, ${boardDetail.boardTypeSeq}, ${boardDetail.boardSeq});">삭제</a>
							                    	    </c:if>	 
						                    	    </div>	                                           
		                                        </div>
		                                        <!-- end .pull-left -->
		                                    </div>
		                                    <!-- end .vote -->
		                                </div>
		                                <!-- end .reply_content -->
		                            </div>
								</c:forEach>
                            </c:if>
                            
                            <!-- 0래밸 -->
                            <div class="comment-form-area " >
                                <h4><span>${cookie.memberNm.value}</span></h4>
                                
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                       <div class="comment-reply-form">
	                                       <textarea placeholder="댓글을 남겨보세요" id ="commentContent" rows="1" class="comment_inbox_text" style="overflow: hidden; overflow-wrap: break-word; height: 17px;" ></textarea>
										    <button class="btn btn--sm btn--round submit" 
										            onClick="newComment(${boardDetail.boardTypeSeq}, ${boardDetail.boardSeq}, this);">댓글 등록</button>
											<a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=${boardDetail.boardTypeSeq}'/>" 
											   class="btn btn--sm btn--round btn-list" style="float:right; margin: 20px 2px 0 2px;">목록</a>                                				     
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
							<!-- 댓글 추가 창 -->
                            <div class="comment-form-area reply" style="display:none">
                                <h4><span>${cookie.memberNm.value}</span></h4>
                                
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                       <div class="comment-reply-form">
	                                       <textarea placeholder="댓글을 남겨보세요" id ="commentContent" rows="1" style="overflow: hidden; overflow-wrap: break-word; height: 17px;" ></textarea>
										    <button class="btn btn--sm btn--round submit" 
										            onClick="newComment(${boardDetail.boardTypeSeq}, ${boardDetail.boardSeq}, this);">댓글 등록</button>
	                                        <button type="button" class="btn btn--sm btn--round btn_cancel"
	                                        				     onclick="cancelComment(this,'reply');">취소</button>
                                       </div>
                                    </div>
                                </div>
                            </div>
                            <!-- comment reply -->
                                
                            <!-- 댓글 수정 창 -->
                            <div class="comment-form-area edit" style="display:none">
                            	<h4><span>${cookie.memberNm.value}</span></h4>
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    
                                   <div class="media-body">
                                       <div class="comment-reply-form">
                                       	<textarea placeholder="댓글을 남겨보세요" rows="1" id="commentContent" style="overflow: hidden; overflow-wrap: break-word; height: 17px;" ></textarea>
                                           <!-- <div id="comment-edit"></div> -->
                                           <div class="editBtn">
	                                           <button type="button" class="btn btn--sm btn--round btn_cancel"
	                                           		onclick="cancelComment(this,'edit');">취소</button>
	                                           <button type="button" class="btn btn--sm btn--round edit"
	                                                   onclick="editComment(${boardDetail.boardTypeSeq}, ${boardDetail.boardSeq}, this);">댓글 수정</button>
                                              </div>	                                           		
                                       </div>
                                   </div>
                                   <!-- comment reply edit -->
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

       /* 게시글 (좋아요/싫어요) */
     	function vote(boardTypeSeq, boardSeq, thisElement) {
    	    let url = `<%=ctx%>/forum/board/vote.do`;
    	    
    	    let voteDto = { boardTypeSeq : boardTypeSeq,
   	    		         boardSeq : boardSeq,
   	    		         isLike : thisElement.getAttribute("data-isLike")};
    	    
    	    $.ajax({
    	    	type : 'POST',
    	    	url : url,
    	    	headers : { 'Content-Type' : 'application/json'},
    	    	dataType : 'text',
    	    	data: JSON.stringify(voteDto),
    	    	success : function(result){    	    		
	   	    		let thumb = thisElement.getAttribute("data-isLike")==='Y' ? 'Up' : 'Down';
	   	    		
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
    	    			
	    				location.href='<%=ctx%>'+page;
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
    	
	    /* 대댓글 추가 창 */
	    function openReplyCommentBox(event, elem){
	    	
    		event.preventDefault();

	    	let ReplyArea = elem.closest('div.forum_single_reply');
	    	let commentForm = document.querySelector('div.comment-form-area.reply');
	    	
	   	    /* 기존에 다른 댓글버튼이 눌려서 열린 대댓글창을 닫기 */
    	    let previousreplyForm = document.querySelector('div.comment-form-area.reply[data-open="true"]');
    	    if (previousreplyForm) {
    	    	
    	    	previousreplyForm.style.display = "none";
    	    	previousreplyForm.removeAttribute('data-open');
    	    }

    	    /* 기존에 다른 댓글의 수정 버튼이 눌려서 열린 수정창을 닫기 */
    	    let previousEditForm = document.querySelector('div.comment-form-area.edit[data-open="true"]');
    	    if (previousEditForm) {
    	    	
    	    	let parentElement = previousEditForm.parentElement;
    	    	
    	    	parentElement.querySelector('.reply_content').style.display = "block";    	    	
    	        previousEditForm.style.display = "none";
    	        previousEditForm.removeAttribute('data-open');
    	    }

    	    /* 추가될 대댓글 위치, 환경값 설정 */
	    	ReplyArea.append(commentForm);
	    	commentForm.style.display = "block";
	    	commentForm.setAttribute('data-open', 'true');
	    	
	    	/* 댓글 추가시 필요 데이터 설정 */
	    	let submitBtn = commentForm.querySelector('button.submit');
	    	submitBtn.setAttribute('data-parentSeq', parseInt(elem.getAttribute('data-commentSeq')));
	    	submitBtn.setAttribute('data-commentLvl', parseInt(elem.getAttribute('data-commentLvl'))+1);
	    }    
	    
    	/* 댓글 수정 창 */
    	function editCommentBox(event, elem){
    		
    		event.preventDefault();

    	    let commentContent = elem.closest('div.forum_single_reply').querySelector('.commentContent').innerText;
    	    document.querySelector('.comment-form-area.edit textarea').value = commentContent;
    	    
    	    let commentArea = elem.closest('div.forum_single_reply');
    	    let editForm = document.querySelector('div.comment-form-area.edit');
    	    commentArea.querySelector('.reply_content').style.display = "none";
    	    
    	    /* 기존에 다른 댓글의 수정 버튼이 눌려서 열린 수정창을 닫기 */
    	    let previousEditForm = document.querySelector('div.comment-form-area.edit[data-open="true"]');
    	    if (previousEditForm) {
    	    	
    	    	let parentElement = previousEditForm.parentElement;
    	    	
    	    	parentElement.querySelector('.reply_content').style.display = "block";    	    	
    	        previousEditForm.style.display = "none";
    	        previousEditForm.removeAttribute('data-open');
    	    }

	   	    /* 기존에 다른 댓글버튼이 눌려서 열린 대댓글창을 닫기 */
    	    let previousreplyForm = document.querySelector('div.comment-form-area.reply[data-open="true"]');
    	    if (previousreplyForm) {
    	    	
    	    	previousreplyForm.style.display = "none";
    	    	previousreplyForm.removeAttribute('data-open');
    	    }

    	    /* 수정 댓글 위치, 환경값 설정 */
    	    commentArea.append(editForm);
    	    editForm.style.display = "block";
    	    editForm.setAttribute('data-open', 'true');

	    	/* 댓글 수정시 필요 데이터 설정 */
    	    let editBtn = editForm.querySelector('button.edit');
    	    editBtn.setAttribute('data-commentSeq', commentArea.getAttribute('data-commentSeq'));
    	}
    	
    	/* 댓글 삭제 버튼 */
    	function cancelComment(elem, type){
    		
    		if(type==='edit'){
        	    let previousEditForm = document.querySelector('div.comment-form-area.edit[data-open="true"]');
        	    if (previousEditForm) {
        	    	
        	    	let parentElement = previousEditForm.parentElement;
        	    	
        	    	parentElement.querySelector('.reply_content').style.display = "block";    	    	
        	        previousEditForm.style.display = "none";
        	        previousEditForm.removeAttribute('data-open');
        	    }
    		} else{
    	   	    /* 기존에 다른 댓글버튼이 눌려서 열린 대댓글창을 닫기 */
        	    let previousreplyForm = document.querySelector('div.comment-form-area.reply[data-open="true"]');
        	    if (previousreplyForm) {
        	    	
        	    	previousreplyForm.style.display = "none";
        	    	previousreplyForm.removeAttribute('data-open');
        	    } 		
    		}
    	}
	
    	/* 댓글 추가 */
    	function newComment(boardTypeSeq, boardSeq, elem){

    		var url = '<%=ctx%>/forum/board/newComment.do';
    		
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
    				content : elem.parentElement.querySelector('#commentContent').value,
    				parentSeq: elem.getAttribute("data-parentSeq") == null ? null :  elem.getAttribute("data-parentSeq"),
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
    	
    	/* 댓글 수정 */
    	function editComment(boardTypeSeq, boardSeq, elem) {

    		var url = '<%=ctx%>/forum/board/editComment.do';

    		$.ajax({
    			type : 'post',
    			url : url,    			
    			headers : {
    				'Content-Type' : 'application/json',
	    			"accept" : "application/json"
    			},
    			dataType : 'JSON',
    			data : JSON.stringify ({
    				commentSeq : elem.getAttribute("data-commentSeq"),
    				boardTypeSeq : boardTypeSeq,
    				boardSeq : boardSeq,
    				content : elem.parentElement.parentElement.querySelector('#commentContent').value,
    				parentSeq: elem.getAttribute("data-parentSeq") == null ? null :  elem.getAttribute("data-parentSeq"),
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
    	
    	/* 댓글 삭제 */
    	function deleteComment(commentSeq, boardTypeSeq, boardSeq, event) {
    	    // 현재 스크롤 위치 저장
    	    let currentScrollPos = window.pageYOffset || document.documentElement.scrollTop;

    		var result = confirm("정말 현재 댓글을 삭제 하시겠습니까?");

    		let url = '<%=ctx%>/forum/board'
    			url += '/' + boardTypeSeq
    			url += '/' + boardSeq
    			url += '/' + commentSeq
		    	url += '/deleteComment.do';
		    	
    		if(result){    			
    			$.ajax({
    	            type: 'delete',
    	            url: url,
    	            headers: {
    	                "Accept": "application/json",  // 요청에 대한 Accept 헤더를 설정
    	                "Content-Type": "application/json"
    	    		},
    	    		// 결과 성공 콜백함수 
    	    		success : function(result) {   
        				if(result){
        	                // 스크롤 위치를 부드럽게 복원
                            scrollToSmoothly(0, currentScrollPos);

                            // 페이지 리로드
                            window.location.reload();
        				} else{
        					alert('실패!');
        				}    	
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
	