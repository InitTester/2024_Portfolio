<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/scripts.jsp" %>
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
		
		.imgView {
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
                        <input type="hidden" name ="boardTypeSeq" value='${empty board.boardTypeSeq ?boardTypeSeq : board.boardTypeSeq}'>
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
	                                       		<div class="drop-area" id="drop-area${i.index+1}">
	                                       			<label>
												    <span class="lnr lnr-paperclip"></span> 파일 추가
													    <input type="file" name="attFile" style="display:none;" accept = "image/gif, image/png, image/jpeg" onchange="handleFiles(this.files, 'gallery${i.index}')" >
													    <div class="gallery" id="gallery${i.index+1}">
										                    <c:if test="${not empty attFile.savePath}">
										                      <!-- <img src="/images2/1dbb8157cf544ba09327ff5de5c9521a.jpg" alt="Image" style="max-width: 300px; max-height: 300px;"/>
										                      <img src="/images2/1dbb8157cf544ba09327ff5de5c9521a" alt="Image2" style="max-width: 300px; max-height: 300px;"/>
										                      <img src="/images/1dbb8157cf544ba09327ff5de5c9521a" alt="Image3" style="max-width: 300px; max-height: 300px;"/> -->
										                      <img src='/WebContent/images/1dbb8157cf544ba09327ff5de5c9521a.jpg'/>
										                      <img src='/pf/images/1dbb81zm57cf544ba09327ff5de5c9521a.jpg'/>
										                      <img src="file:///C:/Users/USER/pf/file/1dbb8157cf544ba09327ff5de5c9521a.jpg" alt="이미지">
										                      <img src="/image2/1dbb8157cf544ba09327ff5de5c9521a.jpg" alt="이미지">
										                      <img src="http://ec2-3-35-171-121.ap-northeast-2.compute.amazonaws.com/img/shutterstock_2205178589-1-1.png">

<%-- 											                    <img class='imgView' src="/pf/file/${attFile.savePath}" alt="Image ${i.index+1}" /> --%>
											                    <%-- <img class='imgView' src="file:///C:/Users/USER/pf/file/1dbb8157cf544ba09327ff5de5c9521a.jpg" alt="Image ${i.index+1}" /> --%>
											                    
											                </c:if>
													    </div>
													</label>
											    </div>
                                        	</c:forEach>
                                        </c:if>               
                                        
                                        <!-- <span id="profileImg" style="background: url('file:///C:/Users/USER/pf/file/1dbb8157cf544ba09327ff5de5c9521a.jpg') 50% 37% / cover no-repeat"></span> -->         
                      <%--                   <img src="<%=ctx%>/file/1dbb8157cf544ba09327ff5de5c9521a.jpg" alt="Image" /> --%>
                                        
                                        
                                        
<!-- 										<label class="drop-area" id="drop-area1">
										    <span class="lnr lnr-paperclip"></span> 파일 추가
										    <input type="file" name="attFile" style="display:none;" onchange="handleFiles(this.files, 'gallery1')" >
										    multiple accept="*/*"
										    <div class="gallery" id="gallery1"></div>
										</label>
										
										<label class="drop-area" id="drop-area2">
										    <span class="lnr lnr-paperclip"></span> 파일 추가
										    <input type="file" name="attFile" style="display:none;" onchange="handleFiles(this.files, 'gallery2')" >
										    <div class="gallery" id="gallery2"></div>
										</label>
										
										<label class="drop-area" id="drop-area3">
										    <span class="lnr lnr-paperclip"></span> 파일 추가
										    <input type="file" name="attFile" style="display:none;" onchange="handleFiles(this.files, 'gallery3')" >
										    <div class="gallery" id="gallery3"></div>
										</label> -->
                                                          
                                 </div> 
                            </div>
                            
                            <div class="form-group">
                                <button type="submit" class="btn btn--md btn-primary">등록</button>
                            	<a href="<c:url value='/forum/board/listPage.do'/>" class="btn btn--md btn-light">취소</a>
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
    		
   			if(msg != null & msg != "") {
   				alert(msg);
   			}
    	}

	    // 파일 처리 함수
	    function handleFiles(files, galleryId) {
	        const gallery = document.getElementById(galleryId);
	        
	        gallery.innerHTML = '';
	        gallery.style.display = 'block';
	        
	        ([...files]).forEach(uploadFile.bind(null, gallery));
	    }

	    function uploadFile(gallery, file) {
	        const reader = new FileReader();
	        
	        reader.readAsDataURL(file);
	        reader.onloadend = function () {
	        	$('.imgView').attr('src', reader.result);
	        	$('.imgView').attr('alt', file.name);
	        }
	    }

    </script>