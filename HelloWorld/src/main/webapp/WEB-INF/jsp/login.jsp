<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
</head>
<body>

<form method="POST" action="login">
<input type="text" id="login_name" name="login_name" value="" />
<input type="text" id="login_password" name="login_password" value="" />

<button type="submit">登录</button>

</form>
<c:if test="${errorMessage!=null}">${errorMessage}</c:if>
</body>
</html>