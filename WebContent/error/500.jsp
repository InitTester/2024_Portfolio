<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

	<head>
	    <title>HTTP 505 Error - HTTP Version Not Supported</title>
	    <style>
	        body {
	            font-family: Arial, sans-serif;
	            background-color: #f4f4f4;
	            margin: 0;
	            padding: 0;
	            display: flex;
	            justify-content: center;
	            align-items: center;
	            height: 100vh;
	        }
	        .container {
	            text-align: center;
	            background-color: #fff;
	            padding: 50px;
	            border-radius: 8px;
	            box-shadow: 0 0 10px rgba(0,0,0,0.1);
	        }
	        h1 {
	            font-size: 48px;
	            color: #ff0000;
	        }
	        p {
	            font-size: 18px;
	            color: #333;
	        }
	        a {
	            display: inline-block;
	            margin-top: 20px;
	            padding: 10px 20px;
	            font-size: 16px;
	            color: #fff;
	            background-color: #007bff;
	            text-decoration: none;
	            border-radius: 5px;
	        }
	        a:hover {
	            background-color: #0056b3;
	        }
	    </style>
	</head>

	<body>
	    <div class="container">
	        <h1>505 Error</h1>
	        <p>HTTP Version Not Supported</p>
	        <p>죄송합니다. 서버에서 HTTP 요청을 처리할 수 없습니다.</p>
	        <p>나중에 다시 시도해 주세요.</p>
	        <a href="<c:url value='/index.do'/>">홈으로 돌아가기</a>
	    </div>
	</body>

</html>
