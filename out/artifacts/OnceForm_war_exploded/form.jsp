<%--
  Created by IntelliJ IDEA.
  User: WangS
  Date: 2017/2/26
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
    <form action="/form/DoFormServlet" method="post">
        <input type="hidden" name="token" value="${token}">
        用户名：<input type="text" name="username"><br/>
        <input type="submit" value="提交">
    </form>
</body>
</html>
