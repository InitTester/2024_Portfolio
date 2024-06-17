<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/scripts.jsp" %>
<%@ page import="com.portfolio.www.forum.board.message.BoardMessageEnum" %>

	<style>
		.drop-area  {
		    border: 4px dashed #ccc;
		    border-radius: 10px;
		    width: 50%;
		    padding: 20px;
		    text-align: center;
		    cursor: pointer;
		    background-color: #fff;
		}
		
		.atta label{
			display: block;
		}
		
		.gallery {
		    margin: auto;
		    text-align: center;
		    width: 100px;
		    height: 100px;
		}
	
	</style>
	
    <!--================================
            START DASHBOARD AREA
    =================================-->
    <section class="support_threads_area section--padding2">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="question-form cardify p-4">
                        <form action="<%=ctx%>/forum/board/edit.do" method="post" enctype="multipart/form-data">
                        <input type="hidden" name ="boardTypeSeq" value='${empty board.boardTypeSeq ? boardTypeSeq : board.boardTypeSeq}'>
                        <input type="hidden" name ="boardSeq" value='${empty board.boardSeq ? boardSeq : board.boardSeq}'>
                            <div class="form-group">
                                <label>제목</label>
                                <input type="text" name="title" value='${board.title}'  placeholder="제목을 입력해주세요" required>
                            </div>
                           
                            <div class="form-group">
                                <label>내용</label>
                                <div id="trumbowyg-demo">${board.content}</div>
                            </div>
                            
                            <div class="form-group">
                                <div class="atta">
                                    <label>첨부파일</label>
										<c:set var="attachCnt" value='${attFiles.size()}'/>
										
										<c:if test='${attFiles.size() !=0}'>
	                                       	<c:forEach items='${attFiles}' var='attFile' varStatus="i">
	                                       		<label class="drop-area" id="drop-area${i.index+1}">
												    <span class="lnr lnr-paperclip"></span> 파일 추가
												    <%-- <input type="file" name="attFile" style="display:none;" accept = "image/gif, image/png, image/jpeg" onchange="handleFiles(this.files, 'gallery${i.index}')" > --%>
												    <input type="file" name="attFile" style="display:none;" onchange="handleFiles(this.files, 'gallery${i.index+1}')" >
												    <div class="gallery" id="gallery${i.index+1}">
									                    <c:if test="${not empty attFile.savePath}">										                      
									                      <img src="${attFile.accessUri}" alt=${attFile.orgFileNm }>
										                </c:if>
												    </div>
												 </label>
	                                       </c:forEach>
                                       </c:if>               
                                       
                                       <!-- 첨부파일 추가기능 -->
                                       <c:forEach var ="i" begin='${attachCnt+1}' end="3" step="1">
											<label class="drop-area" id="drop-area${i}">
											    <span class="lnr lnr-paperclip"></span> 파일 추가
										    	<input type="file" name="attFile" style="display:none;" onchange="handleFiles(this.files, 'gallery${i}')" >
											    <div class="gallery" id="gallery${i}" style="display: none;"></div>
										    </label>
	                                	</c:forEach>          
                                 </div> 
                            </div>
                            
                            <div class="form-group">
                                <button type="submit" class="btn btn--sm btn--round submit">수정</button>         
                            	<a href="<c:url value='/forum/board/listPage.do?boardTypeSeq=${empty board.boardTypeSeq ?boardTypeSeq : board.boardTypeSeq}'/>" class="btn btn--sm btn--round btn_cancel">취소</a>
                            </div>
                        </form>
                    </div><!-- ends: .question-form -->
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
	    $('#trumbowyg-demo').trumbowyg({
	        lang: 'kr'
	    });
	    
		window.onload=function(){
			var code = '${code}';
			var msg = '${msg}';
	
			if(code !== '' && code !== '<%= BoardMessageEnum.SUCCESS.getCode() %>'){
				alert(msg);
			}
		} 

	    // 파일 처리 함수
	    function handleFiles(files, galleryId) {
	        const gallery = document.getElementById(galleryId);
	        gallery.innerHTML = '';
	        gallery.style.display = 'block';
	        /* ([...files]).forEach(uploadFile); */
	        ([...files]).forEach(uploadFile.bind(null, gallery));
	    }

	    function uploadFile(gallery, file) {
	        const reader = new FileReader();
	        reader.readAsDataURL(file);
	        reader.onloadend = function () {
	            const img = document.createElement('img');
	            img.src = reader.result;
	            img.alt = file.name;
	            gallery.appendChild(img);
	        }
	    }


    </script>