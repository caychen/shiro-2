<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Login Page!</h3>
	<form action="shiro/login" method="post">
		<shiro:guest>
			<h4>Welcome to Here!</h4>
		</shiro:guest>
		Username: <input name="username" />
		<br/><br/>
		Password: <input type="password" name="password"/>
		<br/><br/>
		<input type="submit" value="登录" />
	</form>
</body>
</html>