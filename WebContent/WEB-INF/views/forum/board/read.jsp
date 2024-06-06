<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String ctx = request.getContextPath();
%>

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
                            <!-- end .title_vote -->
                            
                            
                            <div class="suppot_query_tag">
                                <img class="poster_avatar" src="<%=ctx%>/assest/template/images/support_avat1.png" alt="Support Avatar"> ${boardDetail.regMemberNm}
                                <span><fmt:formatDate value="${boardDetail.formatRegDtm}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                조회 ${boardDetail.hit}
                            </div>
                            <p style="margin-bottom: 0; margin-top: 19px;">${boardDetail.content}</p>
                        </div>
                        <!-- end .forum_issue -->

                        <div class="forum--replays cardify">
                            <div class="area_title">
                                <h4>1 Replies</h4>
                            </div>
                            <!-- end .area_title -->

                            <div class="forum_single_reply">
                                <div class="reply_content">
                                    <div class="name_vote">
                                        <div class="pull-left">
                                            <h4>AazzTech
                                                <span>staff</span>
                                            </h4>
                                            <p>Answered 3 days ago</p>
                                        </div>
                                        <!-- end .pull-left -->

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
                                    <p>Nunc placerat mi id nisi interdum mollis. Praesent pharetra, justo ut sceleris que the
                                        mattis, leo quam aliquet congue placerat mi id nisi interdum mollis. </p>
                                </div>
                                <!-- end .reply_content -->
                            </div>
                            <!-- end .forum_single_reply -->

                            <div class="comment-form-area">
                                <h4>Leave a comment</h4>
                                <!-- comment reply -->
                                <div class="media comment-form support__comment">
                                    <div class="media-left">
                                        <a href="#">
                                            <img class="media-object" src="<%=ctx%>/assest/template/images/m7.png" alt="Commentator Avatar">
                                        </a>
                                    </div>
                                    <div class="media-body">
                                        <form action="#" class="comment-reply-form">
                                            <div id="trumbowyg-demo"></div>
                                            <button class="btn btn--sm btn--round">Post Comment</button>
                                        </form>
                                    </div>
                                </div>
                                <!-- comment reply -->
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
	   
	    /* 새로고침 */ 
       window.addEventListener('popstate', function(event) {
            window.location.reload();
        });	    
	    
       /* vote 함수 */
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
	   	    		
	   	    		console.log('a#vote'+thumb + ', result : ' + result);
	   	    		console.log('값은 ? '+(result === 1));
	   	    		
	   	    		/* 1.추가 2.수정 3.삭제 */
	    			if (result == 1) {   		
	    				console.log('1번에 왔다');
	    				$('a#vote'+thumb).addClass('active');
	    			}else if(result == 2){
	    				console.log('2번에 왔다');
	    				$('a#vote'+!thumb).removeClass('active');
	    				$('a#vote'+thumb).addClass('active'); 
	    			} else if(result ==3){
	    				console.log('3번에 왔다');
		      			$('a#vote'+thumb).removeClass('active');	
	    			}    	  
	   	    		console.log('ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ');  		
    	    	},
    	    	error : function(request, status, error){
    	    		console.log('vote method error :' +error);
    	    	}
    	    	
    	    });
    	}

       
	</script>
	