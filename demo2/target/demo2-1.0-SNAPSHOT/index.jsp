<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<form action="${pageContext.request.contextPath}/form" method="get">
    <label>
        test2:<input type="text" name="url">
    </label><br>
    <input type="submit" name="submit">
</form>
<form action="${pageContext.request.contextPath}/search" method="get">
    id查询:<input type="text" name="id"><br>
    <input type="submit" name="submit">
</form>
<form action="${pageContext.request.contextPath}/register" method="get">
    <h1>注册</h1>>
    id:  <input type="text" name="id"><br>
    name:<input type="text" name="name"><br>
    <input type="submit" name="submit">
</form>

</body>
</html>