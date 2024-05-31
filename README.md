# 프로젝트 개요
이 프로젝트는 사용자 인증 및 게시판 기능을 갖춘 개인 홈페이지를 개발하는 것을 목표로 합니다. 이를 통해 웹 개발 역량을 보여주고자 합니다   

# 구현 기능
> #### 회원가입
<img height="500" width="500" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/65dbd2d8-ac94-4958-9d30-ca7fe83fd428">

> 사용자가 새 계정을 만들기 위해 이름, 이메일, 비밀번호 등의 정보를 입력합니다.   
> 입력된 정보를 검증,데이터베이스에 저장
> 
> 구현 기능
> >1. 이메일 인증
     * 이메일 전송(javax.mail, spring-context-support 라이브러리 사용)
     * 네이버 계정을 이용해서 이메일 전송(네이버 계정 사용시에 jasypt-spring31 라이브러리를 사용, 네이버 계정 정보에 대한 보안 강화)
> >2. 비밀번호 암호화
     * bcrypt 라이브러리를 사용, DB에서 비밀번호에 대한 보안 강화
> >3. 이메일 유효성 검사   


> #### 로그인
<img height="500" width="500" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/ac6ab488-a2dc-4b74-a346-800b362d1f90">

> 사용자가 이메일과 비밀번호를 입력하여 로그인합니다.
> 입력된 정보를 검증하고, 성공적으로 로그인하면 세션을 생성합니다.
> 
> 구현 기능
> >1. 비밀번호 암호화
     * 
> >2. 로그인 실패 시 에러 메시지 표시
     * 
> >3. Remember me 기능

# 사용 기술 및 Tools
	 - Front-End : JS , HTML, CSS, JQuery
	 - Back-End : Java, Spring	
	 - Database : MySQL, myBatis
	 - Collab : GitHub	 
	
# 개발 환경
 - 이클립스 Version: 2024-03 (4.31.0)
 - Spring Version : 5.3.32
 - Maven Version : 3.9.6
 - Tomcat Version : 9.0.87
 - Java Version : openJDK 11.0.2
