
# 🖥 배포 사이트
 [Portfolio HomePage](https://bit.ly/4blLmeh)
 
   *지속적인 ec2 서버 재구축(ec2환경설정 익숙해지기 위해) 중이므로 종종 개발 사이트 연결이 맞지 않는 경우가 생길 수도 있습니다*
    
# 📝 프로젝트 개요
이 프로젝트는 사용자 인증 및 게시판 기능을 갖춘 개인 홈페이지를 개발하는 것을 목표로 합니다. 이를 통해 웹 개발 역량을 보여주고자 합니다

      
# 🏗 구현 기능
### 1. 회원가입
 사용자가 새 계정을 만들기 위해 이름, 이메일, 비밀번호 등의 정보를 입력합니다.   
 입력된 정보를 검증, 데이터베이스에 저장합니다.
 
1. 이메일 인증
    - 이메일 전송(javax.mail, spring-context-support 라이브러리 사용)
    - 네이버 계정을 이용해서 이메일 전송(네이버 계정 사용시에 jasypt-spring31 라이브러리를 사용, 네이버 계정 정보에 대한 보안 강화)
2. 비밀번호 암호화
    - bcrypt 라이브러리를 사용, 데이터 베이스 저장 시 비밀번호에 대한 보안 강화
3. 유효성 검사 (회원 가입 제약 사항)
   - 아이디
     - [x]  공백 또는 빈 칸 불가능, 4~20자의 영어 소문자, 숫자만 사용 가능
     - [x]  아이디 중복 체크
   - 이메일
     - [x]  공백 또는 빈칸 불가능, 이메일 형식 준수 "[xxx@xxx.xxx](mailto:xxx@xxx.xxx)"
     - [x]  이메일 중복 체크
   - 이름
     - [x]  공백 또는 빈칸 불가능, 숫자 입력 불가능, 최소 2자 이상
   - 비밀번호
     - [x]  공백 또는 빈칸 불가능, 8~20자, 영문, 숫자, 특수 문자 혼합하여 입력
     - [x]  비밀번호 확인
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/980cde11-b67f-48fc-b80d-e3a13761c316">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/e4fbd4ed-a42f-4c10-a8da-8239572523ca">

### 2. 로그인
 사용자가 아이디와 비밀번호를 입력하여 로그인합니다.
 입력된 정보를 검증하고, 성공적으로 로그인하면 세션을 생성
 만약 아이디/비밀번호를 잊었다면 찾기 기능을 통해 사용 가능합니다.

1. 아이디 
    - 사용자가 입력한 아이디를 데이터베이스에서 조회하여 일치 여부를 확인
2. 비밀번호
    - 사용자가 입력한 비밀번호를 데이터베이스에서 조회하여 일치 여부를 확인
    - 로그인 시 입력된 암호와 DB에 저장된 암호화된 정보와 비교 하여 보안성 높임
3. 아이디 저장
    - 쿠키를 통해 아이디 저장, 사용자의 편의성을 높임    
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/f0656aa0-3ebb-4b4b-b649-df2753073a55">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/4a8ebe5d-1bdd-45b2-9c9f-fe6456d1616b">
4. 아이디 찾기
    - 데이터베이스에 저장된 사용자의 이름과 이메일 정보로 아이디를 확인    
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/a61d4651-3d8e-4e6a-ba4d-8e9b3e1b74e7">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/98b554e8-a029-4e27-9fdf-bdad13cd6e0f">
5. 비밀번호 변경
   비밀번호 재설정 (계정확인 -> 이메일 전송링크 -> 비밀번호 변경)   
    - 데이터베이스에 저장된 사용자의 이메일로 비밀번호 변경 이메일 전송(사용자인증)  
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/559d998a-c846-4f72-852c-faa51dbe68d0">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/e924b7e5-5d1a-4ffa-9c82-557ac6002d17">
    - 전송된 이메일 비밀번호 변경 url을 통해 비밀번호 변경  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/357b4b41-6661-4c90-99da-7cf9fffaf29c">
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/2142b6bc-61f6-49f1-b4e3-606f2059028a">


### 3. 게시판
 사용자가 로그인인 후 자유롭게 게시글을 작성합니다.
 게시글은 첨부파일도 추가/다운로드, 게시글에 대한 좋아요/싫어요, 댓글 기능이 가능합니다.
 
1. 게시판 조회
    - 게시글 첨부파일여부, 댓글, 조회수 표시
    - 페이징을 통해 게시글 목록을 이동  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/5e997f22-09b9-4f5e-8abd-ed3af7cc26d0">
2. 게시글 등록
    - [trumbowyg](https://alex-d.github.io/Trumbowyg/) 텍스트 에디터를 이용한 게시물 내용 입력
    - 첨부파일 (3개로 제한) 등록/미리보기 확인 가능  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/5521bcc3-ba2e-4a6a-bed5-cd13a16ff7b2">  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/5bbd0bce-d9c8-4225-b451-7315b8159565">
3. 게시글 상세조회
    - 게시글에 좋아요/싫어요 
    - 게시글 수정/삭제 
    - 게시글 첨부파일 다운로드  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/0be91b6b-92ee-4078-a12a-79f90b87b357">
    - 게시글 댓글  
      <img height="220" width="220" alt="image" src="">
4. 게시글 수정
    - 게시글에 수정
    - 게시글 첨부파일 미리보기(진행중)
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/445628f4-29dc-44a8-be90-405ba384fb2b">
5. 게시글 삭제




# ⏳ 개발기간 
 2024.05.27 ~ ing
 
# 🛠 사용 기술 및 Tools
	 - Front-End : JS , HTML, CSS, JQuery
	 - Back-End : Java, Spring	
	 - Database : MySQL, myBatis
	 - Collab : GitHub	 
	
# ⚙ 개발 환경
 -  이클립스 Version: 2024-03 (4.31.0)
 - Spring Version : 5.3.32
 - Maven Version : 3.9.6
 - Tomcat Version : 9.0.87
 - Java Version : openJDK 11.0.2
 - AWS EC2, RDS, Nginx
 
