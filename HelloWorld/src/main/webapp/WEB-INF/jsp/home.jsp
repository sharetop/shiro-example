<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>WO+开放平台</title>
</head>
<body>
<h4><a href="logout">退出</a></h4>

<c:choose>
<c:when test="${message!=null}">
<h1>${message}</h1>
</c:when>
<c:otherwise>
<h2  style="color:red">No message</h2>
</c:otherwise>
</c:choose>

<p>
<shiro:hasRole name="administrator">
<h4>管理员才能看到我</h4>
</shiro:hasRole>

<shiro:hasPermission name="system:view">
需要有system:view权限
</shiro:hasPermission>

</p>

<h4><a href="admin">Admin Console</a></h4>

</body>
</html>