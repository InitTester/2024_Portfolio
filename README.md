
# 🖥 배포 사이트
 <a href="https://bit.ly/4bxohp2" target="_blank">Portfolio HomePage</a>
 
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
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/9d296483-3d44-448e-83d8-3ac42d123413">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/c174e674-8fd5-4391-8a9b-af0851da030e">

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
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/39b1352d-63ba-4eb0-bd16-cc993ec823d1">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/a962700e-9eac-4166-a9fb-c3ca3e0e1bdf">
4. 아이디 찾기
    - 데이터베이스에 저장된 사용자의 이름과 이메일 정보로 아이디를 확인    
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/790afe6c-2437-4d76-85b3-ec8666f05ac1">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/edcefafe-0d65-4a81-9b48-11438996a1dc">
5. 비밀번호 변경
   비밀번호 재설정 (계정확인 -> 이메일 전송링크 -> 비밀번호 변경)   
    - 데이터베이스에 저장된 사용자의 이메일로 비밀번호 변경 이메일 전송(사용자인증)  
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/bf5715bb-bb74-44f7-8d17-13a320858160">
      <img height="250" width="250" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/f4ae0cd9-a9ea-45d7-bc54-97b6659bc908">
    - 전송된 이메일 비밀번호 변경 url을 통해 비밀번호 변경  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/3e956f0a-b8a2-4235-93b0-ddba66dedf30">
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/71877e03-14b9-4b1c-87c1-ddb934dbe375">

### 3. 게시판
 사용자가 로그인인 후 자유롭게 게시글을 작성합니다.
 게시글은 첨부파일도 추가/다운로드, 게시글에 대한 좋아요/싫어요, 댓글 기능이 가능합니다.
 
1. 게시판 조회
    - 게시글 첨부파일여부, 댓글, 조회수 표시
    - 페이징을 통해 게시글 목록을 이동  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/e84d83ea-06ac-4623-963f-ffb30e0d0e60">
2. 게시글 등록
    - [trumbowyg](https://alex-d.github.io/Trumbowyg/) 텍스트 에디터를 이용한 게시물 내용 입력
    - 첨부파일 (3개로 제한) 등록/미리보기 확인 가능  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/2f0ad890-3e65-4358-a27a-16a36c811d53">  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/38cb27b5-c339-4da3-bed4-a8d2a136ce12">
3. 게시글 상세조회
    - 게시글에 좋아요/싫어요 
    - 게시글 수정/삭제 
    - 게시글 첨부파일 다운로드  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/b620ebba-de5b-41b4-918c-21144d05c052">
    - 게시글 댓글  
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/52521be4-ea1f-4437-b8bd-943e950c375d">
4. 게시글 수정
    - 게시글에 수정
    - 게시글 첨부파일 미리보기   
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/2c76d06f-0d66-4034-b82d-7e136cf5d0ea">
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/e0360f23-1f6d-4325-8f8b-76aa3ca696f6"> 
5. 게시글 삭제   
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/33b39be2-d896-4bce-bb78-e5e997f4b86c">
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/e1a09d9b-0d47-4a57-a309-c0bf977ee0f6">
      <img height="220" width="220" alt="image" src="https://github.com/InitTester/2024_Portfolio/assets/143479869/59906f67-a561-4d9f-a73b-c619dd89e0d4">
      
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
 
