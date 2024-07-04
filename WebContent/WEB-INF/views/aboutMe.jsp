<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!--================================
    START FEATURE AREA
=================================-->
   <section class="features section--padding">
   
   
   	<article class="artAboutMe" id="about-me">
 		<div class="aboutMe_content">
   			<div class="aboutMeTitle">
				<div class="aboutMeTitle_text">ABOUT ME</div>
				<div class="aboutMeTitle_link-wrapper">
				<img class="aboutMeTitle_link" alt=""
					 <%-- src="<c:url value='/images/black-link.png'/>" > --%>
					 src ="https://img.icons8.com/?size=48&id=v1iBAtFtZmRQ&format=png"/>
				</div>				
   			</div>
   			
	   		<div class="aboutMe_info_Intro">
	   			<p class="aboutMe_info_Intro_Title">안녕하세요! 다양한 경험을 쌓으며 성장하고 있는 신입 BackEnd 웹 개발자 전임경입니다.</p>
				<p>저는 6개월 동안 웹 개발 교육을 받았으며, 팀 프로젝트에서 ERD 설계부터 Spring 프레임워크를 활용한 백엔드 개발까지 경험했습니다. 
				   프론트엔드 개발에는 Tails를 사용하여 디자인 영역을 구축했습니다. 이전에는 C#, VB.NET을 이용한 프로그램 개발 경력도 있습니다. 
				   데이터베이스 설계와 클라우드 서비스 활용에도 관심이 많으며, 팀 협력을 통해 성장하고 새로운 기술을 배우고자 합니다.</p>
	   		</div>
	   		
   			<div class="aboutMe_infos">   			
	   			<div class="aboutMe_info_wrapper">
		   			<div class="aboutMe_info">
			   			<div class="aboutMe_icon_img_wrapper">
			   				<img class="aboutMe_icon-img"  alt=""
			   				     <%-- src="<c:url value='/images/about-me/person-fill.png'/>"> --%>
			   				     src="https://img.icons8.com/?size=48&id=YI3iUUls6qvv&format=png">
			   			</div>
			   			&emsp;
			   			<div class="aboutMe_field">
				   			<div class="aboutMe_field_label"> 전임경</div>
			   			</div>
		   			</div>
	   			</div>
	   			
	   			<div class="aboutMe_info_wrapper">
		   			<div class="aboutMe_info">
		   				<div class="aboutMe_icon_img_wrapper">
			   				<img class="aboutMe_icon-img" alt=""
			   				     <%-- src="<c:url value='/images/about-me/calendar-fill.png'/>"> --%>
			   				     src="https://img.icons8.com/?size=48&id=O4nOoRjqFf8k&format=png">
			   			</div>
			   			&emsp;
			   			<div class="aboutMe_field">
				   			<div class="aboutMe_field_label"> 1990. 02. 09</div>
			   			</div>
		   			</div>
	   			</div>
	   			
   				<div class="aboutMe_info_wrapper">
		   			<div class="aboutMe_info">
			   			<div class="aboutMe_icon_img_wrapper">
			   				<img class="aboutMe_icon-img" alt=""
			   				     <%-- src="<c:url value='/images/about-me/telephone-fill.png'/>"> --%>
			   				     src="https://img.icons8.com/?size=64&id=47813&format=png">
			   			</div>
			   			&emsp;
			   			<div class="aboutMe_field">
					   			<div class="aboutMe_field_label">
					   				<a class="aboutMe_telephone" href="tel:010-3161-3416"> 010-3161-3416</a>
					   			</div>
				   			</div>
			   			</div>
		   			</div>   
	   			
		   		<div class="aboutMe_info_wrapper">
		   			<div class="aboutMe_info">
			   			<div class="aboutMe_icon_img_wrapper">
			   				<img class="aboutMe_icon-img" alt=""
			   				     <%-- src="<c:url value='/images/about-me/envelope-fill.png'/>"> --%>
			   				     src="https://img.icons8.com/?size=80&id=68415&format=png">
			   			</div>
			   			&emsp;
			   			<div class="aboutMe_field">
				   			<div class="aboutMe_field_label">
				   				<a class="aboutMe_email" href="mailto:initsave@gmail.com"> initsave@gmail.com</a>
				   			</div>
			   			</div>
		   			</div>
	   			</div> 
	   		</div>			
   		</div>	   		
	</article>
	   	
	<article class="artSkills" id="skills">
		<div class="skills_content">
		
   			<div class="aboutMeTitle">
				<div class="aboutMeTitle_text">SKILLS</div>
				<div class="aboutMeTitle_link-wrapper">
				<img class="aboutMeTitle_link" alt=""
					 <%-- src="<c:url value='/images/black-link.png'/>" > --%>
					 src="https://img.icons8.com/?size=48&id=Z9LL7UhM6A2a&format=png">
				</div>				
   			</div>
			
			<div class="skills_tech-stacks-container">
			
				<div class="skills_tech-stacks">
	   				<div class="skills_title">Languages</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/Languages.png'/>" alt="">
	   			</div>
	   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Web Server</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/WS.png'/>" alt="">
	   			</div>
	   				   				   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Frameworks</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/FL.png'/>" alt="">
	   			</div>

	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Cloud Services</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/CS.png'/>" alt="">
	   			</div>
	   				   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Database</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/Database.png'/>" alt="">
	   			</div>
	   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Version Control</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/VC.png'/>" alt="">
	   			</div>
	   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">IDE</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/ide.png'/>" alt="">
	   			</div>
	   			
	   			<div class="skills_tech-stacks">
	   				<div class="skills_title">Tools & Utilities</div>
	   				<img class="skills_img" src="<c:url value='/images/skills/tu.png'/>" alt="">
	   			</div>		
			</div> 
		</div>
	</article>   	

	<article class="artDocumenting" id="documenting">
		<div class="documenting">
		
   			<div class="aboutMeTitle">
				<div class="aboutMeTitle_text">DOCUMENTING</div>
				<div class="aboutMeTitle_link-wrapper">
				<img class="aboutMeTitle_link" alt=""
					 <%-- src="<c:url value='/images/black-link.png'/>" > --%>
					 src="https://img.icons8.com/?size=80&id=XK6z0FGoTKzS&format=png">
				</div>				
   			</div>	
		
			<div class="documenting_container">
				<a class="documenting_git-hub" href="https://github.com/InitTester" target="_blank">
					<div class="documenting_img_wrapper">
						<img class="documenting_img" src="<c:url value='/images/documenting/git-hub.png'/>" alt="">
					</div>
					<div class="documenting_url">https://github.com/InitTester</div>
					<div class="documenting_description">
						<p><b>스터디/프로젝트 소스 코드 저장소</b>입니다.</p>
						<ul>
							<li>개발/깃헙에 익숙해지려고 남기는 소스 코드</li>
							<li>지금까지 스터디, 프로젝트에서 진행한 소스, 앞으로 스터디하고자 기록을 남기는 곳입니다</li>
						</ul>
					</div>
				</a>
				<a class="documenting_tistory" href="https://initsave.tistory.com/" target="_blank">
					<div class="documenting_img_wrapper">
						<img class="documenting_img" src="<c:url value='/images/documenting/tistory.png'/>" alt="">
					</div>
					<div class="documenting_url">https://initsave.tistory.com/</div>
					<div class="documenting_description">
						<p><b>스터디와 개발중 에러를 기록하기 위한 목적의 블로그</b>입니다.</p>
						<ul>
							<li>공부한 것을 진정한 나의 것으로 만들기 위한 기록</li>
							<li>개발자의 길을 걸으며 공부한 개발 관련 지식 정리</li>
							<li>스터디로 공부한 내용 복습 겸 정리</li>
						</ul>
					</div>
				</a>
			</div>
		</div>
	</article>	   	
	 
	<article class="artProjects" id="projects">
		<div class="projects_content">
	
	   		<div class="aboutMeTitle">
				<div class="aboutMeTitle_text">PROJECTS</div>
				<div class="aboutMeTitle_link-wrapper">
				<img class="aboutMeTitle_link" alt=""
					 <%-- src="<c:url value='/images/black-link.png'/>" > --%>
					 src="https://img.icons8.com/?size=80&id=ySIGkb04a2KV&format=png">
				</div>				
	   		</div>	
	   				
			<div class="projects_container">
			
				<div class="projects_container_project">				
					<div class="project_title">개인 포트폴리오</div>
					<div class="project_period">2024.05~
						&nbsp;
						<span class="project_new-line-chunk">(1人 개인 프로젝트)</span>
					</div>
					
					<div class="project_info">
						<div class="project_img-carousel">
							<div class="project_imgs">
								<div class="project_img-wrapper">
									<img class="project_img" src="<c:url value='/images/projects/portfolio/1.png'/>"  alt="">
								</div>
								<div class="project_img-wrapper">
									<img class="project_img" src="<c:url value='/images/projects/portfolio/2.png'/>" alt="">
								</div>
							</div>
						</div>
						
						<div class="project_descriptions">
							<div class="project_main-description">
								<b>회원가입, 로그인, 게시판, 포트폴리오를 위한 웹사이트</b>
								입니다. 실제로 운영되는 사이트들을 참고하여 직접 개발하였고 이를 통해 웹 개발 역량을 보여주고자 합니다.
								<p></p>스터디그룹에서 공부하고 있었던<!-- -->
								<span class="project_red"> Tails</span>를 통해 header, body, footer를 나누어 구현했습니다.
								<p></p>회원가입, 로그인에서는 
								<span class="project_red"> Jasypt, Bcrypt</span>를 통해 보안/인증을 구현 했습니다.
								<p></p>게시판에서는 
								<span class="project_red">AWS EC2, RDS, Nginx</span>
								를 처음 사용해보며 첨부파일, 파일미리보기 등 환경설정을 통해 실제 어떻게 운영되는지 알수 있었습니다.<!-- -->
								<p></p>또한 게시글에서  
								<span class="project_red">댓글, 좋아요/싫어요, 첨부파일</span>
								를 직접 구현 해보며 작동 하는 방식을 고민하고, 구현된 것을 보며 성취감을 느꼈습니다. 
								<span class="project_red">GitHub Issues</span>관리를 통해 현업시 어떤 방식으로 해야 할지 고민하는 시간도 되었습니다.	
								<p></p>
								<!-- <div class="project_show-readme-btn">자세히 보기 ▶ README</div> -->
							</div>
							
							<div class="project_description">
								<div class="project_label">주요 기능</div>
								<div class="project_value">회원가입/로그인 회원인증, 댓글/좋아요, 게시글 수정을 통한 첨부파일 미리보기, 다운로드,게시글에 따른 조회 Rank </div>
							</div>
							
							<div class="project_description">
								<div class="project_label">GitHub</div>
								<div class="project_value">
									<a class="project_url" href="https://github.com/InitTester/2024_Portfolio"  target="_blank">github.com/InitTester/2024_Portfolio</a>
								</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">URL</div>
								<div class="project_value">
									<a class="project_url" href="https://bit.ly/4blLmeh"  target="_blank">bit.ly/4blLmeh</a>
								</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Frontend</div>
								<div class="project_value">JavaScript, HTML, CSS, JQuery</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Backend</div>
								<div class="project_value">Java, Spring, JSP, Tiles, Maven, Mybatis, Junit5</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Database</div>
								<div class="project_value">MySQL</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Deployment</div>
								<div class="project_value">ApacheTomcat, Nginx, AWS EC2/RDS, Jenkins, docker</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">ETC</div>
								<div class="project_value">DBeaver, Eclipse, MobaXterm, FileZilla, SourceTree, GitHub, Notion</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="projects_container_project">				
					<div class="project_title">Fundly</div>
					<div class="project_period">2024.01.15 ~ 2024.03.15
						&nbsp;
						<span class="project_new-line-chunk">(7人 팀 프로젝트)</span>
					</div>
					
					<div class="project_info">
						<div class="project_img-carousel">
							<div class="project_imgs">
								<div class="project_img-wrapper">
									<img class="project_img" src="<c:url value='images/projects/fundly/1.png'/>"  alt="">
								</div>
								<div class="project_img-wrapper">
									<img class="project_img" src="<c:url value='/images/projects/fundly/2.png'/>" alt="">
								</div>
							</div>
						</div>
						
						<div class="project_descriptions">
							<div class="project_main-description">
								<b>크리에이터들의 창작활동을 지원하기 위한 크라우드 펀딩 플랫폼 프로젝트</b>
								를 진행하였습니다. 이 프로젝트는 <a href="https://tumblbug.com/">텀블벅</a> 사이트를 클론코딩하여 개발하였고, 
								팀원들과 함께 팀프로젝트로 수행하였습니다.
								
								<p></p>스터디 중이던
								<span class="project_red"> DB Modeling, ERD, Spring, MySQL</span>스킬을 채택했고 
								기본 셋팅을 팀원들과 상의 후 함께 설정하였습니다. 기본적인 환경 설정은 <span class="project_red">Java(config)</span>로 설정하였고, 
								<span class="project_red">Domain</span>별로 프로젝트 구조를 선택하여 유지보수의 편리함을 도모했습니다. 
								
								<p></p>
								<span class="project_red">TDD, Mockito</span>를 통해 의존적이지 않은 테스트도 진행했으며
								<span class="project_red">GitHub Fork</span>를 통해 여러 명이 함께 프로젝트를 진행할 때 소스 관리 방법도 익혔습니다. 
								이 과정을 통해 협업의 중요성과 실무와 유사한 환경에서의 프로젝트 진행 경험을 쌓을 수 있었습니다.
								
								<p></p>이 팀 프로젝트를 통해 얻은 귀중한 경험은 다음과 같습니다. 작은 부분부터 전반적인 사항까지 팀원들과 의논하며 진행했고, 
								클론 코딩으로 구현된 가시적 부분과 비가시적 부분에 대해 팀원들과 의견을 취합하여 실제 운영 중인 사이트와의 차이점을 줄이기 위해 
								노력했습니다. 팀원들과의 연결 부위에서 발생할 수 있는 문제점을 사전에 주의하는 법도 배울 수 있었습니다.
									
								<p></p>
								<!-- <div class="project_show-readme-btn">자세히 보기 ▶ README</div> -->
							</div>
							
							<div class="project_description">
								<div class="project_label">주요 기능</div>
								<div class="project_value">프로젝트생성, 후원자 참여, 목표 설정, 리워드 제공, 커뮤니티 상호작용이 있습니다.</div>
							</div>

							<div class="project_description">
								<div class="project_label">ERD</div>
								<div class="project_value">
									<a class="project_url" href="https://www.erdcloud.com/d/8TcNeth4NEGRfXgtt" target="_blank">Fundly ERD</a>
								</div>
							</div>							
							<div class="project_description">
								<div class="project_label">GitHub</div>
								<div class="project_value">
									<a class="project_url" href="https://github.com/InitTester/Fundly" target="_blank">github.com/InitTester/Fundly</a>
								</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Frontend</div>
								<div class="project_value">JavaScript, HTML, CSS, JQuery</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Backend</div>
								<div class="project_value">Java, Spring, JSP, Tiles, Maven, Mybatis, Junit5, Mockito</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">Database</div>
								<div class="project_value">MySQL</div>
							</div>
							
							<div class="project_description">
								<div class="project_label">ETC</div>
								<div class="project_value">ERDCloud, Eclipse, GitHub, Notion</div>
							</div>
						</div>
					</div>
				</div>		
				
			</div>
		</div>
	 </article>	
   </section>
   
   <div class="modalAboutMe" id="modalAboutMe">
       <div class="modal-content">
           <nav class="navigation">
               <ul>
                   <li><a href="#about-me">ABOUT ME</a></li>
                   <li><a href="#skills">SKILLS</a></li>
                   <li><a href="#documenting">DOCUMENTING</a></li>
                   <li><a href="#projects">PROJECTS</a></li>
               </ul>
           </nav>
       </div>
    </div>
