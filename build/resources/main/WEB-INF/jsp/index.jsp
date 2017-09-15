<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<a href="test">测试控制器</a>
	<a href="users">测试数据库</a>
	<a href="users/page?page=1&rows=5">测试分页</a>
	<a href="user/transaction">测试事务</a>
	<a href="user/1/name">测试缓存</a>
	<form action="upload" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="submit" value="测试上传">
	</form>
	<form action="download" method="get">
		<input type="submit" value="测试下载">
	</form>
	<form action="user" method="post">
		<label>id:</label><input type="text" name="id"><br/>
		<label>name:</label><input type="text" name="name"><br/>
		<input type="submit" value="添加用户">
	</form>
</body>
</html>